import java.util.ArrayList;
/**
 * Hand.java  
 *
 * @author: Noah, Joel, and Vik
 * Assignment #: Blackjack Hand
 * 
 * Brief Program Description:
 * Deck consists of an ArrayList of Cards, and a value depending on
 * the cards inserted into it. Hand must be evaluted based on status. 
 * Worked on by Noah and Joel on March 20, 2018
 *
 */
public class Hand
{
    private ArrayList<Card> cards;
    private int value;

    /**
     * Default constructor for the Hand class that instantiates a list of cards
     * Noah, Vik, and Joel
     */
    public Hand()
    {
        cards = new ArrayList<Card>();
        value = 0;
    }
    
    /**
     * @return  the cards in the hand
     * Noah, Vik, and Joel
     */
    public ArrayList<Card> getHand()
    {
        return cards;
    }

    /**
     * @return cards.size() the size of the list of cards
     * Noah, Vik, and Joel
     */
    public int getSizeOfHand()
    {
        return cards.size();
    }

    /**
     * @return value the sum of the values of cards in the list
     * Noah, Vik, and Joel
     */
    public int getValueOfHand()
    {
        value = 0;
        for(Card c : cards)
        {
            value += c.getCardValue();
        }
        return value;
    }

    /**
     * Returns the status of a hand: Blackjack, Bust, Five Card Charlie, or 
     * Simply a Value
     * Noah, Vik, and Joel
     * 
     * @return String status
     */
    public String status()
    {
        if(getSizeOfHand() == 2 && getValueOfHand() == 21)
        {
            return "Blackjack";
        }
        if(getValueOfHand() > 21)
        {

            for(int i = 0; i < getSizeOfHand(); i ++)
            {
                
                if(cards.get(i).getCardValue() == 11)
                {
                    if(getValueOfHand() > 21) {
                    Card temp = cards.remove(i);
                    temp.setAceToOne(true);
                    cards.add(i, temp);
                }
                    
                }
            }
           
            if(getValueOfHand() > 21)
            {
                return "Bust";
            } else {
                return status();
            }
        }
        if(getValueOfHand()<=21 && getSizeOfHand()==5)
        {
            return "Five Card Charlie";
        }
        return Integer.toString(getValueOfHand());
    }

    /**
     * Clears the list of cards
     * Noah, Vik, and Joel
     */
    public void clearHand()
    {
        for(Card i: cards)
            {
                if(i.isAce())
                {
                    i.setAceToOne(false);
                }
            }
        cards.clear();
    }

    /**
     * Adds a new card to the list
     * Noah, Vik, and Joel
     * 
     * @param Card c
     */
    public void addCard(Card c)
    {
        cards.add(c);
    }

    /**
     * Returns a string representation of the Hand
     * Noah, Vik, and Joel
     * 
     * @return String str
     */
    public String toString()
    {
        String str = "";
        for(Card c : cards)
        {
            str += c.toString() + "\n";
        }
        str += status();
        return str;
    }
}