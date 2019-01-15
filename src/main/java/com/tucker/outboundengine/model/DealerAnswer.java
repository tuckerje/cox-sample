package com.tucker.outboundengine.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
/**
 * @author jeffrey tucker
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DealerAnswer {

    private int dealerId;

    private String name;

    private List<VehicleResponse> vehicles = new LinkedList<>();

    public int getDealerId() {
        return dealerId;
    }

    public void setDealerId(int dealerId) {
        this.dealerId = dealerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VehicleResponse> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleResponse> vehicles) {
        this.vehicles = vehicles;
    }

    public  boolean  addVehicle(VehicleResponse vehicleResponse) {

        return getVehicles().add(vehicleResponse);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DealerAnswer that = (DealerAnswer) o;
        return dealerId == that.dealerId &&
                Objects.equals(name, that.name) &&
                Objects.equals(vehicles, that.vehicles);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dealerId, name, vehicles);
    }

    @Override
    public String toString() {
        return "DealerAnswer{" +
                "dealerId=" + dealerId +
                ", name='" + name + '\'' +
                ", vehicles=" + vehicles +
                '}';
    }
}
