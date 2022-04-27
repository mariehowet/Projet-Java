package test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calculator;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @org.junit.jupiter.api.Test
    void add() {
        assertEquals(1000.0, calculator.add(900.0,100.0),0.01);
    }

    @org.junit.jupiter.api.Test
    void substract() {
        assertEquals(800.0, calculator.substract(900.0,100.0),0.01);
    }

    @org.junit.jupiter.api.Test
    void multiply() {
        assertEquals(200.0, calculator.multiply(100.0,2.0),0.01);
    }

    @org.junit.jupiter.api.Test
    void divide() {
        assertEquals(50.0, calculator.divide(100.0,2.0),0.01);
    }

    public void divideException() {
        assertThrows(ArithmeticException.class, () -> {
            calculator.divide(900.0,0.0);
        });
    }
}