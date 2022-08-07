package vehicle.rental.managers;

import vehicle.rental.models.VehicleChoiceStrategy;
import vehicle.rental.models.VehicleDetails;
import vehicle.rental.services.BookingService;
import vehicle.rental.services.BranchService;
import vehicle.rental.services.IncreasingPriceOrder;
import vehicle.rental.services.VehicleService;
import vehicle.rental.utils.RentalUltility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class OperationManager {

    private final BranchService branchService;
    private final VehicleService vehicleService;
    private final BookingService bookingService;

    public OperationManager() {
        this.branchService = new BranchService();
        this.vehicleService = new VehicleService();
        this.bookingService = new BookingService();
    }

    public boolean addBranch(String branchId, List<String> vehicleTypes){
        if(branchId == null || branchId.length() == 0){
            RentalUltility.printLog("Invalid Branch Id!");
            return false;
        }

        if(vehicleTypes == null || vehicleTypes.isEmpty()){
            RentalUltility.printLog("No vehicles added to branch.");
            return false;
        }

        if(this.branchService.getBranch(branchId) != null){
            return false;
        }
        return this.branchService.addBranch(branchId, vehicleTypes);
    }

    public boolean addVehicle(String branchId, String vehicleType, String vehicleId, Integer price){
        if(branchId == null || branchId.length() == 0 || this.branchService.getBranch(branchId) == null){
            RentalUltility.printLog("Invalid Branch Id!");
            return false;
        }

        if(vehicleType == null || vehicleType.length() == 0){
            RentalUltility.printLog("Vehicle Type not specified.");
            return false;
        }

        if(vehicleId == null || vehicleId.length() == 0){
            RentalUltility.printLog("Vehicle Id not specified.");
            return false;
        }

        if(price == null || price <= 0){
            RentalUltility.printLog("Invalid price!");
            return false;
        }

        List<String> vehicleTypes = this.branchService.getVehicleTypesFromId(branchId);
        boolean isValidVehicle = branchService.validateVehicle(vehicleTypes, vehicleType);

        if(!isValidVehicle){
            RentalUltility.printLog("Vehicle not supported for branch: " + branchId);
            return false;
        }
        this.vehicleService.addVehicle(branchId, vehicleType, vehicleId, price);
        return true;
    }

    public Integer bookVehicle(String branchId, String vehicleType, Integer startTime, Integer endTime, VehicleChoiceStrategy strategy){
        if(branchId == null || branchId.length() == 0 || this.branchService.getBranch(branchId) == null){
            RentalUltility.printLog("Invalid Branch Id!");
            return -1;
        }

        if(vehicleType == null || vehicleType.length() == 0){
            RentalUltility.printLog("Vehicle Type not specified.");
            return -1;
        }

        if(endTime <= startTime){
            RentalUltility.printLog("Enter valid time range!");
            return -1;
        }

        if(this.vehicleService.getVehiclesFromBranchId(branchId) == null){
            RentalUltility.printLog("No Vehicle available.");
            return -1;
        }

        Map<String, List<VehicleDetails>> vehicleDetailsMap = this.vehicleService.getVehiclesFromBranchId(branchId);
        if(!vehicleDetailsMap.containsKey(vehicleType) || vehicleDetailsMap.get(vehicleType).isEmpty()){
            RentalUltility.printLog("Vehicle Type not available at branch");
            return -1;
        }

        // get specified vehicle type for mentioned branch
        List<VehicleDetails> vehicleDetails = vehicleDetailsMap.get(vehicleType);

        VehicleDetails bookedVehicle = this.bookingService.bookVehicle(vehicleDetails, startTime, endTime, strategy);

        if(bookedVehicle == null) {
            RentalUltility.printLog("Vehicle Type not available at branch.");
            return -1;
        }
        return bookedVehicle.getPrice()*(endTime-startTime);
    }

    public List<String> displayAvailableVehicles(String branchId, Integer startTime, Integer endTime){

        if(branchId == null || branchId.length() == 0 || this.branchService.getBranch(branchId) == null){
            RentalUltility.printLog("Invalid Branch Id!");
            return null;
        }

        if(endTime < startTime){
            RentalUltility.printLog("Enter valid time range!");
            return null;
        }

        if(this.vehicleService.getVehiclesFromBranchId(branchId) == null){
            RentalUltility.printLog("No Vehicle available.");
            return null;
        }

        Map<String, List<VehicleDetails>> vehicleDetailsMap = this.vehicleService.getVehiclesFromBranchId(branchId);

        List<String> availableVehicleList = new ArrayList<>();
        for(List<VehicleDetails> entry : vehicleDetailsMap.values()){
            availableVehicleList.addAll(this.bookingService.populateAvailableVehicleList(entry, startTime, endTime).stream().map(VehicleDetails::getId).collect(Collectors.toList()));
        }
        return availableVehicleList;
    }

    public void enableLogs(){
        RentalUltility.enableLogs = true;
        RentalUltility.printLog("Logs enabled.");
    }
}
