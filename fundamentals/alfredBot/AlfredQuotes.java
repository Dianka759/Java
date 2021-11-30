import java.util.Date;
public class AlfredQuotes {
    
    public String basicGreeting() {
        // You do not need to code here, this is an example method
        return "Hello, lovely to see you. How are you?";
    }
    
    public String guestGreeting(String name) {
        return String.format("\nHello, %s. lovely to see you!", name);
    }
    
    public String guestGreeting(String dayPeriod, String name) {
        return String.format("\n%s, %s. lovely to see you!", dayPeriod, name);
    }

    public String dateAnnouncement() {
        Date date = new Date();
        return "\nIt is currently " + date;
    }
    
    public String respondBeforeAlexis(String conversation) {
        if (conversation.indexOf("Alexis") > -1) {
        // if (conversation.contains("Alexis")) {
            return conversation;
        }
        else if (conversation.indexOf("Alfred") > -1) {
        // else if (conversation.contains("Alfred")) {
            return conversation;
        }
        else {
            return conversation;
        }
    }

    public String angeyAlfred(String angey, String name) {
        return String.format("\n%s!? %s. Go to your room!", angey, name);
    }
    
	// NINJA BONUS
	// See the specs to overload the guessGreeting method
    // SENSEI BONUS
    // Write your own AlfredQuote method using any of the String methods you have learned!
}