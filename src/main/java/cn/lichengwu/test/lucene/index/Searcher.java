package cn.lichengwu.test.lucene.index;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

/**
 * search file
 * User: lichengwu
 * Date: 9/27/12
 * Time: 10:17 PM
 */
public class Searcher {

    private static final Logger log = Logger.getLogger(Searcher.class);

    /**
     * search q
     *
     * @param indexDir index dir
     * @param q        keyword
     * @throws IOException
     * @throws ParseException
     */
    public static void search(String indexDir, String q) throws IOException, ParseException {
        Directory dir = FSDirectory.open(new File(indexDir));
        IndexSearcher is = new IndexSearcher(dir);
        QueryParser parser = new QueryParser(Version.LUCENE_34, "contents", new StandardAnalyzer(Version.LUCENE_34));
        Query query = parser.parse(q);
        long start = System.currentTimeMillis();
        TopDocs hits = is.search(query, 1000);
        log.info("Found " + hits.totalHits + " document(s) (in " +
                (System.currentTimeMillis() - start) + "ms) that match query[" + q + "] :");
        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = is.doc(scoreDoc.doc);
            log.info(doc.get("fullpath"));
        }
        is.close();
    }
}
