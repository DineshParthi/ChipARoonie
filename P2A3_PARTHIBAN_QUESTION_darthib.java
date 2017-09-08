//Name: Dinesh Parthiban
//Original Created Date: 11th July 2017
//Modified Date: 12th July 2017
//Description: This class consists of methods to read input from a file in a specified format
//and create the question for the ChipARoonie game.

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class P2A3_PARTHIBAN_QUESTION_darthib{

  private String fileName; //contains the file Name
  private ArrayList<String> fileContent; //store the contents of the file
  private String question; //contains the question
  private String hint; //contains the hint

  //no arg constructor
  public P2A3_PARTHIBAN_QUESTION_darthib(){
    fileName="";
    fileContent = new ArrayList<>();
    question="";
    hint="";
  }

  //method that sets the file name
  public boolean setFileName(String fn){
    fileName=fn;
    return setFileContent();
  }

  //method that initialise the file content based on the file name
  public boolean setFileContent(){

    try{
    Scanner in = new Scanner(new FileReader(fileName));
    StringBuilder sb = new StringBuilder();
      while(in.hasNext()) {
          fileContent.add(in.nextLine());
      }
      in.close();
      return true;
    }
      catch (FileNotFoundException ex){
        System.out.println("No file found....");
        return false;
      }
  }

  //setter method for question field
  public void setQuestion(String q){
    question=q;
  }

  //setter method for hint field
  public void setHint(String h){
    hint=h;
  }

  //getter method for question field
  public String getQuestion(){
    return question.toLowerCase();
  }

  //getter method for hint field
  public String getHint(){
    return hint;
  }

  //randomly generates the question
  public void generateQues(){
    //genrate a random number
    int rand= (int) (Math.random() * fileContent.size());
    //get the corresponding question and hint
    String[] temp = (fileContent.get(rand)).split(",");
    setQuestion(temp[0]);
    setHint(temp[1]);
  }
}
