package com.tucker.outboundengine.service;

import com.tucker.outboundengine.model.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author jeffrey tucker
 */
public class SampleRestClient {

    private RestTemplate restTemplate;
    private String apiBaseUrl;
    private volatile Map<Integer, DealerAnswer> dealerId2Dealer = new HashMap<>();

    public SampleRestClient(RestTemplate restTemplate, String apiBaseUrl) {
        this.restTemplate = restTemplate;
        this.apiBaseUrl = apiBaseUrl;
    }

    public synchronized DataSet getDataSet() throws Exception {
        dealerId2Dealer.clear();
        DataSet dataSet = new DataSet();
        // Generate data set and get id
        String dataSetId = this.getDataSetId();
        dataSet.setDatasetId(dataSetId);
        // get vehicle ids of all vehicles in the data set
        Set<Integer> vehicleIds = this.getVehicleIds(dataSetId);
        // get vehicle details of all vehicles in the data set, then vehicle details to their respective dealerships
        vehicleIds.parallelStream().forEach(v -> this.getVehicleInformation(dataSetId, v));
        Answer answer = new Answer();
        answer.setDealers(dealerId2Dealer.values());
        dataSet.setAnswer(answer);
        return dataSet;
    }


    /**
     * retrieves a datasetID
     * @return DatasetIdResponse id
     * @throws Exception service exception
     */

    public String getDataSetId() throws Exception {
        String endPointUrl = apiBaseUrl + "datasetId";
        DatasetIdResponse dataSetIdResponse =  this.restTemplate.getForObject(endPointUrl, DatasetIdResponse.class);
        if (dataSetIdResponse == null) throw new Exception("Failed to get data set id response from server");
        return dataSetIdResponse.getDatasetId();
    }

    /**
     * posts the answer to the coding challenge back to the server
     * @param datasetId given id of generated data set
     * @param dataSet answer to coding challenge
     * @return instance of {@link AnswerResponse}
     * @throws Exception service exception
     */

    public AnswerResponse postAnswer(String datasetId, Answer dataSet) throws Exception {
        String endPointUrl = apiBaseUrl + datasetId + "/answer";
        AnswerResponse answerResponse = this.restTemplate.postForObject(endPointUrl, dataSet, AnswerResponse.class);
        if (answerResponse == null) throw new Exception("Failed to post answer back to server");
        return answerResponse;
    }

    /**
     * gets the actual answer from the server for the generated data set
     * @param datasetId id of generated data set
     * @return instance of {@link Answer}
     * @throws Exception service exception
     */

    public Answer getCheatAnswer(String datasetId) throws Exception {
        String endPointUrl = apiBaseUrl + datasetId + "/cheat";
        Answer cheatAnswer = this.restTemplate.getForObject(endPointUrl, Answer.class);
        if (cheatAnswer == null) throw new Exception("Failed to get cheat answer from server");
        return cheatAnswer;

    }

    /**
     * gets the dealer information from the server for the generated data set
     * @param datasetId id of generated data set
     * @param dealerId id of dealer
     * @return instance of {@link DealerAnswer}
     */

    public DealerAnswer getDealer(String datasetId, int dealerId) {
        String endPointUrl = apiBaseUrl + datasetId + "/dealers/" + dealerId;
        DealerAnswer dealer = this.restTemplate.getForObject(endPointUrl, DealerAnswer.class);
        //dealer.setVehicles(dealerIds2Vehicles.get(dealerId));
        return dealer;
    }

    /**
     * gets the set of unique vehicle ids from the server for the generated data set
     * @param datasetId id of generated data set
     * @return instance of {@link VehicleIdsResponse}
     * @throws Exception service exception
     */

    public Set<Integer> getVehicleIds(String datasetId) throws Exception {
        String endPointUrl = apiBaseUrl + datasetId + "/vehicles";
        VehicleIdsResponse vehicleIdsResponse = this.restTemplate.getForObject(endPointUrl, VehicleIdsResponse.class);
        if (vehicleIdsResponse == null) throw new Exception("Failed to get dealer response from server");
        return vehicleIdsResponse.getVehicleIds();
    }

    /**
     * gets the details of a given vehicles from the server for the generated data set
     * @param datasetId id of generated data set
     * @param vehicleId id of vehicle
     * @return  instance of {@link VehicleResponse}
     */

    private VehicleResponse getVehicleInformation(String datasetId, int vehicleId){
        String endPointUrl = apiBaseUrl + datasetId + "/vehicles/" + vehicleId;
        VehicleResponse vehicleResponse = this.restTemplate.getForObject(endPointUrl, VehicleResponse.class);
        int dealerId = vehicleResponse.getDealerId();
        putIfAbsent(datasetId, dealerId, vehicleResponse);
        return vehicleResponse;
    }

    /**
     * The volatile dealerId 2 dealer map is synchronized here to guarantee accuracy on the dealers and the vehicle members
     * @param datasetId id of generated data set
     * @param dealerId id of dealer
     * @param vehicleResponse instance of {@link VehicleResponse}
     */

    private void putIfAbsent(String datasetId, int dealerId, VehicleResponse vehicleResponse){
        synchronized (dealerId2Dealer) {
            if (!dealerId2Dealer.containsKey(dealerId)) {
                dealerId2Dealer.put(dealerId, getDealer(datasetId, dealerId));
            }
            dealerId2Dealer.get(dealerId).addVehicle(vehicleResponse);
        }
    }

}
