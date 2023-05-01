package Q3;

public class BoxOffice {
    private final Theatre theatre;
    private final WaitingList waitingList;
    private final Payments payments;

    public BoxOffice(Theatre theatre, WaitingList waitingList, Payments payments) {
        this.theatre = theatre;
        this.waitingList = waitingList;
        this.payments = payments;
    }

    public void bookTickets(Show show, int num, Customer customer) {
        if(theatre.checkAvaiibility(show,num)){
            payments.makepayments(show.price()*num,customer);
            theatre.confirm(show,num);
        } else{
            waitingList.add(customer,show,num);
        }

    }


    public void returnTickets(Show show, int num) {
       waitingList.anyoneWaiting(show,num);
    }

    //overloading method for book tickets,change parameter sequence this time no need to check avalibility now
    public void bookTickets(int num, Customer customer,Show show){
        payments.makepayments(num*show.price(),customer);
    }

}
