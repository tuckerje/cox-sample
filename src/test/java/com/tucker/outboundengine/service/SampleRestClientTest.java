package com.tucker.outboundengine.service;

import com.tucker.outboundengine.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SampleRestClientTest {
    @Mock
    private RestTemplate restTemplate;

    private SampleRestClient sampleRestClient;

    private String apiBaseUrlFixture =  "Test API Url ";

    private String dataSetIdFixture = "Test Data Set ID";

    @Before
    public void setUp() throws Exception {
        sampleRestClient = new SampleRestClient(restTemplate, apiBaseUrlFixture);
    }

    @Test
    public void getDataSet() {
    }


    @Test
    public void getDataSetId() throws Exception {
        // Setup
        String endPointFixture = apiBaseUrlFixture + "datasetId";
        Class<DatasetIdResponse> clazz = DatasetIdResponse.class;
        DatasetIdResponse responseFixture = new DatasetIdResponse();
        responseFixture.setDatasetId(dataSetIdFixture);
        when(restTemplate.getForObject(anyString(), any())).thenReturn(responseFixture);

        //execute
        String idActual = sampleRestClient.getDataSetId();

        //verify
        verify(restTemplate, times(1)).getForObject(endPointFixture, clazz);
        Assert.assertEquals(dataSetIdFixture, idActual);

    }

    @Test
    public void postAnswer() throws Exception {
        // Setup
        AnswerResponse responseFixture = new AnswerResponse();
        Answer answer = new Answer();
        answer.setDealers(new LinkedList<>());
        String endPointFixture = apiBaseUrlFixture +  dataSetIdFixture + "/answer";
        Class<AnswerResponse> clazz = AnswerResponse.class;
        when(restTemplate.postForObject(anyString(), any(), any())).thenReturn(responseFixture);

        //execute
        AnswerResponse answerResponseActual = sampleRestClient.postAnswer(dataSetIdFixture, answer);

        //verify
        verify(restTemplate, times(1)).postForObject(endPointFixture, answer, clazz);
        Assert.assertEquals(responseFixture, answerResponseActual);
    }

    @Test
    public void getCheatAnswer() throws Exception {
        // Setup
        Answer answerFixture = new Answer();
        answerFixture.setDealers(new LinkedList<>());
        String endPointFixture = apiBaseUrlFixture + dataSetIdFixture  + "/cheat";
        Class<Answer> clazz = Answer.class;
        when(restTemplate.getForObject(anyString(), any())).thenReturn(answerFixture);

        //execute
        Answer answerActual = sampleRestClient.getCheatAnswer(dataSetIdFixture);

        //verify
        verify(restTemplate, times(1)).getForObject(endPointFixture, clazz);
        Assert.assertEquals(answerFixture, answerActual);
    }

    @Test
    public void getDealer() {
        // Setup
        int dealerId = 42;
        String endPointFixture = apiBaseUrlFixture + dataSetIdFixture + "/dealers/" + dealerId;
        DealerAnswer dealerAnswerFixture =new DealerAnswer();
        Class<DealerAnswer> clazz = DealerAnswer.class;
        when(restTemplate.getForObject(anyString(), any())).thenReturn(dealerAnswerFixture);

        //execute
        DealerAnswer dealerAnswerActual = sampleRestClient.getDealer(dataSetIdFixture, dealerId);

        //verify
        verify(restTemplate, times(1)).getForObject(endPointFixture, clazz);
        Assert.assertEquals(dealerAnswerFixture, dealerAnswerActual);
    }

    @Test
    public void getVehicleIds() throws Exception {
        // Setup
        Set<Integer> idsFixture = new HashSet<>();
        String endPointFixture = apiBaseUrlFixture + dataSetIdFixture + "/vehicles";
        VehicleIdsResponse vehicleIdsResponse = new VehicleIdsResponse();
        vehicleIdsResponse.setVehicleIds(idsFixture);
        Class<VehicleIdsResponse> clazz = VehicleIdsResponse.class;
        when(restTemplate.getForObject(anyString(), any())).thenReturn(vehicleIdsResponse);

        //execute
        Set<Integer> idsActual = sampleRestClient.getVehicleIds(dataSetIdFixture);

        //verify
        verify(restTemplate, times(1)).getForObject(endPointFixture, clazz);
        Assert.assertEquals(idsFixture, idsActual);
    }

    @Test @Ignore
    public void getVehicleInformation() {
/*        // Setup
        int vehicleIdFixture = 42;
        VehicleResponse vehicleFixture = new VehicleResponse();
        vehicleFixture.setVehicleId(vehicleIdFixture);
        String endPointFixture = apiBaseUrlFixture + dataSetIdFixture + "/vehicles/" + vehicleIdFixture;
        Class<VehicleResponse> clazz = VehicleResponse.class;
        when(restTemplate.getForObject(anyString(), any())).thenReturn(vehicleFixture);

        //execute
        VehicleResponse vehicleActual = sampleRestClient.getVehicleInformation(dataSetIdFixture, vehicleIdFixture, new HashMap<>());

        //verify
        verify(restTemplate, times(1)).getForObject(endPointFixture, clazz);
        Assert.assertEquals(vehicleFixture, vehicleActual);*/
    }
}