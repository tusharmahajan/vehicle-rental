package vehicle.rental.models;

import lombok.Getter;

@Getter
public class VehicleBookingDetails {

    private final VehicleDetails vehicleDetails;
    private final int startTime;
    private final int endTime;

    public VehicleBookingDetails(VehicleDetails vehicleDetails, int startTime, int endTime) {
        this.vehicleDetails = vehicleDetails;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
