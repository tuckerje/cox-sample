package com.tucker.outboundengine.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collection;
import java.util.Objects;

/**
 * @author jeffrey tucker
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Answer {

    private Collection<DealerAnswer> dealers;

    public Collection<DealerAnswer>  getDealers() {
        return dealers;
    }

    public void setDealers(Collection<DealerAnswer>  dealers) {
        this.dealers = dealers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(dealers, answer.dealers);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dealers);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "dealers=" + dealers +
                '}';
    }
}
