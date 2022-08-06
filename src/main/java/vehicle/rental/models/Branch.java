package vehicle.rental.models;

import lombok.Getter;
import java.util.List;

@Getter
public class Branch {

    private final String id;
    private final List<String> vehicleType;

    public Branch(String id, List<String> vehicleType) {
        this.id = id;
        this.vehicleType = vehicleType;
    }

}
