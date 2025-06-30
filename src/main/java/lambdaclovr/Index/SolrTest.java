import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.NoOpResponseParser;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.util.NamedList;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SolrTest {
    private static final Logger logger = LogManager.getLogger(new Object(){}.getClass().getEnclosingClass());

    public static void main(String[] args) throws Exception {
        Solr.getClusterStatus();

        indexFeatures();

        queryUsingDoc();
        queryTimeTest();
    }

    private static String toMillisTest(long millis) {
        long hr = (millis / 3600000);
        long mn = (millis / 60000) % 60;
        long sc = (millis / 1000) % 60;
        long ps = millis % 1000;

        return String.format("%02d:%02d:%02d.%03d", hr, mn, sc, ps);
    }

    private static SolrClient getSolrClient() {
        final String solrUrl = "host:port/solr";

        return new HttpSolrClient.Builder(solrUrl)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();
    }

    private static void indexFeatures() throws SolrServerException, IOException {
        int batchSize = 5000;
        String collection = "collection";

        long count = 0;

        final SolrClient client = getSolrClient();

        Instant totalTimeStart = Instant.now();

        Instant batchTimeStart = null;

        Scanner scanner = new Scanner(new File("input"));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] tokens = line.split(",");

            String videoID = tokens[0];
            long frameNumber = Long.parseLong(tokens[1]);
            long timeStamp = Long.parseLong(tokens[2]);
            String features = tokens[3].trim();

            final VideoIndexObject doc = new VideoIndexObject(UUID.randomUUID().toString(), videoID, frameNumber, timeStamp, features);

            if (count % batchSize == 0) batchTimeStart = Instant.now();

            final UpdateResponse updateResponse = client.addBean(collection, doc);

            count++;

            if (count % batchSize == 0 || !scanner.hasNextLine()) {
                client.commit(collection);

                Instant batchTimeEnd = Instant.now();
                long batchTimeElapsedMillis = Duration.between(batchTimeStart, batchTimeEnd).toMillis();                
            }
        }

        scanner.close();

        Instant totalTimeEnd = Instant.now();
        long totalTimeElapsedMillis = Duration.between(totalTimeStart, totalTimeEnd).toMillis();

        System.out.println("Features Indexed: " + count);

        System.out.println("Execution Time: " + Utils.formatMilliSeconds(totalTimeElapsedMillis) + ", " + totalTimeElapsedMillis + "ms");
    }

    private static void queryUsingDoc() throws SolrServerException, IOException {
        final SolrClient client = getSolrClient();

        final Map<String, String> queryParamMap = new HashMap<String, String>();
        queryParamMap.put("q", "f488");
        queryParamMap.put("df", "Features");
        queryParamMap.put("fl", "VideoID, FrameNumber");
        queryParamMap.put("rows", "100");
        queryParamMap.put("sort", "id asc");
        MapSolrParams queryParams = new MapSolrParams(queryParamMap);

        final QueryResponse response = client.query("cbvr", queryParams);
        final SolrDocumentList documents = response.getResults();

        logger.info("Found " + documents.getNumFound() + " documents");

        for(SolrDocument document : documents) {
            final String videoID = (String) document.getFirstValue("VideoID");
            final Long frameNumber = (Long) document.getFirstValue("FrameNumber");

            logger.info("VideoID: " + videoID + "\t FrameNumber: " + frameNumber);
        }
    }

    private static void queryTimeTest() throws SolrServerException, IOException {
        String timeLog = "timeLogTarget";

        int batch = 100;
        final SolrClient client = getSolrClient();

        for (int i = 100; i <= 100000; i += batch) {
            final Map<String, String> queryParamMap = new HashMap<String, String>();
            queryParamMap.put("q", "*:*");
            queryParamMap.put("fl", "videoID, frameNumber");
            queryParamMap.put("rows", Integer.toString(i));
            MapSolrParams queryParams = new MapSolrParams(queryParamMap);

            Instant start = Instant.now();

            final QueryResponse response = client.query("cbvr100", queryParams);
            final SolrDocumentList documents = response.getResults();

            Instant end = Instant.now();
            long duration = Duration.between(start, end).toMillis();

            Utils.save(timeLog, i + "," + response.getQTime());
        }
    }
}
