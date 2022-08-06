package vehicle.rental.services;

import vehicle.rental.models.VehicleDetails;

import java.util.Comparator;

public class IncreasingPriceOrder implements Comparator<VehicleDetails> {

    @Override
    public int compare(VehicleDetails o1, VehicleDetails o2) {
        return o1.getPrice().compareTo(o2.getPrice());
    }

}
