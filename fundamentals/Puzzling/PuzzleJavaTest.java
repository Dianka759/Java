import java.util.ArrayList;
import java.util.Random;

public class PuzzleJavaTest{
    public static void main(String[] args){

	System.out.println("\n10 random numbers range 1-20 inclusive: ");
        PuzzleJava generator = new PuzzleJava();
	ArrayList<Integer> randomRolls = generator.getTenRolls();
	System.out.println(randomRolls);

	System.out.println("\nOne random letter: ");	
	System.out.println(generator.getRandomLetter());

	System.out.println("\nRandom 8 characters password: ");
	System.out.println(generator.randomPassword());

	System.out.println("\nRadom 8 characters password, but x amount of times:");
	System.out.println(generator.getNewPasswordSet(3));
	System.out.println("");


	int[] testNumbers = {77,4,12,8,5};
        generator.shuffleArray(testNumbers);
	}
}
