import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private final Calculator calculator = new Calculator();
    
    @Test
    void testAdd() {
        assertEquals(5.0, calculator.add(2.0, 3.0), "2 + 3 should equal 5");
        assertEquals(-1.0, calculator.add(-2.0, 1.0), "-2 + 1 should equal -1");
        assertEquals(0.0, calculator.add(0.0, 0.0), "0 + 0 should equal 0");
    }
    
    @Test
    void testSubtract() {
        assertEquals(2.0, calculator.subtract(5.0, 3.0), "5 - 3 should equal 2");
        assertEquals(-3.0, calculator.subtract(2.0, 5.0), "2 - 5 should equal -3");
        assertEquals(0.0, calculator.subtract(3.0, 3.0), "3 - 3 should equal 0");
    }
    
    @Test
    void testMultiply() {
        assertEquals(6.0, calculator.multiply(2.0, 3.0), "2 * 3 should equal 6");
        assertEquals(-6.0, calculator.multiply(-2.0, 3.0), "-2 * 3 should equal -6");
        assertEquals(0.0, calculator.multiply(5.0, 0.0), "5 * 0 should equal 0");
    }
    
    @Test
    void testDivide() {
        assertEquals(2.0, calculator.divide(6.0, 3.0), "6 / 3 should equal 2");
        assertEquals(-2.0, calculator.divide(-4.0, 2.0), "-4 / 2 should equal -2");
        assertEquals(1.0, calculator.divide(5.0, 5.0), "5 / 5 should equal 1");
    }
    
    @Test
    void testDivideByZero() {
        Exception exception = assertThrows(
            ArithmeticException.class,
            () -> calculator.divide(1.0, 0.0),
            "Division by zero should throw ArithmeticException"
        );
        assertEquals("Division by zero", exception.getMessage());
    }
}