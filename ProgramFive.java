/*
Riya Gharat    N00901846


Assignment #5       NO LATE ASSIGNMENTS ACCEPTED PLEASE.     

DUE 11 p.m.  Friday, March 11, 2016

Write a program to implement Huffman coding and decoding (see pages 415-421) in Java.

The program's input will be a command line file that can contain any char, but the 
only ones of interest in this assignment are the capital letters A through G.
A COMMAND LINE FILE IS JUST THAT: A FILE NAME THAT IS ENTERED ON THE COMMAND
LINE (you will use jGrasp with build/run args) PRIOR TO RUNNING THE PROGRAM.
Read the file char by char.
IF the file happens to contain chars other than A through G IGNORE THEM in a-d.
Spaces should be ignored , for example , as should other chars!
You may assume that each of A through G are in the file.

I want you to display (all answers MUST  go to 
the console) using a menu system (from the console OR from a window) :

  a.  The Huffman tree (see page 405 and 418 )itself (you may use the code from Tree.java if you wish.)
      You may assume that the tree will turn out to be no deeper than the tree shown on page 405.
      You may also assume that no individual char appears more than 9 times.
      Print either a char or a weight depending upon whether the node is a leaf or not.
      The Huffman tree can be constructed using a priority queue.
      Exercise OPTION a before other options.

  b.  the code table that displays the encoding for each of the chars A through G (see page 
      416-417.) EXERCISE  OPTION b after OPTION a. 

  c.  the binary encoding of the portion of the file
      that only contains the chars A through G (after each eight bits leave a space and print only 3 bytes per line.)
      The binary encoding may not necessarily display a "full" byte at the end (see page 417.) Exercise 
      OPTION c after OPTION b

 d.  the A-G portion of the original file that is calculated by using the HUFFMAN TREE  from
     part a and the binary encoding from part c (don't just print the file back from the original; you
     must use the HUFFMAN TREE to do this.)  So you want to "read" the binary encoding from part c (using the 
     HUFFMAN TREE from part a to reconstruct the portion of the file that consists of A through G.)  BASICALLY
     YOU ARE DEOCODING THE PORTION OF THE FILE THAT CONSISTS OF THE CHARS A-G.  AS YOU READ THE ENCODED FILE
     YOU ARE  MARCHING DOWN THE HUFFMAN TREE--WHEN YOU REACH A LEAF, YOU CAN FIGURE OUT WHICH CHAR IT IS AND
     YOU PRINT THAT CHAR. Exercise OPTION d after OPTION c.


*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.lang.*;

public class ProgramFive{
  public static void main(String[] args){
   
     Scanner input = new Scanner(System.in);
     Tree theTree = new Tree();
     
     String encoded = "";
     String [] masterCodes = new String[256];
     int [] numOfLetters;
     char [] lettersInFile;
     int ch;
     char charToSearchA = 'A', charToSearchB = 'B', charToSearchC = 'C', charToSearchD = 'D';
     char charToSearchE = 'E', charToSearchF = 'F', charToSearchG = 'G';
     int counterA = 0, counterB = 0, counterC = 0, counterD = 0, counterE = 0, counterF = 0, counterG = 0, total = 0, numOfNodes = 0;    
      
      while(true){
         System.out.println("Option a: Show the Huffman Tree.");
         System.out.println("Option b: Display the code table.");
         System.out.println("Option c: Display the binary encoding.");
         System.out.println("Option d: Decode the encoding.");
         System.out.println("Option e: EXIT.");
         System.out.print("Enter a choice: ");
         int choice = input.next().charAt(0);
         switch(choice){
            case 'a':
                        // Read the file            
               String fileName = args[0];
            
               File file = new File(fileName + ".txt");   
      
               if (!file.exists()) {
                  System.out.println(args[0] + " does not exist.");
                  return;
               }
         
               if (!(file.isFile() && file.canRead())) {
                  System.out.println(file.getName() + " cannot be read from.");
                  return;
               }
                                             
               try{
                  BufferedReader reader = new BufferedReader(new FileReader(file));
                  while((ch = reader.read()) != -1) {
                      if(charToSearchA == (char)ch) {
                          counterA++;
                          total++;
                      }
                      if(charToSearchB == (char)ch) {
                          counterB++;
                          total++;
                      }
                      if(charToSearchC == (char)ch) {
                          counterC++;
                          total++;
                      }
                      if(charToSearchD == (char)ch) {
                          counterD++;
                          total++;
                      }
                      if(charToSearchE == (char)ch) {
                          counterE++;
                          total++;
                      }
                      if(charToSearchF == (char)ch) {
                          counterF++;
                          total++;
                      }
                      if(charToSearchG == (char)ch) {
                          counterG++;
                          total++;
                      }
                  }
                  reader.close();
                  
                  if(counterA > 0){
                     numOfNodes++;}
                  if(counterB > 0){
                     numOfNodes++;}
                  if(counterC > 0){
                     numOfNodes++;}
                  if(counterD > 0){
                     numOfNodes++;}
                  if(counterE > 0){
                     numOfNodes++;}
                  if(counterF > 0){
                     numOfNodes++;}
                  if(counterG > 0){
                     numOfNodes++;}                  
                  
                  System.out.print("A: " + counterA + " B: " + counterB + " C: " + counterC + " D: " + counterD + " E: " + counterE);
                  System.out.println(" F: " + counterF + " G: " + counterG + " Total: " + total + " NumOfNodes: " + numOfNodes);
                  
                  lettersInFile = new char[numOfNodes]; 
                  char c = 'A';
                  for(int i = 0; i < numOfNodes; i++) {
			            lettersInFile[i] = c;
		            }
                                   
                  numOfLetters = new int[numOfNodes];
                  int n = 0;
                  for(int i = 0; i < numOfNodes; i++){
                     numOfLetters[i] = n;
                  }   
                  
                  int k = 0;
                  if(counterA > 0){
                     lettersInFile[k] = 'A';
                     numOfLetters[k] = counterA;
                     k++;
                  }
                  if(counterB > 0){
                     lettersInFile[k] = 'B';
                     numOfLetters[k] = counterB;
                     k++;
                  }
                  if(counterC > 0){
                     lettersInFile[k] = 'C';
                     numOfLetters[k] = counterC;
                     k++;
                  }
                  if(counterD > 0){
                     lettersInFile[k] = 'D';
                     numOfLetters[k] = counterD;
                     k++;
                  }
                  if(counterE > 0){
                     lettersInFile[k] = 'E';
                     numOfLetters[k] = counterE;
                     k++;
                  }
                  if(counterF > 0){
                     lettersInFile[k] = 'F';
                     numOfLetters[k] = counterF;
                     k++;
                  }
                  if(counterG > 0){
                     lettersInFile[k] = 'G';
                     numOfLetters[k] = counterG;   
                     k++;
                  }
                  
                  for(int i = 0; i < numOfNodes; i++){
                     System.out.println("Letter: " + lettersInFile[i]);
                     System.out.println("Num: " + numOfLetters[i]);
                  }   
                  
                  theTree.buildTree(numOfLetters, lettersInFile);  
                  theTree.displayTree(); 

                  
                }catch (IOException e){
                  e.printStackTrace();
                }    
                
               break;
            case 'b':
               
               String [] st = new String[256];
               
               System.out.println("");
               masterCodes = theTree.printCode(st, theTree.root, new String());
               System.out.println("");
               
               break;
            case 'c':
               
               System.out.println("");
               // Reread the File
               String fileName2 = args[0];
            
               File file2 = new File(fileName2 + ".txt");   
      
               if (!file2.exists()) {
                  System.out.println(args[0] + " does not exist.");
                  return;
               }
         
               if (!(file2.isFile() && file2.canRead())) {
                  System.out.println(file2.getName() + " cannot be read from.");
                  return;
               }

               try{
                  BufferedReader reader = new BufferedReader(new FileReader(file2));
                  while((ch = reader.read()) != -1) {
                     
                     if(charToSearchA == (char)ch) {
                          encoded = encoded + masterCodes[charToSearchA];
                     }
                     if(charToSearchB == (char)ch) {
                          encoded = encoded + masterCodes[charToSearchB];
                      }
                      if(charToSearchC == (char)ch) {
                          encoded = encoded + masterCodes[charToSearchC];
                      }
                      if(charToSearchD == (char)ch) {
                          encoded = encoded + masterCodes[charToSearchD];
                      }
                      if(charToSearchE == (char)ch) {
                          encoded = encoded + masterCodes[charToSearchE];
                      }
                      if(charToSearchF == (char)ch) {
                          encoded = encoded + masterCodes[charToSearchF];
                      }
                      if(charToSearchG == (char)ch) {
                          encoded = encoded + masterCodes[charToSearchG];
                      }                     
                  }
                  
 ///                 System.out.println(encoded);
 
                     System.out.println("The encoded file: ");
                  
                  // the number of sets of 8
                  int i = (encoded.length())/8;
                  // the excess numbers that don't fit in 8
                  int j = (encoded.length())%8;

                  int counter = 0;
                  int countSets = 0;
                  int countExcess = 0;
                  
                  System.out.println(" ");
                  
                  for(int k = 0; k < encoded.length(); k++){
                     while(countSets <= i){
                        if(counter == 3){
                           counter = 0;
                           System.out.println("");
                           countSets++;
                        }else{
                           counter++;
                           System.out.print(encoded.substring(k,k+8));  
                           k = k+8; 
                           System.out.print(" ");
                           countSets++;
                        }
                     }
                  }
 
                  if((i%3) == 0){
                     System.out.println("");
                     System.out.print(encoded.substring(encoded.length()-j, encoded.length()));
                  }else{
                     System.out.print(encoded.substring(encoded.length()-j, encoded.length()));
                  }   
                           
                  System.out.println("");
                  System.out.println("");
            
               }catch (IOException e){
                  e.printStackTrace();
                }
            
               break;
            case 'd':
               System.out.println("");
               int i = 0;
               int j = 1;
               String decode = "";
               
               System.out.println("The intial input was: " + theTree.decode(encoded, theTree.root, decode, i));      
               System.out.println("");
                     
               break;
            case 'e':
               return;   
            default:
               System.out.print("Invalid entry\n");
         }
      }                                                  
   }
   
   static class Tree{
   
      Node root;
      
      public Tree(){
         root = null;
      }   
   
      public Node buildTree(int frequency[], char letters[]){
                                                 
         PriorityQueue<Node> pq = new PriorityQueue<Node>();
         for (int i = 0; i < frequency.length; i++){
               Node node = new Node(frequency[i], letters[i], ' ',  null, null);
               pq.offer(node);  
         } 
         
         while(pq.size() > 1){
            Node left = pq.poll();
            Node right = pq.poll();
            Node node = new Node(left.iData + right.iData, ' ', ' ', left, right);
            pq.offer(node);
            root = node;
         }
         return pq.poll();
      }
      
      public void displayTree(){
         
         Stack globalStack = new Stack();
         globalStack.push(root);
         int nBlanks = 64;
         boolean isRowEmpty = false;
         System.out.println("..............................................................................................................");
         
         while(isRowEmpty == false){
            Stack localStack = new Stack();
            isRowEmpty = true;
            
            for(int j = 0;j < nBlanks; j++){
               System.out.print(" ");
            }
            
            while(globalStack.isEmpty() == false){
               Node temp = (Node)globalStack.pop();
               if(temp != null){
                  System.out.print(temp.iData);   
                  System.out.print(temp.letter);                
                  localStack.push(temp.leftChild);
                  localStack.push(temp.rightChild);
                  
                  if(temp.leftChild != null || temp.rightChild != null){
                     isRowEmpty = false;
                  }
               }else{
                  localStack.push(null);
                  localStack.push(null);
               }
               
               for(int j = 0; j < nBlanks*2-2; j++){
                  System.out.print(" ");
               }
            }
               
            System.out.println();
            nBlanks /= 2;
            
            while(localStack.isEmpty() == false){
               globalStack.push(localStack.pop());
            }
         }   
         System.out.println("..............................................................................................................");
      }                                
    
      public String [] printCode(String [] codes, Node root, String s){
     
         if(!root.isLeaf()){
            root.code = '1';
            printCode(codes, root.rightChild, s+'1');
         
            root.code = '0';
            printCode(codes, root.leftChild, s+'0');

         }else{
            System.out.println("The code for: " + root.letter + " is: " + s);
            codes[root.letter] = s;
            
         }
         return codes;
      }     
      
      public void encode(String coded, int i, int j){
         for(i = 0; i < coded.length(); i++){
            System.out.print(coded.substring(i, j));
            j++;
         }   
      } 
      
      public String decode(String coded, Node root, String decode, int i){
         
         Node current = root;
         char[] input = coded.toCharArray();
         i = 0;
         
         while(i < input.length){
            if(!current.isLeaf()){
               if(input[i] == '1'){
                  current = current.rightChild;
               }else if(input[i] == '0'){
                  current = current.leftChild;
               }
               i++;
            }else if(current.isLeaf()){
               decode = decode + (current.letter);
               current = root;
            }
         }        
         return decode;
      }     
    }                
      
   static class Node implements Comparable<Node>{
      private final int iData;
      private final char letter;
      private char code;
      private final Node leftChild;
      private final Node rightChild;   
      
      ArrayList <Integer> path = new ArrayList <Integer>();
      
      Node(int freq, char val, char num, Node lc, Node rc){
         iData = freq;
         letter = val;
         code = num;
         leftChild = lc;
         rightChild = rc;
      }
      
      public void displayNode(){
         System.out.print("{");
         System.out.print(iData);
         System.out.print(", ");
         System.out.print(letter);
         System.out.print("}");
      }
      
      private boolean isLeaf(){
         return ((leftChild == null) && (rightChild == null));
      }  
    
      public int compareTo(Node n){
         if(this.iData < n.iData){
            return -1;
         }else if(this.iData > n.iData){
            return 1;
         }else{
            return 0;
         }         
      }   
   }
             
} 

  