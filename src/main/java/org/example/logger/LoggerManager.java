package org.example.logger;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.example.logger.appenders.ElasticAppender;
import org.example.logger.appenders.filesAppenders.FileAppender;
import org.example.logger.appenders.filesAppenders.logFiles.LogFilesManager;
import org.example.logger.appenders.filesAppenders.rolling.LevelRollingAppender;
import org.example.logger.appenders.filesAppenders.rolling.SizeRollingAppender;
import org.example.logger.appenders.filesAppenders.rolling.TimeRollingFileAppender;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Scanner;

public class LoggerManager {
    private HashMap configs;

    public LoggerManager(String confPath) {
        this.configs = getLoggerManagerConfig(confPath);
    }

    private HashMap getLoggerManagerConfig(String confPath) {
        try {
            StringBuilder json = new StringBuilder();
            File myObj = new File(confPath);

            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                json.append(data);
            }
            myReader.close();
            HashMap result =
                    new ObjectMapper().readValue(json.toString(), HashMap.class);
            return result;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public AbstractLogger getLogger(String loggerName) {
        HashMap loggerData = (HashMap) this.configs.get(loggerName);
        LogFilesManager logFilesManager = new LogFilesManager();
        switch (loggerName) {
            case "FileAppender":
                try {
                    return new FileAppender(logFilesManager.getCreateLogFile(FileSystems.getDefault().getPath((String) loggerData.get("path"))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            case "ElasticAppender":
                RestClient restClient = RestClient.builder(
                        new HttpHost((String) loggerData.get("host"), (Integer) loggerData.get("port"))).build();
                ElasticsearchTransport transport = new RestClientTransport(
                        restClient, new JacksonJsonpMapper());
                ElasticsearchClient client = new ElasticsearchClient(transport);
                return new ElasticAppender(client, (String) loggerData.get("index"));
            case "LevelRollingAppender":
                return new LevelRollingAppender(FileSystems.getDefault().getPath((String) loggerData.get("path")), logFilesManager);
            case "SizeRollingAppender":
                long maxSizeBytes = ((Number) loggerData.get("maxSizeBytes")).longValue();
                return new SizeRollingAppender(FileSystems.getDefault().getPath((String) loggerData.get("path")), logFilesManager, maxSizeBytes);
            case "TimeRollingFileAppender":
                long minutesToRoll = ((Number) loggerData.get("minutesToRoll")).longValue();
                return new TimeRollingFileAppender(FileSystems.getDefault().getPath((String) loggerData.get("path")), logFilesManager, minutesToRoll);
        }
        return null;
    }
}
