package myproject.base.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.Getter;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@Getter
public class ElasticsearchConnector {
    private final String ELASTICSEARCH_URL = "http://localhost:9200";
    private ElasticsearchClient client;

    public ElasticsearchConnector() {
        JacksonConfig jacksonConfig = new JacksonConfig();
        RestClient restClient = RestClient.builder(HttpHost.create(ELASTICSEARCH_URL)).build();
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper(jacksonConfig.getObjectMapper()));
        client = new ElasticsearchClient(transport);
    }

}
