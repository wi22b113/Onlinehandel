package Onlinehandel.util;

import Onlinehandel.entities.Order;
import Onlinehandel.provided.Matcher;

public class OnRouteMatcher implements Matcher<Order> {


    @Override
    public boolean matches(Order order) {

        if (order.isCollected()==true && order.isDelivered()== false){
            return true;
        }
        return false;
    }
}
