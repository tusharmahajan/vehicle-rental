package vehicle.rental;

import vehicle.rental.managers.OperationManager;
import vehicle.rental.services.IncreasingPriceOrder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RentalSimulator {

    public static void main(String[] args) throws FileNotFoundException {
        OperationManager operationManager = new OperationManager();

        File file = new File("/Users/tusharmahajan/Downloads/vehicle-rental-test.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()){
            String command = sc.nextLine();
            String[] commands = command.split(" ");
            String commandType = commands[0];
            String branchId = commands[1];

            switch (commandType){
                case "ADD_BRANCH":
                    List<String> vehicleTypes = Arrays.asList(commands[2].split(","));
                    boolean isBranchCreated = operationManager.addBranch(branchId, vehicleTypes);
                    System.out.println(isBranchCreated);
                    break;

                case "ADD_VEHICLE":
                    String vehicleType = commands[2];
                    String vehicleId = commands[3];
                    Integer vehiclePrice = Integer.valueOf(commands[4]);
                    boolean isVehicleAdded = operationManager.addVehicle(branchId, vehicleType, vehicleId, vehiclePrice);
                    System.out.println(isVehicleAdded);
                    break;

                case "BOOK":
                    String vehicleType1 = commands[2];
                    Integer startTime = Integer.valueOf(commands[3]);
                    Integer endTime = Integer.valueOf(commands[4]);
                    Integer priceForBooking = operationManager.bookVehicle(branchId, vehicleType1, startTime, endTime, new IncreasingPriceOrder());
                    System.out.println(priceForBooking);
                    break;

                case "DISPLAY_VEHICLES":
                    Integer startTime1 = Integer.valueOf(commands[2]);
                    Integer endTime1 = Integer.valueOf(commands[3]);
                    List<String> availableVehicles = operationManager.displayAvailableVehicles(branchId, startTime1, endTime1);
                    if(availableVehicles == null) break;
                    for (String vehicle : availableVehicles){
                        System.out.println(vehicle);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
