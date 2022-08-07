package vehicle.rental.models;

import java.util.List;

public interface VehicleChoiceStrategy {

    public void applyVehicleChoiceStrategy(List<VehicleDetails> vehicleDetails);
}
