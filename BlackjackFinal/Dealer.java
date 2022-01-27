import java.util.ArrayList;
import java.util.Scanner;
/**
 * Dealer.java  
 *
 * @author: Noah, Joel, and Vik 
 * Assignment #:
 * 
 * Brief Program Description:
 * Dealer object runs the game, deals cards, takes the player's option, hits if at 16 or lower,
 * determines the outcome, handles chips and insurance
 *
 */
public class Dealer
{
    private Hand dealerHand;
    private Player gamePlayer;
    private Shoe gameShoe;
    private int chipPool;
    private int insurance;
    private boolean playerWins;
    
    /**
     * Paramaterized constructor for the dealer class
     * Noah, Vik, and Joel
     * 
     * @param c chips
     * @param n player's name
     */
    public Dealer(int c, String n)
    {
        dealerHand = new Hand();
        gameShoe = new Shoe(5);
        gamePlayer = new Player(c, n);
    }

    /**
     * Deals a card to either the dealer or the player
     * Noah, Vik, and Joel
     * 
     * @param   dealTo  either the dealer or the player
     */
    public void dealCard(String dealTo)
    {
        if(dealTo.equals("Dealer"))
        {
            //Gives card to dealer
            dealerHand.addCard(gameShoe.dealCard());
        }
        if(dealTo.equals("Player"))
        {
            //Gives card to player
            gamePlayer.getHand().addCard(gameShoe.dealCard());
        }
    }

    /**
     * The start() method starts a game, determines if shoes need to be reshuffled, deals cards, 
     * handles betting, and takes options for players and dealers. When all player options
     * are complete, the method sends the dealer to the endGame() method
     * Noah, Vik, and Joel
     */
    public void start()
    {
        //Reshuffles the shoe when the size of the shoe gets to 25 cards
        if(gameShoe.getNumCardsInShoe() <= 25)
        {
            gameShoe = new Shoe(5);
        }


        System.out.println("Player has " + gamePlayer.getChips() + " chips");

        Scanner reader = new Scanner(System.in);
        System.out.println("How many chips would you like to bet?");
        int bet = reader.nextInt();
        
        //Catches a bad integer bet
        while(bet<=0 || bet > gamePlayer.getChips()){
            System.out.println("Enter a valid bet (positive integer)");
            bet = reader.nextInt();
        }

        chipPool = gamePlayer.betChips(bet);
        System.out.println(gamePlayer.getChips() + " chips remaining");
        
        //
        System.out.println();
        startDealing();

        System.out.println("Player Hand \n" + gamePlayer.getHand()+"\n");
        System.out.println("Dealer Hand \nHole Card");
        System.out.println(dealerHand.getHand().get(1));

        System.out.println();

        if(dealerHand.getHand().get(1).getCardValue() == 11)
        {
            System.out.println("How much insurance do you wish to purchase (up to 1/2 of your original bet)?");
            insurance = reader.nextInt();

            while(insurance > chipPool/2 && insurance <= gamePlayer.getChips())
            {
                System.out.println("Please enter a valid insurance amount");
                insurance = reader.nextInt();
            }

            gamePlayer.betChips(insurance);
        }

        boolean isPlaying = false;
        boolean isDealing = false;

        while(!(isPlaying && isDealing))
        {
            if(!isPlaying)
            {
                isPlaying = playerOption();
                System.out.println();
            }
            if(!isDealing)
            {
                isDealing = dealerOption();
                System.out.println();
            }
        }
        endGame();
    }

    /**
     * Player option determines if a player hits, stands, or doubles down.
     * Once a player has standed or doubled down, playerOption() returns true, 
     * but if a player hits, playerOption() returns false. If true, playerOption()
     * doesn't run again, but if false, playerOption() runs again
     * Noah, Vik, and Joel
     * 
     * @return boolean  the result of the playerOption()
     */
    public boolean playerOption()
    {
        String result = "";
        Scanner reader = new Scanner(System.in);

        if(gamePlayer.getHand().status() != "Blackjack" && dealerHand.status() != "Bust" && gamePlayer.getHand().status() != "Bust")
        {
            if(gamePlayer.getHand().getSizeOfHand()==2){
                System.out.println("Do you wish to 'hit', 'stand', or 'double down'");
                result = reader.nextLine();
                while(!(result.equalsIgnoreCase("hit") || result.equalsIgnoreCase("stand") || result.equalsIgnoreCase("double down")))
                {
                    System.out.println("Please enter a valid response");
                    System.out.println("Do you wish to 'hit', 'stand', or 'double down'");
                    result = reader.nextLine();
                }
            }
            else{
                System.out.println("Do you wish to 'hit' or 'stand'");
                result = reader.nextLine();
                while(!(result.equalsIgnoreCase("hit") || result.equalsIgnoreCase("stand")))
                {
                    System.out.println("Please enter a valid response");
                    System.out.println("Do you wish to 'hit' or 'stand'");
                    result = reader.nextLine();
                }
            }

            
            if(dealerHand.status() == "Blackjack" && insurance > 0)
            {
                handleInsurance(insurance);
                return true;
            } else {
                chipPool += insurance;
            }

        } else {
            if(dealerHand.status() == "Blackjack")
            {
                System.out.println("Push");
                handleInsurance(insurance);
                return true;
            } else {
                return true;
            }
        }

        if(result.equals("hit"))
        {
            dealCard("Player");
            System.out.println();
            System.out.println("PLAYER HAND");
            System.out.println(gamePlayer.getHand());
            return false;
        }
        if(result.equals("stand"))
        {
            return true;
        }
        if(result.equals("double down"))
        {

            chipPool += gamePlayer.betChips(chipPool);
            dealCard("Player");
            System.out.println();
            System.out.println("PLAYER HAND");
            System.out.println(gamePlayer.getHand());
            return true;
        }


        return true;
    }
    
    /**
     * Begins the game by alternating dealing to player and dealer (2 cards each)
     * Noah, Vik, and Joel
     */
    public void startDealing()
    {
        dealCard("Player");
        dealCard("Dealer");
        dealCard("Player");
        dealCard("Dealer");
    }

    /**
     * If player recieves insurance, player gets back their insurance input
     * Noah, Vik, and Joel
     */
    public void handleInsurance(int i)
    
    {
        gamePlayer.chipsWon(i);
    }

    /**
     * Determines if the dealer can hit (if their count is lower than 17 and the player has not busted)
     * otherwise the dealer stands, and returns true
     * Noah, Vik, and Joel
     * 
     * @return boolean 
     */
    public boolean dealerOption()
    {

        if(!(gamePlayer.getHand().status().equals("Blackjack")))
        {
            if(dealerHand.getValueOfHand() < 17 && !(gamePlayer.getHand().status().equals("Bust")))
            {
                System.out.println("Dealer hits");
                dealCard("Dealer");
                System.out.println();
                System.out.println("DEALER HAND");
                System.out.println("Hole Card");
                for(int i = 1; i < dealerHand.getSizeOfHand(); i ++)
                {
                    System.out.println(dealerHand.getHand().get(i));
                }
                if(dealerHand.status().equals("Bust"))
                {
                    System.out.println(dealerHand.status());
                }
                return false;
            }

            if(!(dealerHand.status().equals("Blackjack")) && !(dealerHand.status().equals("Bust")) && !(gamePlayer.getHand().status().equals("Bust")))
            {
                System.out.println("Dealer Stands");
                System.out.println();
                System.out.println("DEALER HAND");
                System.out.println("Hole Card");
                for(int i = 1; i < dealerHand.getSizeOfHand(); i ++)
                {
                    System.out.println(dealerHand.getHand().get(i));
                }
                return true;
            }
        }
        return true;
    }
    
    /**
     * Determines if the player wins, the dealer wins, or push, and also handles chips
     * including 3-2 payout from blackjack. The dealer then clears both hands
     * and asks the player if they'd like to play again
     * Noah, Vik, and Joel
     */
    public void endGame()
    {
        System.out.println("===============================================\n");
        boolean push = false;
        boolean blackjack = false;

        if(gamePlayer.getHand().status().equals("Blackjack"))
        {
            if(!(dealerHand.status().equals("Blackjack")))
            {
                //System.out.println("Player Wins");
                playerWins=true;
                blackjack = true;
            } else {
                System.out.println("Push");
                playerWins=false;
                push = true;
            }
        }

        if(dealerHand.status().equals("Blackjack"))
        {
            System.out.println("Dealer Wins");
            playerWins=false;
        }

        if(!(dealerHand.status().equals("Blackjack")))
        {
            if(gamePlayer.getHand().status().equals("Five Card Charlie"))
            {
                //System.out.println("Player Wins");
                playerWins=true;
            }

            if(!(dealerHand.status().equals("Bust")))
            {
                if(!(gamePlayer.getHand().status().equals("Bust")) && !(gamePlayer.getHand().status().equals("Five Card Charlie")))
                {
                    if(dealerHand.getValueOfHand() < gamePlayer.getHand().getValueOfHand())
                    {
                        System.out.println("Player Wins");
                        playerWins = true;
                    } else if (dealerHand.getValueOfHand() > gamePlayer.getHand().getValueOfHand())
                    {
                        System.out.println("Dealer Wins");
                        playerWins = false;
                    } else {
                        //System.out.println("Push");
                        playerWins = false;
                        push = true;
                    }
                } else {
                    System.out.println("Dealer Wins");
                    playerWins = false;
                }
            }

            if(dealerHand.status().equals("Bust"))
            {
                if(!(gamePlayer.getHand().status().equals("Bust")))
                {
                    System.out.println("Player Wins");
                    playerWins = true;
                }
            }
        }

        if(!playerWins)
        {
            if(!(gamePlayer.getHand().status().equals("Bust")))
            {
            System.out.println("FINAL Player Hand");
            for(int j = 0; j < gamePlayer.getHand().getSizeOfHand(); j++)
            {
                System.out.println(gamePlayer.getHand().getHand().get(j));
            }
            System.out.println(gamePlayer.getHand().status());
            System.out.println();
            
            System.out.println("FINAL Dealer Hand");
            for(int i = 0; i < dealerHand.getSizeOfHand(); i ++)
            {
                System.out.println(dealerHand.getHand().get(i));
            }
            System.out.println(dealerHand.status());
            System.out.println();

            if(push)
            {
                System.out.println("Player gets back their bet");
                gamePlayer.chipsWon(chipPool);
                chipPool = 0;
            } else {
                System.out.println("Player loses their bet");
                chipPool = 0;
            }
        }
        } else {
            if(blackjack)
            {
                System.out.println("Blackjack win - Player wins the hand and their bet is matched 3:2!");
                gamePlayer.chipsWon((chipPool*5)/2);
                chipPool = 0;
            } else {
                System.out.println("Player wins the hand and their bet is matched 1:1!");
                gamePlayer.chipsWon(chipPool*2);
                chipPool = 0;
            }
        }

        dealerHand.clearHand();
        gamePlayer.getHand().clearHand();

        String result = "";
        Scanner reader = new Scanner(System.in);
        System.out.println("Player now has " + gamePlayer.getChips() + " chips");
        System.out.println("Press 'a' to play again, or press any key to end the game");
        result = reader.nextLine();

        if(result.equalsIgnoreCase("a"))
        {
            start();
        } else {
            System.out.println("Thanks for playing");
        }

    }
}
