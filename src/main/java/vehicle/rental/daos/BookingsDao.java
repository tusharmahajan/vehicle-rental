package vehicle.rental.daos;

import vehicle.rental.models.VehicleBookingDetails;
import vehicle.rental.models.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingsDao {

    // key is VehicleId
    private Map<String, List<VehicleBookingDetails>> bookedVehiclesDetails;

    public BookingsDao() {
        this.bookedVehiclesDetails = new HashMap<>();
    }

    public void addBookingsForVehicle(Vehicle vehicle, Integer startTime, Integer endTime){
        if(!this.bookedVehiclesDetails.containsKey(vehicle.getId())){
            this.bookedVehiclesDetails.put(vehicle.getId(), new ArrayList<>());
        }
        this.bookedVehiclesDetails.get(vehicle.getId()).add(new VehicleBookingDetails(vehicle, startTime, endTime));
    }

    public List<VehicleBookingDetails> getBookingDetailsForVehicleId(String vehicleId){
        return bookedVehiclesDetails.get(vehicleId);
    }
}
