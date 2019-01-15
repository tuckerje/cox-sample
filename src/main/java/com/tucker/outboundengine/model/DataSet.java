package com.tucker.outboundengine.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

/**
 * @author jeffrey tucker
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataSet {
    private String datasetId;
    private Answer answer;

    @Override
    public String toString() {
        return "DataSet{" +
                "datasetId='" + datasetId + '\'' +
                ", answer=" + answer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataSet dataSet = (DataSet) o;
        return Objects.equals(datasetId, dataSet.datasetId) &&
                Objects.equals(answer, dataSet.answer);
    }

    @Override
    public int hashCode() {

        return Objects.hash(datasetId, answer);
    }

    public String getDatasetId() {

        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
