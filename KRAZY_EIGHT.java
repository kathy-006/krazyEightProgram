/**
* Group Members: 
* - Anya Joshi
* - Tu-Uyen Nguyen
* - Esha Shivakumar
* - Shakthi Sivasankar
*
* Period: 4
* 
* Game Name: Crazy Eights
* Program Description: This Java program implements a simplified and text-based version of the crazy-eights game.
* Game Objective: To be the first player yo empty their hand by strategically playing cards with matching rank or suits.
*
* Rules:
* - Player matches the suit/rank of a card from their hand with the card on top of the discard pile.
* - Eights are wild cards and can be used on top of any card on the discard pile.
* - A card is drawn for the player if there isn't a valid card.
* - The first player to empty their hand wins.
*
* Sample Output: Someone pls add or text me a sample output copied from the console. The eclipse softawre on 
* my computer keeps crashing.
*/

import java.util.Scanner;

/**
* This class represents a player.
*/

class Player {
   public String hand;
   public int skipCounter = 0;

   /**
    * This is a constructor of the player class that initializes the player's hand.
    */
   Player() {
       hand = "";
       skipCounter = 0;
   }
}

/**
* This class represents the state of the game and tracks the game loop.
*/
class State {
   
   String drawPile;
   // The discard pile.
   String discardPile;
   // Turn tracker.
   boolean playerTurn;
   // The player.
   Player player, computer;

   /**
    * This is a default constructor that initializes the state variables and the game 
    * environment.
    */

   State() {
       drawPile = "";
       discardPile = "";
       playerTurn = false;
       player = new Player();
       computer = new Player();
       setUpGame();
   }

   /**
    * This void method initializes that card game environment, including settimg up a standard deck of 52 cards, 
    * dealing 5 cards to the player and computer, creating a discard pile and setting the starting turn.
    */
    
   public void setUpGame() {
       
       for (int i = 0; i < 4; i++) {
           for (int j = 1; j <= 13; j++) {
               if (j == 1) {
                   drawPile += "A";
               } else if (j == 10) {
                   drawPile += "T";
               } else if (j == 11) {
                   drawPile += "J";
               } else if (j == 12) {
                   drawPile += "Q";
               } else if (j == 13) {
                   drawPile += "K";
               } else {
                   drawPile += j;
               }
               
               if (i == 0) {
                   drawPile += "S";
               } else if (i == 1) {
                   drawPile += "H";
               } else if (i == 2) {
                   drawPile += "D";
               } else if (i == 3) {
                   drawPile += "C";
               }
               drawPile += ",";
           }
       }

       for (int i = 0; i < 5; i++) {
           String card = drawCard();
           player.hand += card;
       }

       for (int i = 0; i < 5; i++) {
           String card = drawCard();
           computer.hand += card;
       }

       discardPile += drawCard();

       playerTurn = false;
   }


    /**
     * This method checks if a String represents a valid card of length 3 that can be played.
     *
     * @param card - The value in a string of the given card.
     * @return - True if the given card is valid and false otherwise.
     */
 
    public boolean isValidCard(String card) {
       if (card.length() != 3) {
           System.out.println("Invalid " + card + " length: " + card.length());
           return false;
       }
       if (card.charAt(0) != 'A' && card.charAt(0) != '2' && card.charAt(0) != '3' && card.charAt(0) != '4'
               && card.charAt(0) != '5' && card.charAt(0) != '6' && card.charAt(0) != '7' && card.charAt(0) != '8'
               && card.charAt(0) != '9' && card.charAt(0) != 'T' && card.charAt(0) != 'J' && card.charAt(0) != 'Q'
               && card.charAt(0) != 'K') {
           System.out.println("Invalid card number.");
           return false;
       }       
        if (card.charAt(1) != 'S' && card.charAt(1) != 'H' && card.charAt(1) != 'D' && card.charAt(1) != 'C') {
           System.out.println("Invalid card family.");
           return false;
       }
       if (card.charAt(2) != ',') {
           System.out.println("Invalid card connector.");
           return false;
       }
       return true;
   }

   public String displayCard(String card) {
       String result = "";

       if (card.charAt(0) == 'T') {
           result += "10";
       } else if (card.charAt(0) == 'J') {
           result += "Jack";
       } else if (card.charAt(0) == 'Q') {
           result += "Queen";
       } else if (card.charAt(0) == 'K') {
           result += "King";
       } else if (card.charAt(0) == 'A') {
           result += "Ace";
       } else {
           result += card.charAt(0);
       }
 
       result += " of ";

       if (card.charAt(1) == 'S') {
           result += "Spades";
       } else if (card.charAt(1) == 'H') {
           result += "Hearts";
       } else if (card.charAt(1) == 'D') {
           result += "Diamonds";
       } else if (card.charAt(1) == 'C') {
           result += "Clubs";
       }

       return result;
   }

   /**
    * This method displays either player's hand in a readable format.
    *
    * @param playerHand - The long string of all the cards in the player's hand separated by commas.
    */
    
   public void displayplayerHand(String playerHand) {
       String hand = "";
       for (int i = 0; i < playerHand.length(); i += 3) {
           hand += displayCard(playerHand.substring(i, i + 3));
           if (i != player.hand.length() - 3) {
               hand += ", ";
           }
       }
       System.out.println(hand);
   }

   /**
    * Return the top card of the discard pile.
    *
    * @param playerHand - The long string of all the cards in the player's hand separated by commas.
    */ 
    
   public String returnTopPlayedCard() {
       return discardPile.substring(discardPile.length() - 3);
   }

   /**
    * This method draws a random card from the draw pile.
    *
    * @return - returns the string value (rank and suit) of the drawn card.
    */ 
    
   public String drawCard() {

       String card = "";
       boolean isValidCard = false;

       while (!isValidCard) {
           if (drawPile.equals("")) {
               drawPile = discardPile;
               discardPile = "";
           }

           int index = (int) (Math.random() * (drawPile.length() / 3)) * 3;
           card = drawPile.substring(index, index + 3);
           drawPile = drawPile.substring(0, index) + drawPile.substring(index + 3);

           if (isValidCard(card)) {
               isValidCard = true;
           }
       }
       return card;
   }

    /**
    * This method checks if the card entered by the palyer can be played.
    *
    * @parameter card - the given card that is eneterd by the player.
    * @return - true if the player's card can be played, false if it can not be played.
    */ 
   
   public boolean checkPlayerCardValidity(String card) {
       // First, check if the card is in the proper format.
       if (!isValidCard(card)) {
           // The card is not in the proper format.
           return false;
       }
       
       // Next, check if the card is in the player's hand.
       if (!containsString(player.hand, card)) {
           // The card is not in the player's hand.
          return false;
       }
       // Next, check if the card can be played according to the rules.
       // For this, we need the top card of the discard pile.
       String topCard = returnTopPlayedCard();
       // If the card is of the same number, it can be played and the player's turn continues.
       if (card.charAt(0) == topCard.charAt(0)) {
           // The card can be played.
           return true;
       }
       // If the card is of the same family, it can be played and the player's turn ends.
       if (card.charAt(1) == topCard.charAt(1)) {
           // The card can be played.
           // The turn flips.
           playerTurn = !playerTurn;
           return true;
       }
       // If the player enters an 8, it can be played and the player's turn ends.
       if (card.charAt(0) == '8') {
           // The card can be played.
           // The turn flips.
           playerTurn = !playerTurn;
           return true;
       }
       // If none of the above conditions are satisfied, the card cannot be played.
       return false;
   }


   /**
    * This method checks if the card entered by the computer can be played.
    *
    * @parameter card - the given card that is eneterd by the player.
    * @return - true if the player's card can be played, false if it can not be played.
    */ 
    
   public boolean checkComputerCardValidity(String card) {
       // First, check if the card is in the proper format.
       if (!isValidCard(card)) {
           // The card is not in the proper format.
           return false;
       }
       // Next, check if the card is in the player's hand.
       if (!containsString(computer.hand, card)){
           // The card is not in the player's hand.
           return false;
       }
       // Next, check if the card can be played according to the rules.
       // For this, we need the top card of the discard pile.
       String topCard = returnTopPlayedCard();
       // If the card is of the same number, it can be played and the player's turn continues.
       if (card.charAt(0) == topCard.charAt(0)) {
           // The card can be played.
           return true;
       }
       // If the card is of the same family, it can be played and the player's turn ends.
       if (card.charAt(1) == topCard.charAt(1)) {
           // The card can be played.
           // The turn flips.
           playerTurn = !playerTurn;
           return true;
       }
       // If the player enters an 8, it can be played and the player's turn ends.
       if (card.charAt(0) == '8') {
           // The card can be played.
           // The turn flips.
           playerTurn = !playerTurn;
           return true;
       }
       // If none of the above conditions are satisfied, the card cannot be played.
       return false;
   }
    
    /**
    * This method checks if the given card exists in the given hand.
    *
    * @parameter handStr - The long string consisting of the cards in the hand separated by commas.
    * @parameter cardString - The string of length 3, that represents the card to be found in the hand.
    * @return - true if the card exists in the hand and false if it doesn't.
    */ 
    
    public boolean containsString(String handStr, String cardString){
        int stringLength = handStr.length();
        int substringLength = cardString.length();
        boolean check = true;
        
        for(int i = 0; i <= stringLength - substringLength; i++){
           check = true;
           
           for (int j = 0; j < substringLength; j++){
               if(handStr.charAt(i+j) != cardString.charAt(j)){
                   check = false;
               }
           }
           
           if(check){
               return true;
           } 
       }
       return false;
    }

  /**
    * This method checks if the player has any valid cards to play. If none of the cards are valid, the player can't
    * play.
    *
    * @return - true if the card can be played in the round and false if it can't be played.
    */ 
    
   public boolean checkPlayerPlayable() {
       String card = "";
       for (int i = 0; i < player.hand.length(); i += 3) {
           card = player.hand.substring(i, i + 3);
           if (isValidCard(card)) {
               String topCard = returnTopPlayedCard();
               if (card.charAt(0) == topCard.charAt(0) || card.charAt(1) == topCard.charAt(1)
                       || card.charAt(0) == '8') {
                   return true;
               }
           }
       }
       return false;
   }

    /**
    * This method gets the first available valid card to play from the computer's hand. If none of the cards are valid, 
    * the computer can't play.
    *
    * @return - the String value of the valid card in the computer's hand.
    */ 
    
   public String getValidComputerCard() {
       for (int i = 0; i < computer.hand.length(); i += 3) {
           String card = computer.hand.substring(i, i + 3);
           if (checkComputerCardValidity(card)) {                
               return card;
           }
       }
       return "";
   }

   // Game Loops.

   /**
    * This method display's the player's hand, displays the top card of the discard pile, and draws a card for the player 
    * if they don't have any valid cards to play.
    *
    * @parameter - the scanner object that takes in the player's input card.
    */ 
    
   public void PlayerTurn(Scanner sc) {
       System.out.println("Your hand: ");
       displayplayerHand(player.hand);

       System.out.println("Top card of the discard pile:\n" + displayCard(returnTopPlayedCard()));

       if (!checkPlayerPlayable()) {
           System.out.println("No valid cards. Draw one card.\n");
           String drawnCard = drawCard();
           player.hand += drawnCard;
           System.out.println("You drew:\n" + displayCard(drawnCard) + "\n");
           player.skipCounter++;
           if (player.skipCounter % 3 == 0) {
               playerTurn = !playerTurn;
               player.skipCounter = 0;
           }
           return;
       }

       System.out.println("Enter a card:");
       String card = sc.nextLine() + ",";

       if (card.charAt(0) == '1' && card.charAt(1) == '0') {
           card = "T" + card.substring(2);
       }

       if (!checkPlayerCardValidity(card) && !card.equals("None")) {
           System.out.println("Invalid card. Can't play this card. Enter another card:\n");
           PlayerTurn(sc);
       } else {

           player.hand = player.hand.substring(0, player.hand.indexOf(card)) + player.hand.substring(player.hand.indexOf(card) + 3);
           discardPile += card;
           System.out.println("You played:\n" + displayCard(card) + "\n");

           player.skipCounter = 0;
       }
   }

   /**
    * This method display's the top card of the discard pile, draws a card when needed for the computer, and displays
    * how many cards the computer has.
    *
    */ 
    
   public void ComputerTurn() {
       System.out.println("Top card of the discard pile:\n" + displayCard(returnTopPlayedCard()));
       String card = getValidComputerCard();

       System.out.println("The computer has " + (computer.hand.length() / 3) + " cards.");

       if (card.equals("")) {
           System.out.println("The computer has no correct cards to play. It must draw a card.\n");
           String drawnCard = drawCard();
           computer.hand += drawnCard;
           computer.skipCounter++;
           if (computer.skipCounter % 3 == 0) {
               playerTurn = !playerTurn;
               computer.skipCounter = 0;
           }
           return;
       }

       computer.hand = computer.hand.substring(0, computer.hand.indexOf(card)) + computer.hand.substring(computer.hand.indexOf(card) + 3);
       discardPile += card;
       System.out.println("The computer played:\n" + displayCard(card) + "\n");

       computer.skipCounter = 0;
   }

   /**
    * This method alternates between the player's turn and the computer's turn until either the computer or player
    * wins the game
    * 
    * @param sc - The scanner object for the user input.
    *
    */ 
    
   public void GameLoop (Scanner sc) {
       while (true) {
           if (playerTurn) {
               PlayerTurn(sc);
           } else {
               ComputerTurn();
           }

           if (player.hand.equals("")) {
               System.out.println("You won!");
               return;
           }

           if (computer.hand.equals("")) {
               System.out.println("The computer won!");
               return;
           }

           if (drawPile.equals("") && discardPile.equals("")) {
               System.out.println("The game is a draw!");
               return;
           }
       }
   }
}


/**
    * 
    * The main class represnting a simplified version of the Crazy Eights card game. 
    * This class contains the main function to start the game, initialized necessary components, and runs the game
    * until the completion.
    * 
    */ 
public class KrazyEight {
    
    /**
    * The main function for the crazy eights game.
    *
    * @param args - Command-line arguments
    */
    
    public static void main (String[] args) {
       Scanner sc = new Scanner(System.in);

       State state = new State();

       state.GameLoop(sc);
    }
}
