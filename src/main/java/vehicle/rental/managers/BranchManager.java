package vehicle.rental.managers;

import vehicle.rental.services.BranchService;
import vehicle.rental.utils.RentalUtility;

import java.util.List;

public class BranchManager {

    private final BranchService branchService;

    public BranchManager(BranchService branchService) {
        this.branchService = branchService;
    }

    public boolean addBranch(String branchId, List<String> vehicleTypes){
        if(branchId == null || branchId.length() == 0){
            RentalUtility.printLog("Invalid Branch Id!");
            return false;
        }

        if(vehicleTypes == null || vehicleTypes.isEmpty()){
            RentalUtility.printLog("No vehicles added to branch.");
            return false;
        }

        if(this.branchService.getBranch(branchId) != null){
            return false;
        }
        return this.branchService.addBranch(branchId, vehicleTypes);
    }

}
