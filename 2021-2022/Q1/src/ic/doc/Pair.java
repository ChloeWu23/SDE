package ic.doc;

import java.util.Objects;

public class Pair<T, T1> {
    private T first;
    private T1 second;

    public Pair(T price, T1 name) {
        first=price;
        second=name;
    }

    public T getFirst(){
        return first;
    }
    public T1 getSecond(){
        return second;
    }
    public void setFirst(T first1){
        first = first1;
    }

    public void setSecond(T1 second1){
        second = second1;

    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o.getClass() != this.getClass()) return false;
        //casting object o
        Pair<?,?> pair = (Pair<?,?>)o;
    if ((first.equals(pair.first)) && second.equals(pair.second)) {
      return true;
        }
    return false;
    }

    //Function to get Hashcode using Key as <first,second> values
    @Override
    public int hashCode(){
        return Objects.hash(first,second);
    }


}
