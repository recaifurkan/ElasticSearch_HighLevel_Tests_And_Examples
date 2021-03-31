package com.rfbsoft.v1;




import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.GetSourceRequest;
import org.elasticsearch.client.core.GetSourceResponse;

import java.io.IOException;
import java.util.Map;

public class GetSource {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));



        GetSourceRequest getSourceRequest = new GetSourceRequest(
                "posts",
                "1");



        GetSourceResponse response =
                client.getSource(getSourceRequest, RequestOptions.DEFAULT);
        Map<String, Object> source = response.getSource();
        System.out.println(response.toString());
        System.out.println(response.toString());


        client.close();
    }


}
