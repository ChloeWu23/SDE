package Q3;

public class BoxOffice {
    private Theatre theatre;
    private Payment payment;
    private WaitingList waitingList;

    public BoxOffice(Theatre theatre, Payment payment, WaitingList waitingList) {
        this.theatre = theatre;
        this.payment = payment;
        this.waitingList = waitingList;
    }

    public void bookTickets(Show show, int num, Customer customer) {
        if(theatre.checkAvailable(show,num)){
            payment.pay(show.price()*num,customer);
            theatre.confirm(show,num);
        } else{
            waitingList.add(customer,show,num);
        }

    }

    public void returnTickets(Show show, int num) {
        waitingList.anyoneWaiting(show,num);
    }

    public void bookTickets(Customer customer,Show show,int num){
        payment.pay(show.price()*num,customer);
    }
}
