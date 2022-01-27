import java.util.*;
/**
 * Game.java 
 *
 * @author: Noah, Vik, and Joel
 * Assignment #: Game Main Method
 * 
 * Brief Program Description:
 * Sets up the game by welcoming the player to the casino, asking them 
 * for their name, asking them for chips to purchase, displaying the rules
 * and beginning a game by instantiating a Dealer object and calling the
 * start() method
 *
 */

public class Game 
{
    public static void main(String[] args)
    {
        Scanner reader = new Scanner(System.in);
        System.out.println("Welcome to the Svetty Wap Casino");
        System.out.println("What is your name?");
        String n= reader.nextLine();
        System.out.println("Hello, "+n+" how many chips would you like to purchase?");
        int c= reader.nextInt();
        System.out.println("Would you like to read the rules of blackjack? Please enter yes or no.");
        String rule= reader.nextLine();
        while(!(rule.equalsIgnoreCase("Yes") || rule.equalsIgnoreCase("No"))){
            System.out.println("Please enter Yes or no");
            rule= reader.nextLine();
        }
        if(rule.equalsIgnoreCase("yes")){
            System.out.println("RULES:");
            System.out.println("1. The dealer will always stand on 17"+
                "\n2. Getting a blackjack will play 3 chips for every 2 you put in\n"+
                "3. Having five cards in your hand will always win unless the dealer has a blackjack\n"+
                "4. Insurance is offered if the dealer has an ace as their up card\n"+
                "5. An ace will change from a value of 11 to a value of 1 if a bust is imminent\n"+
                "6. If you have a hand with a value higher than 21, you bust and the dealer wins\n"+
                "7. A push is a tie between the dealer and the player.\n"+
                "8. After the first hand, the player has the option to “hit”, “stand” or “double down.”\n"+
                "9. If they choose to “hit” the player receives 1 card\n"+
                "10. If they choose to “stand,” the player receives nothing and the round continues\n"+
                "11. If they choose to “double down,” the player doubles their bet and receives 1 last card.\n");
            System.out.println();
            System.out.println("The game will now begin");
        }
        else {
            System.out.println("The game will now begin");
        }
        Dealer game= new Dealer(c,n);
        game.start();
    }
}
