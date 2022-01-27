/**
 * HandTester.java 
 *
 * @author:
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */

public class HandTester 
{
    public static void main(String[] args)
    {
        //Create a new Hand Object
        Hand h1 = new Hand();

        System.out.println("Initial Hand Objcet");
        System.out.println(h1);
        System.out.println();

        System.out.println("Cause a blackjack");
        Card c1 = new Card(40);
        Card c2 = new Card(52);
        h1.addCard(c1);
        h1.addCard(c2);
        System.out.println(h1);

        System.out.println();
        System.out.println("Clear Deck");
        h1.clearHand();
        System.out.println();

        System.out.println("Cause an Ace to change to value 1");
        h1.addCard(c1);
        Card c3 = new Card(30);
        Card c4 = new Card (50);
        h1.addCard(c2);
        h1.addCard(c3);
        System.out.println(h1);

        System.out.println();
        System.out.println("Clear Deck");
        h1.clearHand();
        System.out.println();

        System.out.println("Print a clean hand");
        Card c5 = new Card(5);
        h1.addCard(c1);
        h1.addCard(c3);
        h1.addCard(c5);
        System.out.println(h1);

        System.out.println();
        System.out.println("Clear Deck");
        h1.clearHand();
        System.out.println();

        System.out.println("Cause a hand to bust");
        Card c6 = new Card(15);
        h1.addCard(c1);
        h1.addCard(c3);
        h1.addCard(c6);
        System.out.println(h1);
        System.out.println(h1.getValueOfHand());

        System.out.println();
        System.out.println("Clear Deck");
        h1.clearHand();
        System.out.println();

        System.out.println("Five Card Charlie");
        Card c7 = new Card(1);
        Card c8 = new Card(2);
        Card c9 = new Card(3);
        h1.addCard(c2);
        h1.addCard(c6);
        h1.addCard(c7);
        h1.addCard(c8);
        h1.addCard(c9);
        System.out.println(h1);
        System.out.println(h1.getValueOfHand());

        System.out.println();
        System.out.println("Clear Deck");
        h1.clearHand();
        System.out.println();

        System.out.println("Two aces = 12");
        h1.addCard(c7);
        h1.addCard(c8);
        System.out.println(h1);

    }
}