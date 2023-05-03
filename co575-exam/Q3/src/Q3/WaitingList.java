package Q3;

public interface WaitingList {
    void add(Customer customer, Show show, int num);

    boolean anyoneWaiting(Show show, int num);
}
