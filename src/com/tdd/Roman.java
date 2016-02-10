package com.tdd;

public  class Roman {
    
 
    
    private String roman;
    public Roman(String roman) {
        this.roman=roman;
    }
    public int toArabic() {
        System.out.println("Inside toArabic");
        return RomanDigit.valueOf(roman).getArabic();
    }

    enum RomanDigit{
        I(1),V(5),X(10);
        
        private final int arabic;
        
        RomanDigit(int arabic) {
            System.out.println("Inside Constr"+arabic);
            this.arabic=arabic;
        }
        int getArabic(){
            return this.arabic;
        }
        
        //public abstract void shout();
    }
}
