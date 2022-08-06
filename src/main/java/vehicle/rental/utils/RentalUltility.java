package vehicle.rental.utils;

public class RentalUltility {

    public static boolean enableLogs = false;
    private RentalUltility(){}

    public static void printLog(String logs){
        if(enableLogs) System.out.println(logs);
    }
}
