package Test;
import Model.*;
import Business.BusinessManager;
import Exception.*;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class Test {
    private Calculator calculator;
    private BusinessManager manager;

    @BeforeEach
    void setUp() throws ConnectionException {
        calculator = new Calculator();
        manager = new BusinessManager();
    }

    @org.junit.jupiter.api.Test
    public void add() throws FlightPriceException, SeatTypeException, PriceException {
        Double[] priceWeights = {0.0,10.0,20.0,25.0};
        Double priceExpected = 750.0 + 100.0 + 20.0;
        assertEquals(priceExpected, calculator.add(manager.getFlightPrice(1),(double)manager.getActualSeatType(7).getAdditionalPrice(),priceWeights[2]), 0.01);
    }

    // voir méthode
    @org.junit.jupiter.api.Test
    public void getSeatTypes() throws PriceException, AllSeatTypesException {
        Object [] seatTypesExpected = {new SeatType("Economic", 30),new SeatType("Business", 100), new SeatType("First", 200)};
        Object [] seatTypesActual =  manager.getAllSeatTypes().toArray();
        assertArrayEquals(seatTypesExpected, seatTypesActual);
    }

    @org.junit.jupiter.api.Test
    public void getFlightPrice() throws FlightPriceException {
        Double priceExpected = 750.0;
        Double priceActual = manager.getFlightPrice(1);
        assertEquals(priceExpected, priceActual);
    }

    @org.junit.jupiter.api.Test
    public void getActualSeat() throws SeatNumberException, ActualSeatException {
        Object seatExpected = new Seat(1,1,"A");
        Object seatActual = manager.getActualSeat(1);
        assertEquals(seatExpected, seatActual);
    }

    @org.junit.jupiter.api.Test
    public void getPassenger() throws ActualPassengerException {
        Object passengerExpected = new Passenger(1, "Parache", "Melvin", null);
        Object passengerActual = manager.getActualPassenger(1);
        assertEquals(passengerExpected, passengerActual);
    }
}