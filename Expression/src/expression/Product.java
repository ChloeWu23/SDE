package expression;

public class Product extends BinaryExpression implements Expression {
    //constructor
    public Product(Expression left, Expression right) {
        this.left = left;
        this.right = right;
        this.symbol = "*";
    }

    //specific implement methods
    public int evaluate(){
        return left.evaluate() * right.evaluate();
    }

}
