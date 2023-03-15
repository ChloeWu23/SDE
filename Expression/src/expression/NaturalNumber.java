package expression;

public class NaturalNumber extends AbstractExpression implements Expression{
    private final int value;
//in java the constructor does not return anything
    public  NaturalNumber(int value ){
        if (value <= 0){
            throw  new IllegalArgumentException();
        }
        this.value = value;
    }

    @Override //override a method of super class
    public String toString(){
        return String.valueOf(value);
    }

    /*
    @Override
    public boolean equals(Object x){
        if (x instanceof NaturalNumber){
            NaturalNumber n = (NaturalNumber) x;
            return (n.value == this.value);
        }
        return false;
    }
    */


    @Override
    public int evaluate(){
        return value;
    }



}
