package com.rfbsoft.v1;




import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class Exists {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));



        GetRequest getRequest = new GetRequest(
                "posts",
                "1");



        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);


        client.close();
    }


}
