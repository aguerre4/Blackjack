
/**
 * PlayerTester.java 
 *
 * @author:
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */

public class PlayerTester 
{
   public static void main(String[] args)
   {
       System.out.println("Creates the player");
       Player p1= new Player(1000,"Steve");
       System.out.println("Prints the player's chips and name");
       System.out.println(p1);
       System.out.println("The player bets 50 chips");
       System.out.println(p1.betChips(50));
       System.out.println("The player recieves 75 chips");
       p1.chipsWon(75);
       System.out.println(p1.getChips());
       p1.getHand().addCard(new Card(27));
       p1.getHand().addCard(new Card(21));
       System.out.println("Prints the player's hand");
       System.out.println(p1.getHand());
       System.out.println("Clears the player's hand");
       p1.newHand();
       System.out.println(p1.getHand());
   }
}
