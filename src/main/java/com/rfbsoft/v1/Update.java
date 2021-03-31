package com.rfbsoft.v1;




import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.get.GetResult;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Update {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));



        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("updated", new Date());
        jsonMap.put("reason", "daily update");
        UpdateRequest request = new UpdateRequest("posts", "1")
                .doc(jsonMap);



        String jsonString = "{\"created\":\"2017-01-01\"}";
        request.upsert(jsonString, XContentType.JSON);

        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.timeField("updated", new Date());
            builder.field("reason", "daily update");
//            builder.field("painless" , 1);
        }
        builder.endObject();

        request .doc(builder);



        UpdateResponse updateResponse = client.update(
                request, RequestOptions.DEFAULT);

        String index = updateResponse.getIndex();
        String id = updateResponse.getId();
        long version = updateResponse.getVersion();
        if (updateResponse.getResult() == DocWriteResponse.Result.CREATED) {

        } else if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {

        } else if (updateResponse.getResult() == DocWriteResponse.Result.DELETED) {

        } else if (updateResponse.getResult() == DocWriteResponse.Result.NOOP) {

        }






        request = new UpdateRequest("posts", "1");


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("count", 4);

        Script inline = new Script(ScriptType.INLINE, "painless",
                "ctx._source.painless += params.count", parameters);
        request.script(inline);



        request.fetchSource(true);




        updateResponse = client.update(
                request, RequestOptions.DEFAULT);


        GetResult result = updateResponse.getGetResult();
        if (result.isExists()) {
            String sourceAsString = result.sourceAsString();
            System.out.println(sourceAsString);
            Map<String, Object> sourceAsMap = result.sourceAsMap();
            byte[] sourceAsBytes = result.source();
        } else {

        }










        client.close();
    }


}
