import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;
import org.apache.solr.common.util.NamedList;

import java.util.List;

public class Solr {
    private static final Logger logger = LogManager.getLogger(new Object(){}.getClass().getEnclosingClass());

    private static final SolrClient solrClient = getSolrClient();

    private Solr() {}

    public static SolrClient getSolrClient() {
        final String solrUrl = "host:port/solr";

        return new HttpSolrClient.Builder(solrUrl)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();
    }

    public static void getClusterStatus() throws Exception {
        final SolrRequest request = new CollectionAdminRequest.ClusterStatus();

        final NamedList<Object> response = solrClient.request(request);
        final NamedList<Object> cluster = (NamedList<Object>) response.get("cluster");
        final List<String> liveNodes = (List<String>) cluster.get("live_nodes");

        logger.info("Found " + liveNodes.size() + " live nodes");
    }
}
