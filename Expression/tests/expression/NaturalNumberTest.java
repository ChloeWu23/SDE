package expression;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NaturalNumberTest {
    @Test //add @Test in order to run this test seperately.
    public void testToString() {
        //System.out.println("Creating a natural number...");
        NaturalNumber n = new NaturalNumber(5);
        //System.out.println("Priting the natural number");
        //System.out.println(n); //actually call the value of method on object n, which is to_string on object n.
        assertEquals(n.toString(), "5");
    }

    @Test
    public void testEquals() {
        NaturalNumber n1 = new NaturalNumber(5);
        NaturalNumber n2 = new NaturalNumber(5);
        NaturalNumber n3 = new NaturalNumber(4);
        /*if (!n1.equals(n2)) {
            fail();
        }*/
        assertEquals(n1,n2);
        assertNotEquals(n2,n3);
    }


}
