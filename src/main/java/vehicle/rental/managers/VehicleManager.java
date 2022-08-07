package vehicle.rental.managers;

import vehicle.rental.models.Vehicle;
import vehicle.rental.services.BookingService;
import vehicle.rental.services.BranchService;
import vehicle.rental.services.VehicleService;
import vehicle.rental.utils.RentalUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VehicleManager {

    private final BranchService branchService;
    private final VehicleService vehicleService;
    private final BookingService bookingService;

    public VehicleManager(BranchService branchService, VehicleService vehicleService, BookingService bookingService) {
        this.branchService = branchService;
        this.vehicleService = vehicleService;
        this.bookingService = bookingService;
    }

    public boolean addVehicle(String branchId, String vehicleType, String vehicleId, Integer price){
        if(branchId == null || branchId.length() == 0 || this.branchService.getBranch(branchId) == null){
            RentalUtility.printLog("Invalid Branch Id!");
            return false;
        }

        if(vehicleType == null || vehicleType.length() == 0){
            RentalUtility.printLog("Vehicle Type not specified.");
            return false;
        }

        if(vehicleId == null || vehicleId.length() == 0){
            RentalUtility.printLog("Vehicle Id not specified.");
            return false;
        }

        if(price == null || price <= 0){
            RentalUtility.printLog("Invalid price!");
            return false;
        }

        List<String> vehicleTypes = this.branchService.getVehicleTypesFromId(branchId);
        boolean isValidVehicle = branchService.validateVehicle(vehicleTypes, vehicleType);

        if(!isValidVehicle){
            RentalUtility.printLog("Vehicle not supported for branch: " + branchId);
            return false;
        }
        this.vehicleService.addVehicle(branchId, vehicleType, vehicleId, price);
        return true;
    }

    public List<String> displayAvailableVehicles(String branchId, Integer startTime, Integer endTime){

        if(branchId == null || branchId.length() == 0 || this.branchService.getBranch(branchId) == null){
            RentalUtility.printLog("Invalid Branch Id!");
            return null;
        }

        if(endTime < startTime){
            RentalUtility.printLog("Enter valid time range!");
            return null;
        }

        if(this.vehicleService.getVehiclesFromBranchId(branchId) == null){
            RentalUtility.printLog("No Vehicle available.");
            return null;
        }

        Map<String, List<Vehicle>> vehicleDetailsMap = this.vehicleService.getVehiclesFromBranchId(branchId);

        List<String> availableVehicleList = new ArrayList<>();
        for(List<Vehicle> entry : vehicleDetailsMap.values()){
            availableVehicleList.addAll(this.bookingService.populateAvailableVehicleList(entry, startTime, endTime).stream().map(Vehicle::getId).collect(Collectors.toList()));
        }
        return availableVehicleList;
    }

}
