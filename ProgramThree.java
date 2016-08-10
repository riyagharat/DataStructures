/*

Riya Gharat             n00901846
2/25/2016


Problem 6.4:
   Write a program that solves the knapsack problem for an arbitrary knapsack capacity and series of weights. Assume the weights are stored
   in an array. Hint: The arguments to the recursive knapsack() function are the target weight and the array index where the remaining items
   start.

Do problem 6.4 (recursively) on page 313. The input should be captured on a single line in a window,
such as for the example, but with the target  FIRST followed by the weights, on page 306 (there
will be no repeated values). Permit the user to enter all the numbers on one line:

20 11 8 7 6 5
In this case the output would be:
8 7 5

Be sure and show all possible solutions! This is important! Only partial credit if this is not done.


It would be nice to have the output also in a window, but if you prefer the console, that is ok.
You may assume that the largest capacity (as well as any individual weight) is 100 and the largest
number of weights is 25.
You may also assume that the weights are sorted from largest to smallest.
The basic idea is to send a capacity and an array of weights to a recursive method and to either insert the 
weight or not. In either case call the method again with a reduced capacity and a shorter array OR with the same
capacity and a shorter array. There should be a base case(s) for easy capacity and/or easy array.
IF you do it this way, you would probably return another array which would be the potential solution array which
of course would only be printed it it is truly a solution.

There are multiple ways to attack the problem but recursion MUST be used! A design issue is whether to send two arrays or whether
you want to send one array and return another. It is your choice!


	$ turnin bigone kmartin.cop3530.a4

ONLY turnin the .java file.
(no shars please)

	Check to see if the size of the file you saved is the same as the
	size of the file turned in

	$ turnin -c kmartin.cop3530.a4 (tells you how many
	bytes you have turned in--check against how
        many you have saved) 
    	$ ls -l
        (list with long option)


   */
      
 import java.io.*;
 import java.util.*;
 import java.text.*;
 
 public class ProgramThree{
   public static void main(String[] args){
   
      Scanner input = new Scanner(System.in);
      
      int cap, counter = 0, i = 0;
      int [] weights = new int[25];
      
      System.out.println("Please enter a maximum, followed by the weights: ");
      StringTokenizer st = new StringTokenizer(input.nextLine());
      
      cap = Integer.parseInt(st.nextToken());
 //     System.out.println(cap + " this is the cap");
      
      while (st.hasMoreTokens()) {
         counter ++;
         weights[i] = Integer.parseInt(st.nextToken());
         i++;
      }    
      
      System.out.println("There are " + counter + " weights.");  
      
      int [] answer = new int[25]; 
      //starting indexes
      int valdex = 0;
      int soldex = 0;
      
      knapSack(cap, weights, answer, counter, valdex, soldex);    
      
   }
   
   public static void knapSack(int total, int [] values, int [] solutions, int numOfNums, int indexOfVal, int indexOfSol){
   
      int newTotal = total;
      int i = indexOfVal;
      int j = indexOfSol;
      int k = j;
      boolean check = false;
   
      //Base case
      if(newTotal == 0){
         display(solutions, j);
         check = true;
         return;
      }else{   
         if((values[i] <= total) && (i <= numOfNums)){
            newTotal -= values[i];
            solutions[j] = values[i];
            check = true;
            i++;      
            j++;
            knapSack(newTotal, values, solutions, numOfNums, i, j);
            knapSack(total, values, solutions, numOfNums, i, k);            
         }else if(i <= numOfNums){
            i++;   
            knapSack(total, values, solutions, numOfNums, i, k);
         }            
      }           
   }
   
   public static void display(int [] solutions, int numOfSol){
         
     for(int i = 0; i < numOfSol; i++){
      System.out.print(solutions[i] + " ");
     }
     System.out.println("");
   }     
}    