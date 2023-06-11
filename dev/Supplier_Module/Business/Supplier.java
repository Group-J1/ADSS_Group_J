package Supplier_Module.Business;

import Supplier_Module.Business.Agreement.Agreement;
import Supplier_Module.Business.Card.SupplierCard;
import Supplier_Module.DAO.OrderDAO;

import java.util.LinkedList;
import java.util.List;

public class Supplier {
    SupplierCard card;
    Agreement agreement;
    LinkedList<Order> order_history;

    /**constructor
     *
     */
    public Supplier(SupplierCard card, Agreement agreement, LinkedList<Order> order_history) {
        this.card = card;
        this.agreement = agreement;
        this.order_history = order_history;
    }

    /**constructor
     *
     */
    public Supplier(SupplierCard card, Agreement agreement) {
        this.card = card;
        this.agreement = agreement;
        this.order_history = new LinkedList<>();
    }

    /**
     * Getters and Setter
     */
    public LinkedList<Order> getOrder_history() {
        return order_history;
    }

    public SupplierCard getCard() {
        return card;
    }

    public Agreement getAgreement() {
        return agreement;
    }


    /** function get Order
     * the function add the order to the order history
     */
    public void addOrderToHistory(Order a)
    {
        order_history.add(a);
    }

    /**
     * function print all orders
     */
    public void PrintOrders()
    {
        List<Order> ans=OrderDAO.getInstance().getAllHistoricOrdersBySupplierID(this.card.getSupplier_number());
        if(ans.isEmpty())
            System.out.println("There is no orders for this supplier");
        for(Order order : ans)
            order.PrintOrder();
//        for(int i=0;i<this.order_history.size(); i++){
//            this.order_history.get(i).PrintOrder();
//        }
    }
    /**
     * function print supplier detailes
     */
    public void PrintSupplierDetailes()
    {
        this.card.printCard();
        this.agreement.printAgreement();
    }

}
