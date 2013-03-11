package org.tizen.common.util.asm.main;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        
        
        
        
        if(args.equals("ASDF")) {
            System.out.println("Here is line 15 of main!");
        }
        else {
            System.out.println("Here is line 18 of main");
        }
        
        line(30);
        line(27);
    }
    
    public static void line(int number) {
        if(number == 27) {
            System.out.println("Here is line " + number + " of line");
        }
        else if (number == 30) {
            System.out.println("Here is line " + number + " of line");
        }
        
    }
    

}
