package vehicle.rental.models;

import lombok.Getter;

@Getter
public class VehicleBookingDetails {

    private final Vehicle vehicle;
    private final int startTime;
    private final int endTime;

    public VehicleBookingDetails(Vehicle vehicle, int startTime, int endTime) {
        this.vehicle = vehicle;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
