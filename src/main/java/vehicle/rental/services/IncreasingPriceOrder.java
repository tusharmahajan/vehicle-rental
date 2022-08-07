package vehicle.rental.services;

import vehicle.rental.models.VehicleChoiceStrategy;
import vehicle.rental.models.Vehicle;

import java.util.Comparator;
import java.util.List;

public class IncreasingPriceOrder implements VehicleChoiceStrategy {

    @Override
    public void applyVehicleChoiceStrategy(List<Vehicle> vehicleDetails) {
        vehicleDetails.sort(Comparator.comparing(Vehicle::getPrice));
    }
}
