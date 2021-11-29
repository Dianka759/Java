public class CafeJava {
    public static void main(String[] args) {
        // APP VARIABLES
        // Lines of text that will appear in the app. 
        String generalGreeting = "Welcome to Cafe Java, ";
        String pendingMessage = ", your order will be ready shortly";
        String readyMessage = ", your order is ready";
        String displayTotalMessage = "Your total is $";
        String displayTotalOwedMessage = "You owe $";

        // Menu variables (add yours below)
        double mochaPrice = 3.5;
        double dripPrice = 2.0;
        double lattePrice = 5.0;
        double frappucinoPrice = 6.5;
    
        // Customer name variables (add yours below)
        String customer1 = "Cindhuri";
        String customer2 = "Sam";
        String customer3 = "Jimmy";
        String customer4 = "Noah";
    
        // Order completions (add yours below)
        boolean isReadyOrder1 = false;
        boolean isReadyOrder2 = false;
        boolean isReadyOrder3 = false;
        boolean isReadyOrder4 = true;
    
        // APP INTERACTION SIMULATION (Add your code for the challenges below)
        // Example:
        System.out.println(generalGreeting + customer1); // Displays "Welcome to Cafe Java, Cindhuri"
        System.out.println(customer1 + pendingMessage);

        // Noah's order
        System.out.println("Noah's Order:");
        if (isReadyOrder4 == true){
        System.out.println(customer4 + readyMessage);
        System.out.println(displayTotalMessage + frappucinoPrice);
        }
        else { 
        System.out.println(customer4 + pendingMessage);
        }

        // Sam's order 
        System.out.println("Sam's Order:");
        System.out.println(displayTotalMessage + (lattePrice + lattePrice));
        if (isReadyOrder2 == true) {
        System.out.println(customer2 + readyMessage);
        }
        else {
        System.out.println(customer2 + pendingMessage);
        }

        // Jimmy's order
        System.out.println("Jimmy's Order:");
        System.out.println(displayTotalOwedMessage + (lattePrice - dripPrice));
    }
}