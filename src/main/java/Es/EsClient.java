package Es;

import Dto.UserDto;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class EsClient {

    public static void main(String[] args) throws IOException {

        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("127.0.0.1",9200)));

        try {
            //创建Index
        /*CreateIndexRequest request = new CreateIndexRequest("user");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSONString(createIndexResponse));*/

        /*//查询Index
        GetIndexRequest getIndexRequest = new GetIndexRequest("user");
        GetIndexResponse getIndexResponse = restHighLevelClient.indices().get(getIndexRequest, RequestOptions.DEFAULT);

        //删除Index
        DeleteIndexRequest deleteRequest = new DeleteIndexRequest("user");
        restHighLevelClient.indices().delete(deleteRequest,RequestOptions.DEFAULT);*/

            //向index中添加文档
            IndexRequest indexRequest = new IndexRequest("user");
            UserDto userDto = new UserDto();
            userDto.setUserName("111");
            userDto.setOld(11);
            String s = JSONObject.toJSONString(userDto);
            indexRequest.source(s, XContentType.JSON);
            restHighLevelClient.index(indexRequest,RequestOptions.DEFAULT);

            //条件修改
            UpdateByQueryRequest updateByQueryRequest = new UpdateByQueryRequest();
            updateByQueryRequest.indices("user");
            updateByQueryRequest.setQuery(QueryBuilders.termQuery("userName","111"));
            BulkByScrollResponse bulkByScrollResponse1 = restHighLevelClient.updateByQuery(updateByQueryRequest, RequestOptions.DEFAULT);
            //条件删除
            DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest();
            deleteByQueryRequest.indices("user");
            deleteByQueryRequest.setQuery(QueryBuilders.termQuery("old",11));
            BulkByScrollResponse bulkByScrollResponse = restHighLevelClient.deleteByQuery(deleteByQueryRequest, RequestOptions.DEFAULT);

            SearchRequest searchRequest = new SearchRequest();
            searchRequest.indices("user");
            searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            System.out.println(JSONObject.toJSONString(search.getHits()));
        } finally {
            restHighLevelClient.close();
        }
    }
}
