package com.tdd.shoppingcart;

public class copyarray {

    public static void main(String[] args) {
       /* int[]first= {1,2,3,4,5,6,7,8,9,10};
        int[]second= {8,8,8,8,8,8,8,8,8,8};
        int index=8;
        int numMoved=first.length-index-1;
        System.arraycopy(first, index+1, first, index, numMoved);
        System.out.println();
        for(int i=0;i<=9;i++) {
            System.out.println(first[i]+" ");
        }*/
        
        //int a = 3;
        int b = 3;
        //int c = ++a;
        int d = --b - ++b; 
        System.out.println("b "+b);
        System.out.println("d "+d);

         recur(0); 
    }
    public static int recur(int count) {
        System.out.println("Inside count "+count);
        if (count < 2) {
            int recur = recur(++count);
            int i = count + recur;
            System.out.println("count "+i);
            return i;
        }
        return count;
     }
    
    /* A->C
     * 
     * 
     * 
     * **/
}
