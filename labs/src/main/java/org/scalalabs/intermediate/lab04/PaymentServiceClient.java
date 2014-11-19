package org.scalalabs.intermediate.lab04;


import scala.Function2;
import scala.Predef$;
import scala.collection.immutable.*;
import scala.collection.immutable.$colon$colon;
import scala.runtime.AbstractFunction2;

import java.util.Date;
 
public class PaymentServiceClient {

    public void cachePayment(String userId, int value) {
        Order order = new Order(userId, new Cache(), value);
        List<Order> orders = List$.MODULE$.apply(Predef$.MODULE$.wrapRefArray(new Order[]{
                order
        }));
        PaymentService.pay(orders);
 
    } 

    public void cardPayment(String userId, int value, Date date) {
        PaymentCard card = PaymentCard$.MODULE$.apply(date, userId);
        Order order = new Order(userId, card, value);

        List<Order> orders = Nil$.MODULE$.$colon$colon(order);
        PaymentService.pay(orders);
    }

    public void resetState(){
        PaymentService.reset();
    }

    public void setVerboseLogMode(boolean mode){
        PaymentService$.MODULE$.verboseLogMode_$eq(mode);
    }

    public boolean isVerboseLogMode(){
        return PaymentService.verboseLogMode();
    }

    public List<Order> findAllOrders() {
        return PaymentService.getHistory();

    }

    public void voucherPayment(String userId, int value) {
       Order order = new Order(userId, new GiftVoucher(userId), value);
        List<Order> orders = Nil$.MODULE$.$colon$colon(order);
        PaymentService.pay(orders);
    }

}

class GiftVoucher implements Belongs, PaymentMethod {

    String holderName;

    GiftVoucher(String holderName) {
        this.holderName = holderName;
    }

    @Override
    public String holderName() {
        return holderName;
    }

    @Override
    public String firstName() {
        return holderName().split(" ")[0];
    }
}
