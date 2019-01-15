package com.tucker.outboundengine.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;
import java.util.Set;

/**
 * @author jeffrey tucker
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleIdsResponse {

    Set<Integer> vehicleIds;

    public Set<Integer> getVehicleIds() {
        return vehicleIds;
    }

    public void setVehicleIds(Set<Integer> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleIdsResponse that = (VehicleIdsResponse) o;
        return Objects.equals(vehicleIds, that.vehicleIds);
    }

    @Override
    public int hashCode() {

        return Objects.hash(vehicleIds);
    }

    @Override
    public String toString() {
        return "VehicleIdsResponse{" +
                "vehicleIds=" + vehicleIds +
                '}';
    }
}
