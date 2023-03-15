package expression;

public abstract class AbstractExpression implements Expression{
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Expression){
            return (this.evaluate() == ((Expression)obj).evaluate());
        }
        return false;
    }

}
