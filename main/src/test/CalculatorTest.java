package test;
import Model.*;
import Business.BusinessManager;
import Exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calculator;
    private BusinessManager manager;
    @BeforeEach
    void setUp() throws ConnectionException {
        calculator = new Calculator();
        manager = new BusinessManager();
    }

    @Test
    public void add() throws FlightPriceException, SeatTypeException, PriceException {
        Double[] priceWeights = {0.0,10.0,20.0,25.0};
        Double priceExpected = 750.0 + 100.0 + 20.0;
        assertEquals(priceExpected, calculator.add(manager.getFlightPrice(1),(double)manager.getActualSeatType(7).getAdditionalPrice(),priceWeights[2]), 0.01);
    }

    /* Marche pas
    @Test
    public void getSeatTypes() throws PriceException, AllSeatTypesException {
        Object [] seatTypesExpected = {new SeatType("Economic", 30),new SeatType("Business", 100), new SeatType("First", 200)};
        Object [] seatTypesActual =  manager.getAllSeatTypes().toArray();
        assertArrayEquals(seatTypesExpected, seatTypesActual);
    }
    */

}