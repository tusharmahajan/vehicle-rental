package vehicle.rental.utils;

public class RentalUtility {

    public static boolean enableLogs = false;
    private RentalUtility(){}

    public static void printLog(String logs){
        if(enableLogs) System.out.println(logs);
    }
}
