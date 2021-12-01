public class BaristaTest {
    public static void main(String[] args) {

        //2 orders for unspecified guests
        Order order1 = new Order();
        Order order2 = new Order();

        //Create 3 orders using the overloaded constructor to give each a name for the order
        Order order3 = new Order("Kiwi");
        Order order4 = new Order("Szczurcia");
        Order order5 = new Order("Georgie");

        // Menu
        Item item1 = new Item("drip coffee", 1.50);
        Item item2 = new Item("latte", 5.00);
        Item item3 = new Item("mocha", 4.25);
        Item item4 = new Item("cappuccino" , 3.50);

        //Add at least 2 items to each of the orders using the addItem method you wrote
        order1.addItem(item3); 
        order1.addItem(item4); 
        order2.addItem(item1); 
        order2.addItem(item4); 
        order3.addItem(item4); 
        order3.addItem(item2); 
        order4.addItem(item3); 
        order4.addItem(item3); 
        order5.addItem(item1); 
        order5.addItem(item1); 

        //Test your getStatusMessage functionality by setting some orders to ready 
        //and printing the messages for each order. 
        //For example: order2.setReady(true); System.out.println(order2.getStatusMessage());
        System.out.println("\n--------Status Message--------");
        order1.setReady(true); 
        System.out.println(order1.getStatusMessage());
        order2.setReady(false); 
        System.out.println(order2.getStatusMessage());
        order3.setReady(true); 
        System.out.println(order3.getStatusMessage());
        order4.setReady(false); 
        System.out.println(order4.getStatusMessage());
        order5.setReady(true); 
        System.out.println(order5.getStatusMessage());

        //Test the total by printing the return value like so: System.out.println(order1.getOrderTotal());
        System.out.println("\n-------Display Order------");
        order1.display();
        // System.out.println(order1.getOrderTotal());
        order2.display();
        // System.out.println(order2.getOrderTotal());
        order3.display();
        // System.out.println(order3.getOrderTotal());
        order4.display();
        // System.out.println(order4.getOrderTotal());
        order5.display();
        // System.out.println(order5.getOrderTotal());
    }
}