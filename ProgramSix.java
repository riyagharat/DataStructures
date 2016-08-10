/*

Riya Gharat         N00901846

Assignment #6       NO LATE ASSIGNMENTS ACCEPTED PLEASE.     
DUE 11 pm Saturday, April 2, 2016                  
Use Java.

For this program you are going to read three files of strings (using only lowercase alpha chars, each with no blanks, and each
separated by a new line--all three files will be on the command line--build/run args). The data will be such as:

zyxabc
ace
bobbysue
maybeuptofortychars
etc

The files will have been constructed using Notepad on a Windows machine.

Each string should be thought of as a polynomial, such as

'z'*26**5 + 'y'*26**4 + 'x'* 26**3 + 'a'*26**2 + 'b'*26**1 +'c'*26**0
'a'*26**2 + 'c'*26**1 + 'e'*26**0
etc

where 'z' is the ASCII int associated with it (similarly) for all alpha chars.
For example, 'a' ==97.

So each string is essentially treated as a very large int which causes overflow.
The strings might have as many as 40 chars so that the leading power would be 26**39.


Task 1: Count the number of strings in the first file, say n.
Task 2: Find the first prime number larger than 2n, say p (a hint for finding p is in the book).
Task 3: Construct an array A and another array B of size p (indexed from 0 to p-1) to hold strings.
Task 4: Insert each of the strings in the first file into A using linear probing and into B using 
        quadratic probing. In each case use Horner's polynomial.
        Print the contents of A (index , string, and probe length for each insertion, all three on a single  line) followed
        by the contents of B (index, string, and probe length) . Only do the printing for non-empty cells.
                                 A
                      index   string   probe length for insertion
                       3        xyz        4
                       etc
      ave probe length:                        x.xx           
                                 B
                      index   string   probe length for insertion
                       3        xyz        4
                       etc
                 ave:                     y.yy


It is required that you hash (Horner's polynomial) using the technique we discussed
in class so as to avoid overflow at each stage. See:
http://math.fullerton.edu/mathews/n2003/HornerMod.html and/or pages 564-565.


Task 5: Search for each of the strings in the second  file from  A using hashing and linear probing,
        and from B using hashing and quadratic probing. Print the following tables for A and B 
        (just for the searches) :

               String   Success Failure Probe length for success  Probe length for failure
                ...      yes                     ...
                ...              yes                                          ...
          
average probe length:                                  x.xx                         y.yy                     


Task 6: Delete each of the strings in the third file from  A (if present)  using hashing and linear probing,
        and from B (if present) using hashing and quadratic probing. Print the following tables for A and B
        (just for the deletions) :
               
               String   Succcess Failure Probe length for success  Probe length for failure
                ...      yes                             ...
                ...              yes                                                ...
       
 average probe length:                                  x.xx                         y.yy



$turnin bigone kmartin.cop3530.a6   (no shar please--but a single file with the
class with main named your nnumber.

*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.lang.*;

public class ProgramSix{
  public static void main(String[] args){
   
     Scanner input = new Scanner(System.in);  
     // The number of Strings in File 1
     int n = 0;
     // The first prime number greater than 2n
     int p = 0;
     
     // NOTE: The file names must be entered without a .txt extension
      
     // Read file number 1
     String fileName = args[0];
     File file = new File(fileName + ".txt");   
     readFile(fileName, file);
     
//--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --    
  
     // Read file number 2
     String fileName2 = args[1];
     File file2 = new File(fileName2 + ".txt");   
     readFile(fileName2, file2);
     
//--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  -- 

     // Read file number 3
     String fileName3 = args[2];
     File file3 = new File(fileName3 + ".txt");   
     readFile(fileName3, file3);
      
//---------------------------------------------------------------------------------------------------------------------------------------------------     
 
// TASK 1: Number of strings in File 1
     
     n = countStrings(file, n);    
     
//---------------------------------------------------------------------------------------------------------------------------------------------------        

// TASK 2: First prime number larger than 2n, say p   
     
     for(int i = (2*n + 1); true; i++){
        if(isPrime(i)){
           p = i;
           System.out.println("The next prime number is: " + p);
           break;
         }
      }     
      
//---------------------------------------------------------------------------------------------------------------------------------------------------      

// TASK 3: Construct an array A and another array B of size p (indexed from 0 to p-1) to hold strings.
     
     String[] A = new String[p];
     String[] B = new String[p];
     
     for(int i = 0; i < A.length; i++){
        A[i] = null;
     }
     
     for(int i = 0; i < B.length; i++){
        B[i] = null;
     }     
     
//---------------------------------------------------------------------------------------------------------------------------------------------------
     
// TASK 4: Insert each of the strings in the first file into A using linear probing and into B using quadratic probing. 
        
     // Strings into A via linear probing
     
     int[] linearProbe = new int[p];
     
     for(int i = 0; i < linearProbe.length; i++){
        linearProbe[i] = 0;
        
     }  
     
     try{
        Scanner sc = new Scanner(new FileInputStream(file));
        while(sc.hasNext()){
           insertLinear(A, sc.next(), p, linearProbe); 
        }     
                 
     }catch (IOException e){
        e.printStackTrace();
     }

     String divider = "--------------------------------------------------------------------------------------------------------------";
     
     // Print the contents of A (index, string, and probe length  
     System.out.println("");    
     printListTitles("A", divider);
     for(int i = 0; i < A.length; i++){
        if(A[i] != null){     
           printListOutput(i, A, linearProbe);
        }
     } 
     
     // Finding the average probe Length
     int sumLinear = 0;
     double avgLinear = 0;   
     int countLin = 0; 
     for(int i = 0; i < linearProbe.length; i++){
        sumLinear = sumLinear + linearProbe[i];    
        if(linearProbe[i] != 0){
           countLin++;
        }  
     }
     
     avgLinear = sumLinear/countLin;
     
     System.out.printf("\n%-20s %50.3f","Average Probe Length: ", avgLinear);
     System.out.println("");
     
//--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --      

     // Strings into B via quadratic probing   
     
     int[] quadProbe = new int[p];
     
     for(int i = 0; i < quadProbe.length; i++){
        quadProbe[i] = 0;
        
     }
     
     try{
        Scanner sc = new Scanner(new FileInputStream(file));
        while(sc.hasNext()){
           insertQuad(B, sc.next(), p, quadProbe); 
        }     
                 
     }catch (IOException e){
        e.printStackTrace();
     }
     
     System.out.println("");
     
     // Print the contents of B (index, string, and probe length)
     printListTitles("B", divider);
     for(int i = 0; i < B.length; i++){
        if(B[i] != null){     
           printListOutput(i, B, quadProbe);
        }
     }
     
     // Finding the average probe Length
     int sumQuad = 0;
     double avgQuad = 0; 
     int countQuad = 0;   
     for(int i = 0; i < quadProbe.length; i++){
        sumQuad = sumQuad + quadProbe[i];
        if(quadProbe[i] != 0){   
           countQuad++;
        }      
     }
     
     avgQuad = sumQuad/countQuad;
     
     System.out.printf("\n%-20s %50.3f","Average Probe Length: ", avgQuad);
     System.out.println("");    
     
//---------------------------------------------------------------------------------------------------------------------------------------------------
     
/*TASK 5: Task 5: Search for each of the strings in the second  file from  A using hashing and linear probing,
        and from B using hashing and quadratic probing. Print the following tables for A and B 
        (just for the searches) :

               String   Success Failure Probe length for success  Probe length for failure
                ...      yes                     ...
                ...              yes                                          ...
          
average probe length:                                  x.xx                         y.yy    */

     // Search for strings from second file from A using hashing and linear probing
     
     //Create array for linear success
     String[] linearSuccess = new String[p];
     
     for(int i = 0; i < linearSuccess.length; i++){
        linearSuccess[i] = null;
        
     }
     
     //Create array for linear failure
     String[] linearFailure = new String[p];
     
     for(int i = 0; i < linearFailure.length; i++){
        linearFailure[i] = null;
        
     }
     
     //Create array for linear success probe length
     int[] linearProbeLengthS = new int[p];
     
     for(int i = 0; i < linearProbeLengthS.length; i++){
        linearProbeLengthS[i] = 0;
        
     }
     
     //Create array for linear failure probe length
     int[] linearProbeLengthF = new int[p];
     
     for(int i = 0; i < linearProbeLengthF.length; i++){
        linearProbeLengthF[i] = 0;
        
     }
     
     //Number of Linear Successes
     int linearCountS = 0;
     
     //Number of Linear Failures
     int linearCountF = 0;
     
     
     //Read file 2 and execute the method
     try{
        Scanner sc = new Scanner(new FileInputStream(file2));
        while(sc.hasNext()){
           String temp = sc.next();
           findLinear(A, temp, p, linearSuccess, linearFailure, linearProbeLengthS, linearProbeLengthF); 
        }     
                 
     }catch (IOException e){
        e.printStackTrace();
     }
     
     //Print table for searching via linear probing in A
     printSearchTitles("A", divider);
     
     try{
        Scanner sc = new Scanner(new FileInputStream(file2));
        while(sc.hasNext()){
           String temp = sc.next();
           int hashVal = hashFunction(temp, p);
           printDelSearchOutput(hashVal, temp, linearSuccess, linearFailure, linearProbeLengthS, linearProbeLengthF);
        }     
                 
     }catch (IOException e){
        e.printStackTrace();
     }
     System.out.println("");    
     
     printAvgSearchDel(n, linearProbeLengthS, linearProbeLengthF); 
     
//--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --       

     // Search for strings from second file from B using hashing and quadratic probing
     
     //Create array for quadratic success
     String[] quadSuccess = new String[p];
     
     for(int i = 0; i < quadSuccess.length; i++){
        quadSuccess[i] = null;
        
     }
     
     //Create array for quadratic failure
     String[] quadFailure = new String[p];
     
     for(int i = 0; i < quadFailure.length; i++){
        quadFailure[i] = null;
        
     }
     
     //Create array for quadratic success probe length
     int[] quadProbeLengthS = new int[p];
     
     for(int i = 0; i < quadProbeLengthS.length; i++){
        quadProbeLengthS[i] = 0;
        
     }
     
     //Create array for quadratic failure probe length
     int[] quadProbeLengthF = new int[p];
     
     for(int i = 0; i < quadProbeLengthF.length; i++){
        quadProbeLengthF[i] = 0;
        
     }
     
     //Number of Quadratic Successes
     int quadCountS = 0;
     
     //Number of Quadratic Failures
     int quadCountF = 0;
     
     
     //Read file 2 and execute the method
     try{
        Scanner sc = new Scanner(new FileInputStream(file2));
        while(sc.hasNext()){
           findQuad(B, sc.next(), p, quadSuccess, quadFailure, quadProbeLengthS, quadProbeLengthF); 
        }     
                 
     }catch (IOException e){
        e.printStackTrace();
     }
          
     //Print table for searching via linear probing in b
     System.out.println("");    
     printSearchTitles("B", divider);
     
     try{
        Scanner sc = new Scanner(new FileInputStream(file2));
        while(sc.hasNext()){
        String temp = sc.next();
           int hashVal = hashFunction(temp, p);
           printDelSearchOutput(hashVal, temp, quadSuccess, quadFailure, quadProbeLengthS, quadProbeLengthF);
        }     
                 
     }catch (IOException e){
        e.printStackTrace();
     }
     System.out.println(""); 
     
     printAvgSearchDel(n, quadProbeLengthS, quadProbeLengthF);   
     
//---------------------------------------------------------------------------------------------------------------------------------------------------     

/*Task 6: Delete each of the strings in the third file from  A (if present)  using hashing and linear probing,
        and from B (if present) using hashing and quadratic probing. Print the following tables for A and B
        (just for the deletions) :
               
               String   Succcess Failure Probe length for success  Probe length for failure
                ...      yes                             ...
                ...              yes                                                ...
       
 average probe length:                                  x.xx                         y.yy
*/

     //Delete strings from third file from A via linear probing
     
     //Create array for linear success in deletion
     String[] linearSuccessDel = new String[p];
     
     for(int i = 0; i < linearSuccessDel.length; i++){
        linearSuccessDel[i] = null;
        
     }
     
     //Create array for linear failure in deletion
     String[] linearFailureDel = new String[p];
     
     for(int i = 0; i < linearFailureDel.length; i++){
        linearFailureDel[i] = null;
        
     }
     
     //Create array for linear success probe length in deletion
     int[] linearProbeLengthSDel = new int[p];
     
     for(int i = 0; i < linearProbeLengthSDel.length; i++){
        linearProbeLengthSDel[i] = 0;
        
     }
     
     //Create array for linear failure probe length in deletion
     int[] linearProbeLengthFDel = new int[p];
     
     for(int i = 0; i < linearProbeLengthFDel.length; i++){
        linearProbeLengthFDel[i] = 0;
        
     }
     
     //Number of Linear Successes in deletion
     int linearCountSDel = 0;
     
     //Number of Linear Failures in deletion
     int linearCountFDel = 0;
     
     
     //Read file 3 and execute the method
     try{
        Scanner sc = new Scanner(new FileInputStream(file3));
        while(sc.hasNext()){
           deleteLinear(A, sc.next(), p, linearSuccessDel, linearFailureDel, linearProbeLengthSDel, linearProbeLengthFDel); 
        }     
                 
     }catch (IOException e){
        e.printStackTrace();
     }
      
     //Print table for deleting via linear probing in A    
     System.out.println("");    
     printDelTitles("A", divider);
     
     try{
        Scanner sc = new Scanner(new FileInputStream(file3));
        while(sc.hasNext()){
        String temp = sc.next();
           int hashVal = hashFunction(temp, p);
           printDelSearchOutput(hashVal, temp, linearSuccessDel, linearFailureDel, linearProbeLengthSDel, linearProbeLengthFDel);
        }     
                 
     }catch (IOException e){
        e.printStackTrace();
     }
     System.out.println("");    
     
     printAvgSearchDel(n, linearProbeLengthSDel, linearProbeLengthFDel);   
     
     
//--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --       
     
     //Delete strings from third file from B via quadratic probing
     
     //Create array for quadratic success in deletion
     String[] quadSuccessDel = new String[p];
     
     for(int i = 0; i < quadSuccessDel.length; i++){
        quadSuccessDel[i] = null;
        
     }
     
     //Create array for quadratic failure in deletion
     String[] quadFailureDel = new String[p];
     
     for(int i = 0; i < quadFailureDel.length; i++){
        quadFailureDel[i] = null;
        
     }
     
     //Create array for quadratic success probe length in deletion
     int[] quadProbeLengthSDel = new int[p];
     
     for(int i = 0; i < quadProbeLengthSDel.length; i++){
        quadProbeLengthSDel[i] = 0;
        
     }
     
     //Create array for quadratic failure probe length in deletion
     int[] quadProbeLengthFDel = new int[p];
     
     for(int i = 0; i < quadProbeLengthFDel.length; i++){
        quadProbeLengthFDel[i] = 0;
        
     }
     
     //Number of quadratic Successes in deletion
     int quadCountSDel = 0;
     
     //Number of quadratic Failures in deletion
     int quadCountFDel = 0;
     
     
     //Read file 3 and execute the method
     try{
        Scanner sc = new Scanner(new FileInputStream(file3));
        while(sc.hasNext()){
           deleteQuad(B, sc.next(), p, quadSuccessDel, quadFailureDel, quadProbeLengthSDel, quadProbeLengthFDel); 
        }     
                 
     }catch (IOException e){
        e.printStackTrace();
     }
      
     //Print table for deleting via quadratic probing in B 
     System.out.println("");    
     printDelTitles("B", divider);
     
     try{
        Scanner sc = new Scanner(new FileInputStream(file3));
        while(sc.hasNext()){
        String temp = sc.next();
           int hashVal = hashFunction(temp, p);
           printDelSearchOutput(hashVal, temp, quadSuccessDel, quadFailureDel, quadProbeLengthSDel, quadProbeLengthFDel);
        }     
                 
     }catch (IOException e){
        e.printStackTrace();
     }
     System.out.println("");    
     
     printAvgSearchDel(n, quadProbeLengthSDel, quadProbeLengthFDel);

   }
   
//METHODS   

   // Read Files
   static public void readFile(String filename, File file){  
     if (!file.exists()) {
        System.out.println(filename + " does not exist.");
        return;
     }
   
     if (!(file.isFile() && file.canRead())) {
        System.out.println(file.getName() + " cannot be read from.");
        return;
     }
   }  
   
   //Count Number of Strings in File1
   static public int countStrings(File file, int n){
     try{
        Scanner sc = new Scanner(new FileInputStream(file));
        while(sc.hasNext()){
           sc.next();
           n++;
        }  
        
        System.out.println("There are " + n + " words in File 1.");
                 
     }catch (IOException e){
        e.printStackTrace();
     }
     return n;
   }    
   
   // Checks if the next number is prime
   static public boolean isPrime(int n){
      for(int j = 2; (j*j <= n); j++){
         if(n % j == 0){
            return false;
         }
      }   
      return true;
   }
   
   //Creates the hash key
   static public int hashFunction(String key, int arraySize){
      int hashVal = 0;
      
      for(int j = 0; j < key.length(); j++){
         int letter = key.charAt(j) - 96;
         hashVal = (hashVal * 26 + letter) % arraySize;
      }
      return hashVal;
   }
   
   //Print Titles for List
   static public void printListTitles(String s, String divider){
      System.out.printf("\n%50s", ("List " + s));
      System.out.println("");
      System.out.println(divider);
      System.out.printf("\n%-20s %-40s %10s", "Index:", "String:", "Probe Length for Insertion:");
   }
   
   //Print output for List
   static public void printListOutput(int i, String [] array, int [] probe){
      System.out.printf("\n%-20s %-40s %10s", i, array[i], probe[i]);
   }
   
   //Avg Probe Length for Searching/Deletion
   static public void printAvgSearchDel(int n, int [] probeLengthS, int [] probeLengthF){
     // Finding the average probe length success
     int sumS = 0;
     double avgS = 0;    
     int countS = 0;
     for(int i = 0; i < probeLengthS.length; i++){
        sumS = sumS + probeLengthS[i];    
        if(probeLengthS[i] != 0){
           countS++;
        }  
     }
     
     avgS = sumS/countS;
     
     //Finding the average probe length failure
     int sumF = 0;
     double avgF = 0;
     int countF = 0;    
     for(int i = 0; i < probeLengthF.length; i++){
        sumF = sumF + probeLengthF[i];
        if(probeLengthF[i] != 0){
           countF++;
        }      
     }
     
     avgF = sumF/countF;
     
     System.out.printf("\n%-20s %40.3f %30.3f","Average Probe Length: ", avgS, avgF);
     System.out.println("");   
   }
   
   //Print Titles for Searching
   static public void printSearchTitles(String s, String divider){
     System.out.printf("\n%50s", ("Searching " + s));
     System.out.println("");
     System.out.println(divider);
     System.out.printf("\n%-20s %10s %10s %30s %30s", "String: ", "Success", "Failure", " Probe length for success", "Probe length for failure");
   }
   
   //Print Titles for Deletion
   static public void printDelTitles(String s, String divider){
     System.out.printf("\n%50s", ("Deleting " + s));
     System.out.println("");
     System.out.println(divider);
     System.out.printf("\n%-20s %10s %10s %30s %30s", "String: ", "Success", "Failure", " Probe length for success", "Probe length for failure");
   }
   
   //Print output for Deletion/Searching
   static public void printDelSearchOutput(int hashVal, String temp, String [] success, String [] failure, int [] probeLengthS, int [] probeLengthF){
      if(success[hashVal].equals("yes")){
         System.out.printf("\n%-20s %10s %10s %20s %30s", temp, success[hashVal], "", probeLengthS[hashVal], "");
      }else if(failure[hashVal].equals("yes")){
         System.out.printf("\n%-20s %10s %10s %20s %30s", temp, "", failure[hashVal], "", probeLengthF[hashVal]);  
      }    
   }
   
   //Inserts the string by linear probing
   static public void insertLinear(String hashArray[], String key, int arraySize, int [] probeLength){
      int hashVal = hashFunction(key, arraySize);
      int countProbe = 0;
      key.trim();
      
      while(hashArray[hashVal] != null && hashArray[hashVal] != "DEL"){
         countProbe++;
         ++hashVal;
         hashVal %= arraySize;
      }
      hashArray[hashVal] = key;
      countProbe++;
      probeLength[hashVal] = countProbe;
      
   }  
   
   //Finds the string by linear probing
   static public void findLinear(String hashArray[], String key, int arraySize, String [] linearSuccess, String [] linearFailure,
      int [] linearProbeLengthS, int [] linearProbeLengthF){
      
        int hashVal = hashFunction(key, arraySize);
        int hashValOrig = hashVal;
        int countProbeS = 0;
        int countProbeF = 0;
        boolean found = false;
        
        while(hashArray[hashVal] != null){
           if(hashArray[hashVal].equals(key)){
              linearSuccess[hashValOrig] = "yes";
              linearFailure[hashValOrig] = "";
              countProbeS++;
              linearProbeLengthS[hashValOrig] = countProbeS;
              found = true;
           } 
           if(found == false){
              countProbeS++;
           }  
           countProbeF++;
           ++hashVal;
           hashVal %= arraySize;      
        }
        if(hashArray[hashVal] == null && found == false){
              linearFailure[hashValOrig] = "yes";
              linearSuccess[hashValOrig] = "";
              countProbeF++;
              linearProbeLengthF[hashValOrig] = countProbeF;
           }    
   }    
   
   //Deletes the string by linear probing       
   static public void deleteLinear(String hashArray[], String key, int arraySize, String [] linearSuccess, String [] linearFailure,
      int [] linearProbeLengthS, int [] linearProbeLengthF){
        
      int hashVal = hashFunction(key, arraySize);
      int hashValOrig = hashVal;
      int countProbeS = 0;
      int countProbeF = 0;
      boolean deleted = false;
        
      while(hashArray[hashVal] != null){
         if(hashArray[hashVal].equals(key)){
            hashArray[hashValOrig] = "DEL";
            linearSuccess[hashValOrig] = "yes";
            linearFailure[hashValOrig] = "";
            countProbeS++;
            linearProbeLengthS[hashValOrig] = countProbeS;
            deleted = true;
         }   
         ++hashVal;
         hashVal %= arraySize;
         countProbeF++;
         if(deleted == false){
            countProbeS++;
         }   
      }
      if(hashArray[hashVal] == null && deleted == false){
         linearFailure[hashValOrig] = "yes";
         linearSuccess[hashValOrig] = "";
         countProbeF++;
         linearProbeLengthF[hashValOrig] = countProbeF;
      }                             
   }     
   
   //Inserts the string by quadratic probing
   static public void insertQuad(String hashArray[], String key, int arraySize, int [] probeLength){
      int hashVal = hashFunction(key, arraySize);
      int step = 1;
      int countProbe = 0;
      
      while(hashArray[hashVal]!= null && hashArray[hashVal] != "DEL"){
         hashVal = hashVal + (step*step);
         step++;
         countProbe++;
         hashVal %= arraySize;
      }
      hashArray[hashVal] = key;     
      countProbe++;
      probeLength[hashVal] = countProbe;
   }   
   
   //Finds the string by quadratic probing 
   static public void findQuad(String hashArray[], String key, int arraySize, String [] quadSuccess, String [] quadFailure, int [] quadProbeLengthS,
      int [] quadProbeLengthF){
      
      int hashVal = hashFunction(key, arraySize);
      int hashValOrig = hashVal;
      int step = 1;
      int countProbeS = 0;
      int countProbeF = 0;
      boolean found = false;
      
      while(hashArray[hashVal] != null){
         if(hashArray[hashVal].equals(key)){
            quadSuccess[hashValOrig] = "yes";
            quadFailure[hashValOrig] = "";
            countProbeS++;
            quadProbeLengthS[hashValOrig] = countProbeS;
            found = true;
         }
         hashVal = hashVal + (step*step);
         step++;
         hashVal %= arraySize;
         countProbeF++;
         if(found == false){
            countProbeS++;
         }   
      }
      if(hashArray[hashVal] == null && found == false){
         quadFailure[hashValOrig] = "yes";
         quadSuccess[hashValOrig] = "";
         countProbeF++;
         quadProbeLengthF[hashValOrig] = countProbeF;
      }   
   } 
   
   //Deletes the string by quadratic probing     
   static public void deleteQuad(String hashArray[], String key, int arraySize, String [] quadSuccess, String [] quadFailure,
      int [] quadProbeLengthS, int [] quadProbeLengthF){
        
      int hashVal = hashFunction(key, arraySize);
      int hashValOrig = hashVal;
      int step = 1;
      int countProbeS = 0;
      int countProbeF = 0;
      boolean deleted = false;
        
      while(hashArray[hashVal] != null){
         if(hashArray[hashVal].equals(key)){
            hashArray[hashValOrig] = "DEL";
            quadSuccess[hashValOrig] = "yes";
            quadFailure[hashValOrig] = "";
            countProbeS++;
            quadProbeLengthS[hashValOrig] = countProbeS;
            deleted = true;
         }
         hashVal = hashVal + (step*step);
         step++;
         hashVal %= arraySize;
         countProbeF++;
         if(deleted == false){
              countProbeS++;
         }
      }
      if(hashArray[hashVal] == null && deleted == false){
         quadFailure[hashValOrig] = "yes";
         quadSuccess[hashValOrig] = "";
         countProbeF++;
         quadProbeLengthF[hashValOrig] = countProbeF;
      }                    
   }     
                             
}                  