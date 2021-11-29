import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.text.*;

public class TestCafe {
    public static void main(String[] args) {
        
    CafeUtil appTest = new CafeUtil();

        /* ============ App Test Cases ============= */
    
        System.out.println("\n----- Streak Goal Test -----");
        System.out.printf("Purchases needed by specified week: %s \n\n", appTest.getStreakGoal());
    
        System.out.println("----- Order Total Test-----");
        double[] lineItems = {3.5, 1.5, 4.0, 4.5};
        System.out.printf("Order total: $%s \n\n",appTest.getOrderTotal(lineItems));
        
        System.out.println("----- Display Menu Test-----");
        List<String> loadMenu = Arrays.asList(
            "drip coffee",
            "cappucino",
            "latte",
            "mocha"
        );
        List<Double> loadPrices = Arrays.asList(1.50, 3.50, 4.50, 3.50);
        ArrayList<String> menu = new ArrayList<String>();
        ArrayList<Double> price = new ArrayList<Double>();
        menu.addAll(loadMenu);
        price.addAll(loadPrices);
        appTest.displayMenu(menu, price);
    
        System.out.println("\n----- Add Customer Test-----");
        ArrayList<String> customer = new ArrayList<String>();
        // --- Test 4 times ---
        for (int i = 0; i < 4; i++) {
            appTest.addCustomer(customer);
            System.out.println("\n");
        }

        System.out.println("----- Barista Customers Test-----");
        ArrayList<String> customers = new ArrayList<String>();
            appTest.addCustomers(customers);
            System.out.println("\n"); 

        System.out.println("----- Price Chart Test-----");
        appTest.printPriceChart("Columbian Coffee Grounds", 15.00, 3);
        appTest.printPriceChart("Broke Student Coffee", 2.0, 4);
    }
}