package ic.doc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuctionManager {
    private int highest = 0;
    private final Payment payment;
    private final Bidder bidder;
    private final Dispatch dispatch;
    private final Seller seller;
    private final Item item;
    Pair<Integer,String> currentBid = new Pair<>(0,null) ; //debug here
    Pair<Integer,String> highestBid = new Pair<>(0,null); //debug here
    private List<Pair> owned = new ArrayList<>();

    public AuctionManager(Payment payment, Bidder bidder, Dispatch dispatch, Seller seller, Item item) {
        this.payment = payment;
        this.bidder = bidder;
        this.dispatch = dispatch;
        this.seller = seller;
        this.item = item;
    }


    public void startAuction(Item item, Seller seller) {
    }

    public void getBid(int price, String name) {
        currentBid.setFirst(price);
        currentBid.setSecond(name);
        if(price > highest){
            highest = price;
            highestBid.setFirst(price);
            highestBid.setSecond(name);
            payment.charge(price,name);
            //owned.add(currentBid); debug here
            owned.add(new Pair<>(price,name));
            bidder.giveResult();
        }else{
            bidder.giveResult();
        }

    }

    public void endAuction() {
        payment.pay(highestBid.getFirst(),seller);

        dispatch.patchItem(item,highestBid.getSecond());


        //pay all bidders their bid amount back
        for (Pair p:owned){
            if(!p.equals(highestBid)){
                payment.pay(p.getFirst(),p.getSecond());
                //payment.pay((Integer) p.getFirst(),(String) p.getSecond());
            }


        }


    }
}
