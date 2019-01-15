package com.tucker.outboundengine.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;
/**
 * @author jeffrey tucker
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatasetIdResponse {
    private String datasetId;

    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

    @Override
    public String toString() {
        return "DatasetIdResponse{" +
                "datasetId='" + datasetId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatasetIdResponse dataSetId1 = (DatasetIdResponse) o;
        return Objects.equals(datasetId, dataSetId1.datasetId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(datasetId);
    }
}
