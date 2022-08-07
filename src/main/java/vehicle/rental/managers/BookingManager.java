package vehicle.rental.managers;

import vehicle.rental.models.Vehicle;
import vehicle.rental.models.VehicleChoiceStrategy;
import vehicle.rental.services.BookingService;
import vehicle.rental.services.BranchService;
import vehicle.rental.services.VehicleService;
import vehicle.rental.utils.RentalUtility;

import java.util.List;
import java.util.Map;

public class BookingManager {

    private final BranchService branchService;
    private final VehicleService vehicleService;
    private final BookingService bookingService;

    public BookingManager(BranchService branchService, VehicleService vehicleService, BookingService bookingService) {
        this.branchService = branchService;
        this.vehicleService = vehicleService;
        this.bookingService = bookingService;
    }


    public Integer bookVehicle(String branchId, String vehicleType, Integer startTime, Integer endTime, VehicleChoiceStrategy strategy){
        if(branchId == null || branchId.length() == 0 || this.branchService.getBranch(branchId) == null){
            RentalUtility.printLog("Invalid Branch Id!");
            return -1;
        }

        if(vehicleType == null || vehicleType.length() == 0){
            RentalUtility.printLog("Vehicle Type not specified.");
            return -1;
        }

        if(endTime <= startTime){
            RentalUtility.printLog("Enter valid time range!");
            return -1;
        }

        if(this.vehicleService.getVehiclesFromBranchId(branchId) == null){
            RentalUtility.printLog("No Vehicle available.");
            return -1;
        }

        Map<String, List<Vehicle>> vehicleDetailsMap = this.vehicleService.getVehiclesFromBranchId(branchId);
        if(!vehicleDetailsMap.containsKey(vehicleType) || vehicleDetailsMap.get(vehicleType).isEmpty()){
            RentalUtility.printLog("Vehicle Type not available at branch");
            return -1;
        }

        // get specified vehicle type for mentioned branch
        List<Vehicle> vehicleDetails = vehicleDetailsMap.get(vehicleType);

        Vehicle bookedVehicle = this.bookingService.bookVehicle(vehicleDetails, startTime, endTime, strategy);

        if(bookedVehicle == null) {
            RentalUtility.printLog("Vehicle Type not available at branch.");
            return -1;
        }
        return bookedVehicle.getPrice()*(endTime-startTime);
    }
}


