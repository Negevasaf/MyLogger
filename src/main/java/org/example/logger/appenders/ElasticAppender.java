package org.example.logger.appenders;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import lombok.Getter;
import lombok.Setter;
import org.example.logger.AbstractLogger;
import org.example.logger.MyLog;

import java.io.IOException;

@Getter @Setter
public class ElasticAppender extends AbstractLogger {
    private ElasticsearchClient elasticsearchClient;
    private String esIndex;

    public ElasticAppender(ElasticsearchClient elasticsearchClient, String esIndex) {
        this.elasticsearchClient = elasticsearchClient;
        this.esIndex = esIndex;
    }
    public ElasticAppender(){}

    @Override
    protected void doLog(MyLog log) throws IOException {
        IndexResponse response = this.elasticsearchClient.index(i -> i
                .index(this.esIndex)
                .document(log));
        System.out.println(response.result());
    }

}
