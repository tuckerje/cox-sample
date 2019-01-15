package com.tucker.outboundengine.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

/**
 * @author jeffrey tucker
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnswerResponse {

    private boolean success;
    private String message;
    private int totalMilliseconds;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotalMilliseconds() {
        return totalMilliseconds;
    }

    public void setTotalMilliseconds(int totalMilliseconds) {
        this.totalMilliseconds = totalMilliseconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerResponse that = (AnswerResponse) o;
        return success == that.success &&
                totalMilliseconds == that.totalMilliseconds &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(success, message, totalMilliseconds);
    }

    @Override
    public String toString() {
        return "AnswerResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", totalMilliseconds=" + totalMilliseconds +
                '}';
    }
}
