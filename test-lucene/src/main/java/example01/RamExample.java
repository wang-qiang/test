package example01;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.wltea.analyzer.lucene.IKAnalyzer;

import fuc.common.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/** 内存索引例子 */
public class RamExample {
	
	public static void main(String[] args) throws IOException {
		RamExample re = new RamExample();
		re.executeIndex("E:/data/lucene/doc");
		re.search("中石油");
	}
	
	/** 创建索引存储位置 */
	private Directory dir = new RAMDirectory();
	
	/** 执行 */
	public void executeIndex(String dataPath) {
		long start = new Date().getTime();
		int numIndexed = index(dataPath);
		long end = new Date().getTime();
		System.err.println("索引" + numIndexed + "个文件，用时" + (end - start) + "毫秒");
	}
	
	/** 索引 */
	public int index(String dataPath) {
		try {
			Analyzer analyzer = new IKAnalyzer();											//分词器
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_48, analyzer);	//索引创建器配置
			IndexWriter writer = new IndexWriter(dir, config);								//索引创建器
//			writer.deleteAll();
			indexDirectory(writer, new File(dataPath));
			int docCount = writer.maxDoc();
			writer.close();
			return docCount;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/** 搜索 */
	public void search(String keyword) {
		try {
			//打开索引
			DirectoryReader reader = DirectoryReader.open(dir);								//位置读取器
			IndexSearcher is = new IndexSearcher(reader);									//打开索引
			
			//创建查询
			Analyzer analyzer = new IKAnalyzer();											//创建分词器
//			QueryParser parser = new QueryParser(Version.LUCENE_48, "contents", analyzer);	//查询分析器
			QueryParser parser = new MultiFieldQueryParser(Version.LUCENE_48, new String[]{"filename", "contents"}, analyzer);
			Query query = parser.parse(keyword);											//解析查询字符串
			
			//执行搜索
			long start = new Date().getTime();
			TopDocs docs  = is.search(query, 10);											//搜索索引
			long end = new Date().getTime();
			System.err.println("找到" + docs.totalHits + "个文件，用时"+ (end - start) + "毫秒，匹配查询'" + keyword + "'：");	//输出搜索的统计数据
			
			//输出
			for (ScoreDoc sd : docs.scoreDocs) {
				Document doc = is.doc(sd.doc);
				System.out.println("得分" + sd.score + "，" + doc.get("filename"));
			}
		} catch (IOException | ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/** 索引文件夹 */
	private void indexDirectory(IndexWriter writer, File dir) {
		for (File f : dir.listFiles()) {
			if (f.isDirectory()) {
				indexDirectory(writer, f); 				//递归遍历
			} else if (f.getName().endsWith(".txt")) {	//只索引.txt文件
				indexFile(writer, f);
			}
		}
	}

	/** 索引文件 */
	private void indexFile(IndexWriter writer, File f) {
		try {
			if (f.isHidden()) return;					//过滤隐藏文件
			Document doc = new Document();
			doc.add(new StringField("filename", f.getCanonicalPath(), Field.Store.YES));		//索引文件名
			doc.add(new TextField("contents", FileUtil.readTextFromTxt(f), Field.Store.YES));	//索引文件的内容
			writer.updateDocument(new Term("filename", f.getCanonicalPath()), doc);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}