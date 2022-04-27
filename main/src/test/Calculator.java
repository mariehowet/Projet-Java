package test;

public class Calculator {

    public Double add(Double nb1, Double nb2) {
        return nb1 + nb2;
    }

    public Double substract(Double nb1, Double nb2) {
        return nb1 + nb2;
    }

    public Double multiply(Double nb1, Double nb2) {
        return nb1 * nb2;
    }

    public Double divide(Double nb1, Double nb2) throws ArithmeticException {
        if(nb2 == 0)
            throw new ArithmeticException(" can't divide by zero !");
        return nb1 / nb2;
    }
}
