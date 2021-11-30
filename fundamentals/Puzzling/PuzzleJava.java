import java.util.*;
import java.util.Random;
import java.util.ArrayList;

public class PuzzleJava{

    // Write a method that will generate and return an array with 10 random numbers 
    // between 1 and 20 inclusive. 
    public ArrayList<Integer> getTenRolls() {
        ArrayList<Integer> random_nums = new ArrayList<Integer>();
        Random rando_num = new Random();

        for (int i = 1; i <= 10; i++) {
            random_nums.add(rando_num.nextInt(20) + 1); //(0,20 exclusive, adding one to make it inclusive)
        }
        return random_nums;
    }

    // Create an array within the method that contains all 26 letters of the alphabet (this array must have 26 values). 
    // Generate a random index between 0-25, and use it to pull a random letter out of the array.
    // Return the random letter.
    public String getRandomLetter() {
        Random rando_num = new Random();  

    //     String[] alphabet_arr = new String[26]; //initialize an array with fixed length of 26
    //     String alphabet = "abcdefghijklmnopqrstuvwxyz";

    //     for (int i = 0; i < 26; i++) {
    //         alphabet_arr[i] = String.valueOf(alphabet.charAt(i)); //get out that particular letter at that index
    //     }
    //     return alphabet_arr[rando_num.nextInt(26)];

        String[] letters = {"a","b","c","d","e","f","g","h",
                        "i","j","k","l","m","n","o","p","q",
                        "r","s","t","u","v","w","x","y","z"};
        int letter = rando_num.nextInt(26);
        return letters[letter];
    }

    // Write a method that uses the previous method to create a random string of eight characters, 
    // and return that string.
    public String randomPassword() {
        String password = "";
        for (int i = 1; i <= 8; i++) {
            password += getRandomLetter();
        }
        return password;
    }

    // Write a method that takes an int length as an argument and 
    // creates an array of random eight character words. 
    // The array should be the length passed in as an int.
    public ArrayList<String>  getNewPasswordSet(int value){
        ArrayList<String> passwordSet = new ArrayList<String>();
        for(int i = 0; i < value; i++){
            passwordSet.add(randomPassword());
        }
        return passwordSet;
    }

    // Write a method that takes an array and and mixes up all the values in a pseudo-random way.
    // Hint: use random indexes within the array, and swap values repeatedly. 
    public void shuffleArray(int[] arr){
        Random rando_num = new Random(); 
        for(int i = 0; i < arr.length; i++){
            int swap = rando_num.nextInt(arr.length);
            int temp = arr[swap];
            arr[swap] = arr[i];
            arr[i] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }
}