
/**
 * Player.java  
 *
 * @author: Noah, Joel, Vik
 * Assignment #: Player Class Blackjack
 * 
 * Brief Program Description:
 * Player object has a hand and an amount of chips. These instances work closesly
 * with the Dealer class. 
 *
 */
public class Player
{
    private Hand playerHand;
    private int chips;
    private String name;
    
    /**
     * Parameterized constructor for the Player class
     * Noah, Vik, and Joel
     * 
     * @param c the amount of chips a player starts with
     * @param n the player's name
     */
    public Player(int c, String n)
    {
        playerHand = new Hand();
        chips = c;
        name = n;
    }
    
    /**
     * Takes in a player's bet and withdraws it from the player's chip count
     * Noah, Vik, and Joel
     * 
     * @param bet   the bet
     * @return int  the bet
     */
    public int betChips(int bet){
        chips-=bet;
        return bet;
    }
    
    /**
     * Adds chips back into the player's chip count
     * Noah, Vik, and Joel
     * 
     * @param   b   the winnings
     */
    public void chipsWon(int b){
        chips+=b;
    }
    
    /**
     * Returns the hand of the player
     * Noah, Vik, and Joel
     * 
     * @return  playerHand
     */
    public Hand getHand(){
        return playerHand;
    }
    
    /**
     * Returns the amount of the chips of the player
     * Noah, Vik, and Joel
     * 
     * @return  chips
     */
    public int getChips(){
        return chips;
    }
    
    /**
     * Clears the hand of the player
     * Noah, Vik, and Joel
     */
    public void newHand(){
        playerHand.clearHand();
    }
    
    
    /**
     * Returns the name and chips of the player
     * Noah, Vik, and Joel
     */
    public String toString()
    {
        return name + "\n" + chips;
    }
    
}
