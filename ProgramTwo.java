/*

Riya Gharat             n00901846
2/12/2016

Do problem 5.5 on pages 248-249 using Java (one file only--multiple classes ok).
 
The input will be a sequence of 3 ints entered from the keyboard
(separated only by one or more blanks), such as 7 1 3  as in problem 5.5.
This kind of input can be entered again and again until the user enters stop.
 
 7 1 3 means that there are seven items:
 1 2 3 4 5 6 7 (the numbering always starts at 1)
 and that the holder  starts at 1
 and that the passing is 3
 so that means that the first one eliminated is 4
 creating a new list
 1 2 3 5 6 7
 which now starts holding  at 5
 and the next one eliminated is 1
 creating a new list
 2 3 5 6 7
 which now starts holding at 2
 and the next one eliminated is 6
 creating a new list 2 3 5 7
 which now starts holding at 7
 and the next one eliminated is 5
 creating a new list 2 3 7
 which now starts holding at 7
 and the next one eliminated is 7
 creating a new list 2 3
 which now starts holding at 2
 and the next one eliminated is
 3
 creating the final list 2
 IN ALL SITUATIONS THE FIRST NUMBER IN THE LIST IS 1.
 
 When the user wants to stop the input, it should just be: stop
 The user should be able to continue providing input after the problem is solved.
 The output should be sent to the console (lower pane on jGrasp.)
 Please note also that the first number could be an extremely large int. 
 It might be fun to try a circular linked list in one class , an iterator in another,
 and then the application itself, but the choice is yours. Try to learn from the concepts of the chapter!
 JUST PRINT THE FINAL LIST OF A SINGLE int.
  
  
 1. turnin an electronic version of "bigone" (the file with the classes--unsharred) using
     $ turnin bigone kmartin.cop3530.a3
  
 ONLY turnin the .java file...do not turnin any runs. Name your  file your n number.java.
 DO NOT USE shar...
 
 2. check to see if the size of the file you saved is the same as the
    size of the file turned in
 
    $ turnin -c kmartin.cop3530.a3 (tells you how many
    bytes you have turned in--check against how
         many you have saved) 
       $ ls -l
         (list with long option)
 
 
   */
 import java.io.*;
 import java.util.*;
 import java.text.*;
 
 public class ProgramTwo{   
    public static void main(String[] args) {
    
       Scanner input = new Scanner(System.in);
       int numberOfNums = 0;
       int startingPoint = 0;
       int passing = 0;
       
       String end = "start";
       String userInput;
       
       while (end == "start"){
              
         System.out.println("Please enter three numbers (Enter \"stop\" to quit): ");
       
          if(input.hasNext()){
            if(input.hasNextInt()){
                numberOfNums = input.nextInt();
                startingPoint = input.nextInt();
                passing = input.nextInt();
                
                System.out.println(numberOfNums + " and " + startingPoint + " and " + passing); 
                
                LinkList newList = new LinkList();
                ListIterator iter = newList.getIterator();
                
                int x = 1;
                for(int i = 0; i < numberOfNums; i++){
                   iter.insert(x);
                   x++;
                }
                newList.displayList();
                
                iter.beginningPoint(startingPoint);
                iter.delete(passing, numberOfNums);
                
             }else if(input.hasNext()){
                userInput = input.next();
                boolean x = false;  
                while(x == false){
                  if(userInput.equals("stop")){
                     end = "stop";
                     System.out.println("END PROGRAM");
                     x = true;
                  }else{
                     System.out.println("Please enter a proper command: ");
                     userInput = input.next();            
                  }   
                }  
             }
          }        
      }            
   }       
}

class Link{

   public int dData;
   public Link next;
   
   public Link(int data){
      dData = data;
   }
   
   public void displayLink(){
      System.out.print(dData + " ");
   }
  
}

class LinkList{
   
   private Link first;
   
   public LinkList(){
      first = null;
   }
   
   public Link getFirst(){
      return first;
   }
   
   public void setFirst(Link f){
      first = f;
   }
   
   public boolean isEmpty(){
      return first == null;
   }
   
   public ListIterator getIterator(){
      return new ListIterator(this);
   }
   
   public void displayList(){
      Link current = first;
      while(current != null){
         current.displayLink();
         current = current.next;
      }
      System.out.println("");
   }                    

}

class ListIterator{

   private Link current;
   private Link previous;
   private LinkList ourList;
   
   public ListIterator(LinkList list){
      ourList = list;
      reset();
   }
   
   public void reset(){
      current = ourList.getFirst();
      previous = null;
   }      
   
   public boolean atEnd(){
      return(current.next == null);   
   }
   
   public boolean atEndPrevious(){
      return(previous.next == null);
   }   
   
   public Link getCurrent(){
      return current;
   }
   
   public void nextLink(){
      previous = current;
      current = current.next;
   }   
   
   public void insert(int value){
      Link newLink = new Link(value);
      if(ourList.isEmpty() == true){
         ourList.setFirst(newLink);
         current = newLink;
      }else{
         newLink.next = current.next;
         current.next = newLink;
         nextLink();
      }
   }
   
   public void beginningPoint(int begin){
      if(begin == 1){
         current = ourList.getFirst();
         previous = null;
 //        System.out.println("Current Location: " + current.dData);
      }else if(begin != 1){
         current = ourList.getFirst();
         previous = null;
         for(int i = 1; i < begin; i++){
            nextLink();
  //          System.out.println("Current Location: " + current.dData);
         }
      }       
   } 
   
   public void delete (int skips, int length){
   
      while(current != previous){
         for(int i = 0; i < skips; i++){
            if(atEnd() == true){
               previous = current;
               current = ourList.getFirst();
            }else{
               previous = current;
               current = current.next;
            }         
         } 
         if(atEnd() == true){
            System.out.println("Value removed: " + current.dData);
            previous.next = current.next;
            current = ourList.getFirst();
            ourList.displayList();
         }else{ 
            if(current == ourList.getFirst()){
               System.out.println("Value removed: " + current.dData);
               previous.next = null;
               ourList.setFirst(current.next);
               current = ourList.getFirst();
               ourList.displayList();   
            }else{   
               System.out.println("Value removed: " + current.dData);
               previous.next = current.next;
               current = current.next; 
               ourList.displayList();    
            }   
         }   
      }
   }
}   