package example02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class LuceneHandle {
	private Directory dir;
	private Analyzer analyzer;
	
	public static void main(String[] args) {
		LuceneHandle lh = new LuceneHandle("E:/data/lucene/index");
		lh.index();													//增
		lh.delete(5);												//删
		lh.update(2, "测试内容", "修改测试");						//改
		List<Medicine> ms = lh.search("感冒", "name", "function");	//查
		for (Medicine m : ms) {
			System.out.println("(" + m.getId() + ")" + m.getName() + "\t" + m.getFunction());
		}
	}

	/** 创建索引存储位置和分词器 */
	public LuceneHandle(String indexPath) {
		try {
			dir = FSDirectory.open(new File(indexPath));
			analyzer = new IKAnalyzer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 创建索引 */
	public void index() {
		try {
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_48, analyzer);
			IndexWriter writer = new IndexWriter(dir, config);
			for (Medicine m : DataFactory.getData()) {
				Document doc = addDocument(m.getId(), m.getName(), m.getFunction());
				writer.updateDocument(new Term("id", m.getId().toString()), doc);
			}
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/** 根据ID删除索引 */
	public void delete(Integer id) {
		try {
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_48, analyzer);
			IndexWriter writer = new IndexWriter(dir, config);
			Term term = new Term("id", String.valueOf(id));
			writer.deleteDocuments(term);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 更新索引 */
	public void update(Integer id, String title, String content) {
		try {
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_48, analyzer);
			IndexWriter writer = new IndexWriter(dir, config);
			Document doc = addDocument(id, title, content);
			Term term = new Term("id", String.valueOf(id));
			writer.updateDocument(term, doc);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 查询 */
	public List<Medicine> search(String keyword, String... fields) {
		try {
			//打开索引
			IndexReader reader = DirectoryReader.open(dir);
			IndexSearcher is = new IndexSearcher(reader);
			
			//创建查询
			MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_48, fields, analyzer);
			Query query = parser.parse(keyword);
			
			//执行搜索
			long start = new Date().getTime();
			TopDocs docs  = is.search(query, 10);											//搜索索引，返回前10条
			long end = new Date().getTime();
			System.err.println("找到" + docs.totalHits + "个文件，用时"+ (end - start) + "毫秒，匹配查询'" + keyword + "'：");	//输出搜索的统计数据

			//创建设置高亮器
//			Formatter fmt = new SimpleHTMLFormatter();		//加亮格式，默认为<B>关键字</B>
			Formatter fmt = new SimpleHTMLFormatter("<font color='red'>", "</font>");	//格式为preTag关键字postTag
			Scorer scorer = new QueryScorer(query);			//计分器，首先将片段排序，从原始输入的单词、词组和布尔查询中提取项，基于加权因子给它们加权。为便于QueryScoere使用，还必须对查询的原始形式进行重写。比如，带通配符查询、模糊查询、前缀查询以及范围查询等，都被重写为BooleanQuery中所使用的项。在将Query实例传递到QueryScorer之前，可以调用Query.rewrite(IndexReader)方法来重写Query对象
			Highlighter hl = new Highlighter(fmt, scorer);	//高亮器
			Fragmenter fm = new SimpleFragmenter(100);		//分割器，将原始文本分割成多个片段，大小为100字符
			hl.setTextFragmenter(fm);
			
			//高亮处理
			List<Medicine> result = new ArrayList<Medicine>();
			for (ScoreDoc sd : docs.scoreDocs) {
				//原始数据
				Document doc = is.doc(sd.doc);
				Integer id = Integer.parseInt(doc.get("id"));
				String name = doc.get("name");
				String function = doc.get("function");
				
				//加量name和function
				String lighterName = hl.getBestFragment(analyzer, "name", name);
				String lighterFunciton = hl.getBestFragment(analyzer, "function", function);
				
				//封装新数据
				Medicine medicine = new Medicine();
				medicine.setId(id);
				medicine.setName(lighterName == null ? name : lighterName);
				medicine.setFunction(lighterFunciton == null ? function : lighterFunciton);
				result.add(medicine);
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/** 判断是否已经存在索引文件（根据segments.gen是否存在，判断是否是第一次创建索引） */
	@SuppressWarnings("unused")
	private boolean isExistIndexFile(String indexPath) throws Exception {
		File indexFile = new File(indexPath + "/segments.gen");	
		return indexFile.exists();
	}
	
	/** 添加文档 */
	private Document addDocument(Integer id, String name, String function) {
		Document doc = new Document();
		FieldType ft = new FieldType();
		ft.setStored(true);
		ft.setIndexed(true);
		ft.setTokenized(false);
		doc.add(new Field("id", String.valueOf(id), ft));
		doc.add(new Field("name", name, TextField.TYPE_STORED));
		doc.add(new Field("function", function, TextField.TYPE_STORED));
		return doc;
	}
}