package expression;

public abstract class BinaryExpression extends AbstractExpression implements Expression {
    protected Expression right;
    protected Expression left;
    protected String symbol;

    @Override
    public String toString() {
        return "(" + left + " " + symbol + " " + right + ")";
    }
}
