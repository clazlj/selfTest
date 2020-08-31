package util;

//import org.apache.http.HttpHost;
//import org.elasticsearch.action.get.GetRequest;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.Strings;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.SearchHits;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
//import org.junit.Test;

public class ElasticDemo {
    //@Test
    //public void initialization() {
    //    RestHighLevelClient client = new RestHighLevelClient(
    //            RestClient.builder(
    //                    new HttpHost("localhost", 9200, "http"),
    //                    new HttpHost("localhost", 9201, "http")));
    //    IndexRequest request = new IndexRequest("posts");
    //    request.id("1");
    //    String jsonString = "{" +
    //            "\"user\":\"kimchy\"," +
    //            "\"postDate\":\"2013-01-30\"," +
    //            "\"message\":\"trying out Elasticsearch\"" +
    //            "}";
    //    request.source(jsonString, XContentType.JSON);
    //
    //    Map<String, Object> jsonMap = new HashMap<>();
    //    jsonMap.put("user", "kimchy");
    //    jsonMap.put("postDate", new Date());
    //    jsonMap.put("message", "trying out Elasticsearch");
    //    IndexRequest indexRequest = new IndexRequest("posts")
    //            .id("1").source(jsonMap);
    //    try {
    //        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
    //
    //
    //        GetRequest getRequest = new GetRequest("megacorp2", "1");
    //
    //        String[] includes = new String[]{"message", "*Date"};
    //        includes = null;
    //        String[] excludes = Strings.EMPTY_ARRAY;
    //        FetchSourceContext fetchSourceContext =
    //                new FetchSourceContext(true, includes, excludes);
    //        getRequest.fetchSourceContext(fetchSourceContext);
    //
    //        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
    //
    //        String index = getResponse.getIndex();
    //        String id = getResponse.getId();
    //        if (getResponse.isExists()) {
    //            long version = getResponse.getVersion();
    //            String sourceAsString = getResponse.getSourceAsString();
    //            Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
    //            byte[] sourceAsBytes = getResponse.getSourceAsBytes();
    //        } else {
    //
    //        }
    //
    //        SearchRequest searchRequest = new SearchRequest("megacorp2");
    //        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    //        QueryBuilder matchQueryBuilder = QueryBuilders.matchPhraseQuery("first_name", "美爽二");
    //        sourceBuilder.query(matchQueryBuilder);
    //        //MatchQueryBuilder
    //
    //        sourceBuilder.size(100);
    //
    //        String[] includeFields = Strings.EMPTY_ARRAY;
    //                //new String[] {"first_name", "last_name", "age"};
    //        String[] excludeFields = new String[] {"about"};
    //        sourceBuilder.fetchSource(includeFields, excludeFields);
    //        searchRequest.source(sourceBuilder);
    //
    //        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
    //        int totalShards = searchResponse.getTotalShards();
    //        int successfulShards = searchResponse.getSuccessfulShards();
    //        int failedShards = searchResponse.getFailedShards();
    //
    //        SearchHits hits = searchResponse.getHits();
    //        SearchHit[] searchHits = hits.getHits();
    //        for (SearchHit hit : searchHits) {
    //            String index0 = hit.getIndex();
    //            String id0 = hit.getId();
    //            float score0 = hit.getScore();
    //
    //            String sourceAsString = hit.getSourceAsString();
    //            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
    //            String documentTitle = (String) sourceAsMap.get("title");
    //            List<Object> users = (List<Object>) sourceAsMap.get("user");
    //            Map<String, Object> innerObject =
    //                    (Map<String, Object>) sourceAsMap.get("innerObject");
    //        }
    //
    //        long b = 2;
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //
    //    int a = 1;
    //}
}
