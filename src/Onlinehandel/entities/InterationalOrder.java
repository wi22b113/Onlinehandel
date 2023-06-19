package Onlinehandel.entities;

import Onlinehandel.provided.Costumer;

public class InterationalOrder extends Order {
    private float custom;

    public InterationalOrder(long id, Costumer c, Iterable<Item> items) {
        super(id, c, items);
    }

    public InterationalOrder(long id, Costumer c, Iterable<Item> items, float custom) {
        super(id, c, items);
        if (custom < 1 ) {
            this.custom = 1;
        }else this.custom = custom;
    }

    public int getTotal(){
        int total = 0;

        if (getItems() == null){
            return -1;
        }
        for (Item i : getItems()){
           total += i.totalValue();
        }
        return (int) (total * custom);
    }

//    @Override
//    public String toString() {
//        return "InterationalOrder{" +
//                "custom=" + custom +
//                '}';
//    }
}
