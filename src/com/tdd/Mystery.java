package com.tdd;
 
 public class Mystery {
     public static void main(String[] args) {
         int result = compute(4);
         System.out.println(result);
    }
     
     public static int compute(int n) {
         if(n == 1) {
             return 1;
         } else {
             
             System.out.println("n="+n+" n-1="+(n-1));
             int i=n * compute(n - 1);
             System.out.println("i="+i);
             // here's the recursive invocation
             return i;
         }
     }
 }
