package expressivo;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionTest {

    @Test
    public void testNumberEquality() {
        Expression num1 = new Number(5.0);
        Expression num2 = new Number(5.0);
        Expression num3 = new Number(3.0);
        assertEquals(num1, num2);
        assertNotEquals(num1, num3);
    }

    @Test
    public void testVariableEquality() {
        Expression var1 = new Variable("x");
        Expression var2 = new Variable("x");
        Expression var3 = new Variable("y");
        assertEquals(var1, var2);
        assertNotEquals(var1, var3);
    }

    @Test
    public void testBinaryOperationToString() {
        Expression expr = new BinaryOperation("+", new Variable("x"), new Number(2.0));
        assertEquals("(x + 2.0)", expr.toString());
    }

    @Test
    public void testComplexExpressionEquality() {
        Expression expr1 = new BinaryOperation("+", new Variable("x"), new Number(2.0));
        Expression expr2 = new BinaryOperation("+", new Variable("x"), new Number(2.0));
        Expression expr3 = new BinaryOperation("+", new Variable("y"), new Number(2.0));
        assertEquals(expr1, expr2);
        assertNotEquals(expr1, expr3);
    }

    @Test
    public void testHashCodeConsistency() {
        Expression expr1 = new BinaryOperation("+", new Variable("x"), new Number(2.0));
        Expression expr2 = new BinaryOperation("+", new Variable("x"), new Number(2.0));
        assertEquals(expr1.hashCode(), expr2.hashCode());
    }
}
