import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.text.*;

public class CafeUtil {
    public int getStreakGoal() {
        System.out.println("Please enter number of weeks:");
        String numWeeks = System.console().readLine();
        int weeks = Integer.parseInt(numWeeks);
        int sum = 0;
        for (int i = 1; i <= weeks; i++){
            sum = sum + i;
        }
        return sum;
    }

    public double getOrderTotal(double[] price) {
        double sum = 0;
        for (int i = 0; i < price.length; i++) {
            sum += price[i];
        }
        return sum;
    }

    public void displayMenu(ArrayList<String> menuItems) {
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println(i + " " + menuItems.get(i));
        }
    }

    public boolean displayMenu(ArrayList<String> menuItems, ArrayList<Double> price) {
        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.size() != price.size()) {
                System.out.println("arrays are not equal in size");
                return false;
            } else {
            DecimalFormat twoPlaces = new DecimalFormat("0.00");
            System.out.println(i + " " + menuItems.get(i) + " -- $" + twoPlaces.format(price.get(i)));
            }
        }
        return true;
    }

    public void addCustomer(ArrayList<String> customer){
        System.out.println("Please enter your name:");
        String userName = System.console().readLine();
        System.out.printf("Hellowz, %s!", userName);
        System.out.printf(" \n There are %s people in front of you. \n", customer.size());
        customer.add(userName);
        System.out.println(customer);
    }

    public void addCustomers(ArrayList<String> customers){
        while (!customers.contains("q")) {
            System.out.println("\nPlease enter a customer's name or 'q/Q' to quit:");
            String name = System.console().readLine();
            if (name.equals("q") || name.equals("Q")) {
                System.out.println("\n  'kay Bye!");
                break;
            }
            else if (name.equals("")){
                System.out.println("Please enter a name! Emptiness is not an option.");
            }
            else {
                System.out.printf("Name added: %s. \n", name);
                customers.add(name);
                System.out.println(customers);
            }
        }
    }

    public void printPriceChart(String product, double price, int maxQuantity) { 
        System.out.println("\n" + product);
        DecimalFormat twoPlaces = new DecimalFormat("0.00");
        double discount = .50;
        for (int i = 1; i <= maxQuantity; i++) {
            if ( i == 1 ) {
                double output = price * i;
                System.out.println(i + " - $" + twoPlaces.format(output));
            }
            else { 
                double output = (price * i - discount);
                System.out.println(i + " - $" + twoPlaces.format(output));
                discount += .50;
            }
        }
    }
}