package vehicle.rental;

import vehicle.rental.managers.BookingManager;
import vehicle.rental.managers.BranchManager;
import vehicle.rental.managers.VehicleManager;
import vehicle.rental.services.BookingService;
import vehicle.rental.services.BranchService;
import vehicle.rental.services.IncreasingPriceOrder;
import vehicle.rental.services.VehicleService;
import vehicle.rental.utils.RentalUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RentalSimulator {

    public static void main(String[] args) throws FileNotFoundException {

        BranchService branchService = new BranchService();
        VehicleService vehicleService = new VehicleService();
        BookingService bookingService = new BookingService();

        BranchManager branchManager = new BranchManager(branchService);
        VehicleManager vehicleManager = new VehicleManager(branchService, vehicleService, bookingService);
        BookingManager bookingManager = new BookingManager(branchService, vehicleService, bookingService);

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
                    boolean isBranchCreated = branchManager.addBranch(branchId, vehicleTypes);
                    System.out.println(isBranchCreated);
                    break;

                case "ADD_VEHICLE":
                    String vehicleType = commands[2];
                    String vehicleId = commands[3];
                    Integer vehiclePrice = Integer.valueOf(commands[4]);
                    boolean isVehicleAdded = vehicleManager.addVehicle(branchId, vehicleType, vehicleId, vehiclePrice);
                    System.out.println(isVehicleAdded);
                    break;

                case "BOOK":
                    String vehicleType1 = commands[2];
                    Integer startTime = Integer.valueOf(commands[3]);
                    Integer endTime = Integer.valueOf(commands[4]);
                    Integer priceForBooking = bookingManager.bookVehicle(branchId, vehicleType1, startTime, endTime, new IncreasingPriceOrder());
                    System.out.println(priceForBooking);
                    break;

                case "DISPLAY_VEHICLES":
                    Integer startTime1 = Integer.valueOf(commands[2]);
                    Integer endTime1 = Integer.valueOf(commands[3]);
                    List<String> availableVehicles = vehicleManager.displayAvailableVehicles(branchId, startTime1, endTime1);
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


    private void enableLogs(){
        RentalUtility.enableLogs = true;
        RentalUtility.printLog("Logs enabled.");
    }

}
