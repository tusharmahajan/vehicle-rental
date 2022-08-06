package vehicle.rental.services;

import vehicle.rental.daos.VehiclesDao;
import vehicle.rental.models.VehicleDetails;

import java.util.List;
import java.util.Map;

public class VehicleService {

    private final VehiclesDao vehiclesDao;

    public VehicleService() {
        this.vehiclesDao = new VehiclesDao();
    }

    public void addVehicle(String branchId, String vehicleType, String vehicleId, Integer price){
        this.vehiclesDao.addVehicles(branchId, vehicleType, vehicleId, price);
    }

    public Map<String, List<VehicleDetails>> getVehiclesFromBranchId(String branchId){
        return this.vehiclesDao.getVehiclesForBranchId(branchId);
    }
}
