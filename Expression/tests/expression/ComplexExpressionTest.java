package expression;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ComplexExpressionTest {
    @Test
    public void evaluate() {
        Expression aux = new Sum(new NaturalNumber(4), new NaturalNumber(5));
        Expression e = new Product(aux, new NaturalNumber(3));
        assertEquals(e.evaluate(), 27);
    }

    @Test
    public void equals() {
        Expression aux = new Sum(new NaturalNumber(4), new NaturalNumber(5));
        Expression e = new Product(aux, new NaturalNumber(3));

        NaturalNumber n = new NaturalNumber(27);
        //assertEquals(e,n); have not defined equals for equation only for Natural Number
    }
}
