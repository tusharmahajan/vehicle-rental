package vehicle.rental.services;

import vehicle.rental.daos.BookingsDao;
import vehicle.rental.models.VehicleBookingDetails;
import vehicle.rental.models.VehicleChoiceStrategy;
import vehicle.rental.models.VehicleDetails;
import vehicle.rental.utils.RentalUtility;

import java.util.ArrayList;
import java.util.List;

public class BookingService {

    private final BookingsDao bookingsDao;

    public BookingService() {
        this.bookingsDao = new BookingsDao();
    }

    public VehicleDetails bookVehicle(List<VehicleDetails> vehicleDetails, Integer startTime, Integer endTime, VehicleChoiceStrategy strategy) {
        List<VehicleDetails> availableVehiclesForBooking = this.populateAvailableVehicleList(vehicleDetails, startTime, endTime);

        if(availableVehiclesForBooking.isEmpty()){
            RentalUtility.printLog("Cannot book vehicle.");
            return null;
        }
        strategy.applyVehicleChoiceStrategy(availableVehiclesForBooking);
        this.bookingsDao.addBookingsForVehicle(availableVehiclesForBooking.get(0), startTime, endTime);
        return availableVehiclesForBooking.get(0);
    }


    public List<VehicleDetails> populateAvailableVehicleList(List<VehicleDetails> vehicleDetails,
                                                    Integer startTime, Integer endTime) {
        List<VehicleDetails> availableVehiclesForBooking = new ArrayList<>();
        for(VehicleDetails vehicle : vehicleDetails){
            // check for non-overlapping intervals
            List<VehicleBookingDetails> vehicleBookingDetails = this.bookingsDao.getBookingDetailsForVehicleId(vehicle.getId());
            if(vehicleBookingDetails != null && !vehicleBookingDetails.isEmpty()){
                for(VehicleBookingDetails slot : vehicleBookingDetails){
                    if(Math.max(slot.getStartTime(), startTime) < Math.min(slot.getEndTime(), endTime)){
                        continue;
                    }
                    availableVehiclesForBooking.add(vehicle);
                }
            }
            else {
                availableVehiclesForBooking.add(vehicle);
            }
        }
        return availableVehiclesForBooking;
    }
}
