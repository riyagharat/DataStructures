/*

Riya Gharat             n00901846
1/26/2016


1.	log on to osprey.
2.	change your password (if you like!)
        $ passwd 

3.	Construct program 2.1 on page 76 of the book but you are going to write a getMin() 
        method also.
	Make the last four lines of code:
          
	y=arr.getMax(); /*this should work with any data set not just the data he has.
                      I will modify the data in your code to test it with several
                      data sets. You should declare any variables like y and z that you need.
                      The data might be all postive, all negative, or a mixture. But
                      it will alwasy consists of ints, including possibly the int 0 *

	System.out.println(y);
        z=arr.getMin();
        System.out.println(z); 


Prompt for Program 2.1: To the highArray.java program (Listing 2.3), add a method called getMax() that returns the value of the highest
key in the array, or -1 if the array is empty. Add some code in main() to exercise this method. You can assume all the keys are positive
numbers.


  */

class HighArray{
   
   private long[] a;               // ref to array a
   private int nElems;             // number of data items
   
   public HighArray(int max){      // constructor
      a = new long[max];           // create the array
      nElems = 0;                  // no items yet
   }
   
   public boolean find(long searchKey){
      int j; 
      for(j = 0; j<nElems; j++){
         if(a[j] == searchKey){
            break;
         }
      }   
            
         if(j == nElems){
            return false;
         }else{
            return true;
         } 
   }  //end find()
   
   public void insert(long value){
      a[nElems] = value;
      nElems ++;
   }
   
   public boolean delete(long value){
      int j;
      for(j = 0; j < nElems; j++){
         if(value == a[j]){
            break;
         }
      }                          
      if(j == nElems){
         return false;
      }else{
         for(int k = j; k < nElems; k++){
            a[k] = a[k+1];
         }
         nElems --;
         return true;
      }
   }
   
   public void display(){
      for(int j = 0; j < nElems; j++){
         System.out.println(a[j] + " ");
      }
      System.out.println("");
   }
   
   public long getMax(){
   
      int j;
      long value = a[0];
      for(j = 0; j < nElems; j++){
         if(value < a[j]){
            value = a[j];
         }   
      }         
   return value;
   }                  
   
   public long getMin(){
   
      int j;
      long value = a[0];
      for(j = 0; j < nElems; j++){
         if(value > a[j]){
            value = a[j];
         }   
      }         
   return value;
   }
}

class ProgramOne{   
   public static void main(String[] args) {
      int maxSize = 100;
      HighArray arr;
      arr = new HighArray(maxSize);
      
      arr.insert(77);
      arr.insert(99);
      arr.insert(44);
      arr.insert(55);
      arr.insert(22);
      arr.insert(88);
      arr.insert(11);
      arr.insert(00);
      arr.insert(66);
      arr.insert(33);
      
      arr.display();
      
      int searchKey = 35;
      if(arr.find(searchKey)){
         System.out.println("Found " + searchKey);
      }else{
         System.out.println("Can't find " + searchKey);
      }
      
      arr.delete(00);
      arr.delete(55);
      arr.delete(99);
      
      arr.display();
      
      long y, z;
      
      y=arr.getMax();
   	System.out.println("The max is: " + y);
      z=arr.getMin();
      System.out.println("The min is: " + z); 
      
   }            
} 