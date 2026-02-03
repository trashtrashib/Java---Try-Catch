import java.util.Scanner;

public class CookingTest {
    public static void main(String[] args) throws Exception {
        
        Scanner in = new Scanner(System.in);
        Cooking game = new Cooking(in);

        System.out.printf("Would you like to play?%n(0) Exit%n(1+) Play%n%n");
        while (game.getInput(in) != 0) {
        game.setUp();
        game.round();
        System.out.printf("Would you like to play again?%n(0) Exit%n(1+) Play again%n%n");
        game = new Cooking(in);
        }

    }
}
