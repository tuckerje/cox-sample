package com.tucker.outboundengine;

import com.tucker.outboundengine.model.*;
import com.tucker.outboundengine.service.SampleRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@SpringBootApplication
public class OutboundengineSampleApplication {
    private static final Logger log = LoggerFactory.getLogger(OutboundengineSampleApplication.class);


    @Bean
    public String getBaseApiUrl() {
        return "http://vautointerview.azurewebsites.net/api/";
    }

    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public SampleRestClient getSampleService(String baseApiUrl, RestTemplate restTemplate) {
        return new SampleRestClient(restTemplate, baseApiUrl);
    }

    @Bean
    public CommandLineRunner run(SampleRestClient sampleRestClient) throws Exception {
        return args -> {
            try {
                DataSet dataSet = sampleRestClient.getDataSet();
                log.info(dataSet.toString());
                AnswerResponse answerResponse = sampleRestClient.postAnswer(dataSet.getDatasetId(), dataSet.getAnswer());
                log.info(answerResponse.toString());
            } catch (Exception e) {
                log.error("Failed to get data set and post back to server ", e);
            }
        };
    }

    public static void main(String args[]) {
        SpringApplication.run(OutboundengineSampleApplication.class);
    }
}

