package vehicle.rental.services;

import vehicle.rental.models.VehicleChoiceStrategy;
import vehicle.rental.models.VehicleDetails;

import java.util.Comparator;
import java.util.List;

public class IncreasingPriceOrder implements VehicleChoiceStrategy {

    @Override
    public void applyVehicleChoiceStrategy(List<VehicleDetails> vehicleDetails) {
        vehicleDetails.sort(Comparator.comparing(VehicleDetails::getPrice));
    }
}
