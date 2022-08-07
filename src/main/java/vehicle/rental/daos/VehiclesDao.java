package vehicle.rental.daos;

import vehicle.rental.models.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehiclesDao {

    //key is branchId and Nested key is Vehicle Type
    private Map<String, Map<String, List<Vehicle>>> vehiclesAtSpecificBranch;

    public VehiclesDao() {
        this.vehiclesAtSpecificBranch = new HashMap<>();
    }

    public void addVehicles(String branchId, String vehicleType, String vehicleId, Integer price){
        Vehicle vehicleDetails = new Vehicle(branchId, vehicleType, vehicleId, price);

        if(!vehiclesAtSpecificBranch.containsKey(branchId)){
            vehiclesAtSpecificBranch.put(branchId, new HashMap<>());
        }
        Map<String, List<Vehicle>> vehicleDetailsMap = vehiclesAtSpecificBranch.get(branchId);

        if(!vehicleDetailsMap.containsKey(vehicleType)){
            vehicleDetailsMap.put(vehicleType, new ArrayList<>());
        }
        vehicleDetailsMap.get(vehicleType).add(vehicleDetails);
    }

    public Map<String, List<Vehicle>> getVehiclesForBranchId(String branchId) {
        return vehiclesAtSpecificBranch.get(branchId);
    }
}
