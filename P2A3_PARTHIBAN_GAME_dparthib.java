//Name: Dinesh Parthiban
//Original Created Date: 11th July 2017
//Modified Date: 13th July 2017
//Description: This program depicts a game which allows user to guess words.
//It is dependent on P2A3_PARTHIBAN_PLAYER_darthib and P2A3_PARTHIBAN_QUESTION_darthib classes.

import java.io.*;
import java.util.Scanner;

public class P2A3_PARTHIBAN_GAME_dparthib{

  protected P2A3_PARTHIBAN_PLAYER_darthib [] players; //stores the players
  protected P2A3_PARTHIBAN_QUESTION_darthib question; //stores the question
  protected StringBuilder guessedWord;  //stores the current gueesed word
  protected String status; // stores the status of the game

  //no arg constructor
  public P2A3_PARTHIBAN_GAME_dparthib(){
    this(1);
  }

  //default constructor that sets the number of players
  public P2A3_PARTHIBAN_GAME_dparthib(int num){
    //initialise the number of players
    players=new P2A3_PARTHIBAN_PLAYER_darthib[num];
    for(int i=0;i<num;i++)
      players[i]=new P2A3_PARTHIBAN_PLAYER_darthib();
    //initialise the question
    question= new P2A3_PARTHIBAN_QUESTION_darthib();
    //initialise guessedWord
    guessedWord= new StringBuilder();
    //initialise game status
    status = "Progress";
  }

//print a customized welcome message to the players
  public void welcomeMsg(){
    //get player names
    Scanner input=new Scanner (System.in);
    for(int i=0;i<players.length;i++){
      System.out.println("Enter name of player"+(i+1));
      String temp=input.nextLine();
      players[i].setName(temp);
    }
    //game rules
    System.out.println("Number of players:"+players.length);
    System.out.print("Welcome to the ChipARoonie Challenge :");
    for(int i=0;i<players.length;i++)
      System.out.print("\t"+players[i].getName());
    System.out.println("\n*********************************************************************************************************************************************************************************");
    System.out.println("\t\t\t\t\tRULES OF THE GAME");
    System.out.println("\nFor each round of the game, the player is prompted to input a guessed letter to see if that letter is in the secret word:\n"+
    "\t1.If the guessed letter is contained in the secret word, the player has won that round, and the guessed word thus far is printed (consisting of blank underscores and corrrectly guessed letters).\n"+
    "\t2.If the player's guessed letter is not in the secret word, the guessed word thus far is printed (consisting of blank underscores and any correctly guessed letters), and the player earns a tick.\n"+
    "\t\ta)The ticks add up. A player can only accumulate 6 incorrect ticks or he loses the game and the bomb goes off.\n"+
    "\t\tb)For each round that the player guesses a letter incorrectly, the color of the bomb is also displayed based on how many ticks the player has:\n"+
    "\t\tc)Each tick will correspond to the bomb exploding sooner, for each incorrectly guessed letter:\n"+
    "\t\t\t\t*1 tick = red\n"+
    "\t\t\t\t*2 ticks = orange\n"+
    "\t\t\t\t*3 ticks = yellow\n"+
    "\t\t\t\t*4 ticks = green\n"+
    "\t\t\t\t*5 ticks = blue\n"+
    "\t\t\t\t*6 ticks = purple  BOOM!!!\n");
    System.out.println("*********************************************************************************************************************************************************************************");

    boolean isFileThere=false;
    while (!isFileThere){
      //get the file name from user
      System.out.println("Enter question file for the game");
      String temp=input.nextLine();
      //handle incorrect file name
      isFileThere=question.setFileName(temp);
    }
    System.out.println("Let the game begin..! Tick..!! Tock..!!");
  }

  //setter method for guessedWord field
  public void setGuessedWord(){
    String ques=question.getQuestion();
      for(int i=0;i<ques.length();i++)
        guessedWord.append("_");
  }

  //getter method for guessedWord field
  public String getGuessedWord(){
  return guessedWord.toString();
  }

  //this method runs the game
  public void runGame(){
    question.generateQues();
    setGuessedWord();
    while(status.equals("Progress")){
      for(int i=0;i<players.length;i++){
        if(!isLost(players[i])){
          display(players[i]);
          if(!makeGuess())
            players[i].incTick();
        }
        if(checkWin()){
          System.out.println(players[i].getName()+" has won the game");
          System.out.println("The secret word was "+ question.getQuestion());
          break;
        }
        else if(isLost(players[i])){
          System.out.println("Bomb turned "+players[i].getColor()+" BOOM ");
          System.out.println(players[i].getName()+" has lost the game");
        }
        if(checkLoss()){
          System.out.println("Game Ends");
          System.out.println("The secret word was "+ question.getQuestion());
          break;
        }
      }
    }
  }

  //this method simulates the part where the user is allowed to input a word/letter
  public boolean makeGuess(){
    int choice=0; //get guess choice from the user
    Scanner input=new Scanner(System.in);
    boolean guessStatus=false;

    while(!((choice==1)||(choice==2))){
      System.out.println("Press 1 to guess the word or 2 to guess character");
      choice=input.nextInt();
      input.nextLine();
      if(choice==1){
        System.out.println("Enter word");
        String word=input.nextLine(); //get the word from the user
        guessStatus=checkWordGuess(word);
        //System.out.println("status"+guessStatus);
      }
      else if(choice==2)
      {
        boolean charValidation=false;
        String temp=null;
        while(!charValidation){
          System.out.println("Enter character");
          temp=input.nextLine(); //get the character from the user
          if(temp.length()==1)
            charValidation=true;
          else
            System.out.println("You cannot enter a String");
        }
        char letter[]=temp.toCharArray();
        guessStatus=checkLetterGuess(letter[0]);
        //System.out.println("status"+guessStatus);
      }
      else
      System.out.println("Invalid choice");
    }
    return guessStatus;
  }

  //this method checks whether the word is in the question and returns true if the guess is correct
  public boolean checkWordGuess(String word){
    if(word.equalsIgnoreCase(question.getQuestion())){
      System.out.println("You have guessed the word correctly");
      guessedWord.replace(0,guessedWord.length(),word);
      return true;
    }
    else{
      System.out.println("You have guessed the word incorrectly");
      return false;
    }
  }

  //this method checks whether the letter is in the question and returns true if the guess is correct
  public boolean checkLetterGuess(char letter){
    boolean checkLetterStatus=false; //store the status of the whether the guess is correct of not

    //check if the user has entered an already entered choice
    for(char c:guessedWord.toString().toCharArray()){
      if(Character.toLowerCase(letter)==Character.toLowerCase(c)){
        System.out.println("Guessed Letter is already present.. You Lose in this Round");
        return false;
      }
    }
    //check if the user has entered the correct character
    for(int i=0;i<guessedWord.length();i++){
      char[] temp= question.getQuestion().toCharArray();
      if(Character.toLowerCase(letter)==Character.toLowerCase(temp[i])){
        checkLetterStatus=true;
        guessedWord.setCharAt(i,letter);
      }
    }
    //print current round status to the user
    if(checkLetterStatus)
      System.out.println("Guessed Letter is present.. You Win in this Round");
    else
      System.out.println("Guessed Letter is not present.. You Lose in this Round");

    return checkLetterStatus;
  }

  //this method is check whether the game has been won
  public boolean checkWin(){
    if(guessedWord.toString().equalsIgnoreCase(question.getQuestion())){
      status="Won";
      return true;
    }
    return false;
  }

  //this method is check whether the game has been lost by all the players
  public boolean checkLoss(){
    for(int i=0;i<players.length;i++){
      if(!isLost(players[i]))
          break;
      if(i==players.length-1){
        status="Lost";
        return true;
      }
    }
    return false;
  }

  //check whether the corresponding player has lost the game
  public boolean isLost(P2A3_PARTHIBAN_PLAYER_darthib pl){
    if(pl.getTick()==6)
      return true;
    else
      return false;
  }

  //this method displays the details required for each round of the game
  public void display(P2A3_PARTHIBAN_PLAYER_darthib pl){
    System.out.println("**************************************************NEXT ROUND*******************************************************************************************************************************");
    System.out.println("It is player "+pl.getName()+" turn");
    System.out.println("Color of the bomb :"+pl.getColor());
    System.out.println("Number of guesses left :"+(6-pl.getTick()));
    System.out.println("Guessed word until now :"+getGuessedWord());
    System.out.println("Hint for the word :"+question.getHint());
  }
}
