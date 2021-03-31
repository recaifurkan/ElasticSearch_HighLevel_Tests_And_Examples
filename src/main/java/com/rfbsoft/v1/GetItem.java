package com.rfbsoft.v1;




import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.Map;

public class GetItem {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));



        GetRequest getRequest = new GetRequest(
                "posts",
                "1");

        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        String index = getResponse.getIndex();
        String id = getResponse.getId();
        if (getResponse.isExists()) {
            long version = getResponse.getVersion();
            String sourceAsString = getResponse.getSourceAsString();
            Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
            System.out.println(sourceAsString);
            byte[] sourceAsBytes = getResponse.getSourceAsBytes();
        } else {

        }


        client.close();
    }


}
