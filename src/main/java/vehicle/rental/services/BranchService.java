package vehicle.rental.services;

import vehicle.rental.daos.BranchesDao;
import vehicle.rental.models.Branch;
import java.util.List;

public class BranchService {

    private final BranchesDao branchesDao;

    public BranchService() {
        this.branchesDao = new BranchesDao();
    }

    public boolean addBranch(String branchId, List<String> vehicleTypes){
        return this.branchesDao.addBranches(branchId, vehicleTypes);
    }

    public Branch getBranch(String branchId){
        return this.branchesDao.getBranch(branchId);
    }

    public List<String> getVehicleTypesFromId(String branchId){
        return this.branchesDao.getVehicles(branchId);
    }

    public boolean validateVehicle(List<String> vehicleTypes, String vehicleType) {
        return vehicleTypes.contains(vehicleType);
    }
}
