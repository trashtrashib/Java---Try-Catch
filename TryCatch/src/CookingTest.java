import java.util.Scanner;

public class CookingTest {
    public static void main(String[] args) throws Exception {
        
        Scanner in = new Scanner(System.in);
        Cooking game = new Cooking(in);

        game.round();

    }
}
