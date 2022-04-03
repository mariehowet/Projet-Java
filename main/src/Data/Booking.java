package Data;

import java.util.GregorianCalendar;
import java.util.Optional;

public class Booking {
   private int id;
   private GregorianCalendar date;
   private boolean hasPaid;
   private Optional<Float> luggageWeight;
   private Optional<String> companyName;
   private String mealType;
   private Passenger passenger;
   private Seat seat;

    public Booking(int id, GregorianCalendar date, boolean hasPaid, Optional<Float> luggageWeight, Optional<String> companyName, String mealType, Passenger passenger, Seat seat) {
        this.id = id;
        this.date = date;
        this.hasPaid = hasPaid;
        this.luggageWeight = luggageWeight;
        this.companyName = companyName;
        this.mealType = mealType;
        this.passenger = passenger;
        this.seat = seat;
    }
}
