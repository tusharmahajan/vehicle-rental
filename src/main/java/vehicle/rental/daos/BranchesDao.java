package vehicle.rental.daos;

import vehicle.rental.models.Branch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BranchesDao {

    //key is branchId
    private Map<String, Branch> availableBranches;

    public BranchesDao() {
        this.availableBranches = new HashMap<>();
    }

    public boolean addBranches(String branchId, List<String> vehicleTypes){
        this.availableBranches.put(branchId, new Branch(branchId, vehicleTypes));
        return true;
    }

    public Branch getBranch(String branchId){
        return this.availableBranches.get(branchId);
    }

    public List<String> getVehicles(String branchId){
        return this.availableBranches.get(branchId).getVehicleType();
    }

}
