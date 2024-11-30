package expressivo;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CommandsTest {

    @Test
    public void testDifferentiateSimpleVariable() {
        assertEquals("1.0", Commands.differentiate("x", "x"));
        assertEquals("0.0", Commands.differentiate("y", "x"));
    }

    @Test
    public void testDifferentiateAddition() {
        assertEquals("(1.0 + 0.0)", Commands.differentiate("x + y", "x"));
    }

    @Test
    public void testDifferentiateMultiplication() {
        assertEquals("((1.0 * y) + (x * 0.0))", Commands.differentiate("x * y", "x"));
    }

    @Test
    public void testSimplifyWithEnvironment() {
        assertEquals("(3.0 + 2.0)", Commands.simplify("x + 2", Map.of("x", 3.0)));
        assertEquals("6.0", Commands.simplify("x * y", Map.of("x", 2.0, "y", 3.0)));
    }

    @Test
    public void testSimplifyWithoutEnvironment() {
        assertEquals("x", Commands.simplify("x", Map.of()));
        assertEquals("(x + y)", Commands.simplify("x + y", Map.of()));
    }
}
