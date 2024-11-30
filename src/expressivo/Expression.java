package expressivo;

import java.util.Objects;

/**
 * An immutable data type representing a polynomial expression of:
 *   + and *
 *   nonnegative integers and floating-point numbers
 *   variables (case-sensitive nonempty strings of letters)
 */
public interface Expression {

    // Datatype definition
    // Expression ::= Number(value: double) 
    //              | Variable(name: String) 
    //              | BinaryOperation(operator: String, left: Expression, right: Expression)

    /**
     * Parse an expression.
     * @param input expression to parse, as defined in the PS3 handout.
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static Expression parse(String input) {
        // Basic recursive parser implementation
        return Parser.parse(input);
    }
    
    @Override 
    String toString();

    @Override
    boolean equals(Object thatObject);
    
    @Override
    int hashCode();
}

// Concrete implementation classes for Expression
class Number implements Expression {
    private final double value;

    public Number(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Number)) return false;
        Number other = (Number) obj;
        return Double.compare(value, other.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

class Variable implements Expression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Variable)) return false;
        Variable other = (Variable) obj;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

class BinaryOperation implements Expression {
    private final String operator;
    private final Expression left;
    private final Expression right;

    public BinaryOperation(String operator, Expression left, Expression right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " " + operator + " " + right.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BinaryOperation)) return false;
        BinaryOperation other = (BinaryOperation) obj;
        return operator.equals(other.operator) &&
               left.equals(other.left) &&
               right.equals(other.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operator, left, right);
    }
}
