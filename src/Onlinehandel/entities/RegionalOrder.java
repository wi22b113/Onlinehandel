package Onlinehandel.entities;

import Onlinehandel.provided.Costumer;

public class RegionalOrder extends Order {

    private boolean express;

    public RegionalOrder(long id, Costumer c, Iterable<Item> items) {
        super(id, c, items);
    }

    public RegionalOrder(long id, Costumer c, Iterable<Item> items, boolean express) {
        super(id, c, items);
        this.express = express;
    }

    public int getTotal(){
        int total = 0;
        for (Item i : getItems()){
            total += i.totalValue();
        }

        if (express){
            if (getCostumer().isPremium()){
                return total;
            }else {
                total *= 1.2;
                return total;
            }
        }

        return total;
    }
}
