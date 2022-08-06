package vehicle.rental.daos;

import vehicle.rental.models.VehicleBookingDetails;
import vehicle.rental.models.VehicleDetails;

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

    public void addBookingsForVehicle(VehicleDetails vehicleDetails, Integer startTime, Integer endTime){
        if(!this.bookedVehiclesDetails.containsKey(vehicleDetails.getId())){
            this.bookedVehiclesDetails.put(vehicleDetails.getId(), new ArrayList<>());
        }
        this.bookedVehiclesDetails.get(vehicleDetails.getId()).add(new VehicleBookingDetails(vehicleDetails, startTime, endTime));
    }

    public List<VehicleBookingDetails> getBookingDetailsForVehicleId(String vehicleId){
        return bookedVehiclesDetails.get(vehicleId);
    }
}
