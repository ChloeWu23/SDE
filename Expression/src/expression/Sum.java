package expression;

public class Sum extends BinaryExpression implements Expression{

    public Sum(Expression left, Expression right){
        this.left = left;
        this.right = right;
        this.symbol = "+";
    }

    public int evaluate(){
        return left.evaluate() + right.evaluate();
    }
}

