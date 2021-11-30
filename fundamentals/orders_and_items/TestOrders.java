public class TestOrders {
    public static void main(String[] args) {
    
    Order orders = new Order();
    Item items = new Item();

        // Menu items
        Item item1 = new Item();
            item1.name = "Mocha";
            item1.price = 3.00;
        
        Item item3 = new Item();
            item3.name = "Latte";
            item3.price = 5.00;

        Item item2 = new Item();
            item2.name = "Drip Coffee";
            item2.price = 2.50;
        
        Item item4 = new Item();
            item4.name = "Cappuccino";
            item4.price = 4.75;
    
        // Order variables -- order1, order2 etc.
        // Cindhuri’s order is now ready. Update her status.
        Order order1 = new Order();
            order1.name = "Cindhuri";
            // System.out.println(order1.total);
            order1.ready = true;

        //Add item1 to order2's item list and increment the order's total.
        //Jimmy’s order is now ready. Update his status.
        Order order2 = new Order();
            order2.name = "Jimmy";
            order2.items.add(item1);
            order2.total+=item1.price;
            order2.ready = true;

        //Add the cappuccino to their order list and to their tab(total).
        Order order3 = new Order();
            order3.name = "Noah";
            order3.items.add(item4);
            order3.total+=item4.price;

        //order4 added a latte. Update accordingly.
        //Sam ordered more drinks - 2 lattes. Update their order as well.
        Order order4 = new Order();
            order4.name = "Sam";
            order4.items.add(item3);
            order4.total += item3.price;
            order4.items.add(item3);
            order4.items.add(item3);
            order4.total += (item3.price * 2);


        // Application Simulations
        // Use this example code to test various orders' updates
        System.out.printf("Name: %s\n", order1.name);
        System.out.printf("Total: %s\n", order1.total);
        System.out.printf("Ready: %s\n", order1.ready);

        System.out.printf("Name: %s\n", order2.name);
        System.out.printf("Total: %s\n", order2.total);
        System.out.printf("Ready: %s\n", order2.ready);

        System.out.printf("Name: %s\n", order3.name);
        System.out.printf("Total: %s\n", order3.total);
        System.out.printf("Ready: %s\n", order3.ready);
    
        System.out.printf("Name: %s\n", order4.name);
        System.out.printf("Total: %s\n", order4.total);
        System.out.printf("Ready: %s\n", order4.ready);
    }
}