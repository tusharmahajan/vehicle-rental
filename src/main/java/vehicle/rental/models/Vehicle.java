package vehicle.rental.models;

import lombok.Getter;

@Getter
public class Vehicle {

    private final String id;
    private final String vehicleType;
    private final Integer price;
    private final String branchId;

    public Vehicle(String branchId, String vehicleType, String id, Integer price) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.price = price;
        this.branchId = branchId;
    }

}
