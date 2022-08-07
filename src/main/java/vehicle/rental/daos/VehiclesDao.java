package vehicle.rental.daos;

import vehicle.rental.models.VehicleDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehiclesDao {

    //key is branchId and Nested key is Vehicle Type
    private Map<String, Map<String, List<VehicleDetails>>> vehiclesAtSpecificBranch;

    public VehiclesDao() {
        this.vehiclesAtSpecificBranch = new HashMap<>();
    }

    public void addVehicles(String branchId, String vehicleType, String vehicleId, Integer price){
        VehicleDetails vehicleDetails = new VehicleDetails(branchId, vehicleType, vehicleId, price);

        if(!vehiclesAtSpecificBranch.containsKey(branchId)){
            vehiclesAtSpecificBranch.put(branchId, new HashMap<>());
        }
        Map<String, List<VehicleDetails>> vehicleDetailsMap = vehiclesAtSpecificBranch.get(branchId);

        if(!vehicleDetailsMap.containsKey(vehicleType)){
            vehicleDetailsMap.put(vehicleType, new ArrayList<>());
        }
        vehicleDetailsMap.get(vehicleType).add(vehicleDetails);
    }

    public Map<String, List<VehicleDetails>> getVehiclesForBranchId(String branchId) {
        return vehiclesAtSpecificBranch.get(branchId);
    }
}
