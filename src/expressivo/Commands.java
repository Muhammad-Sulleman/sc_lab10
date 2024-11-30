package expressivo;

import java.util.Map;

public class Commands {

    /**
     * Differentiate an expression with respect to a variable.
     */
    public static String differentiate(String expression, String variable) {
        Expression expr = Expression.parse(expression);
        return differentiateHelper(expr, variable).toString();
    }

    private static Expression differentiateHelper(Expression expr, String variable) {
        if (expr instanceof Number) {
            return new Number(0);
        }
        if (expr instanceof Variable) {
            return expr.equals(new Variable(variable)) ? new Number(1) : new Number(0);
        }
        if (expr instanceof BinaryOperation) {
            BinaryOperation binOp = (BinaryOperation) expr;
            Expression left = binOp.left;
            Expression right = binOp.right;
            switch (binOp.operator) {
                case "+":
                    return new BinaryOperation("+", differentiateHelper(left, variable), differentiateHelper(right, variable));
                case "*":
                    // Apply product rule: f*g' + f'*g
                    return new BinaryOperation("+",
                            new BinaryOperation("*", left, differentiateHelper(right, variable)),
                            new BinaryOperation("*", differentiateHelper(left, variable), right));
            }
        }
        throw new IllegalArgumentException("Unsupported expression type");
    }

    /**
     * Simplify an expression.
     */
    public static String simplify(String expression, Map<String, Double> environment) {
        Expression expr = Expression.parse(expression);
        return simplifyHelper(expr, environment).toString();
    }

    private static Expression simplifyHelper(Expression expr, Map<String, Double> environment) {
        if (expr instanceof Number) {
            return expr;
        }
        if (expr instanceof Variable) {
            String name = ((Variable) expr).toString();
            if (environment.containsKey(name)) {
                return new Number(environment.get(name));
            }
            return expr;
        }
        if (expr instanceof BinaryOperation) {
            BinaryOperation binOp = (BinaryOperation) expr;
            Expression simplifiedLeft = simplifyHelper(binOp.left, environment);
            Expression simplifiedRight = simplifyHelper(binOp.right, environment);
            if (simplifiedLeft instanceof Number && simplifiedRight instanceof Number) {
                double leftVal = ((Number) simplifiedLeft).value;
                double rightVal = ((Number) simplifiedRight).value;
                switch (binOp.operator) {
                    case "+":
                        return new Number(leftVal + rightVal);
                    case "*":
                        return new Number(leftVal * rightVal);
                }
            }
            return new BinaryOperation(binOp.operator, simplifiedLeft, simplifiedRight);
        }
        throw new IllegalArgumentException("Unsupported expression type");
    }
}
