package ThreadInterruption;

public class SimpleCipher {

    public static void main(String[] args) {
        int base = 10;
        int a = 4 , b =12 , c = 24;
        System.out.println( a%5 );

        while(b > base){
            b = b - base;
        }

        System.out.println( b %5 );
        while (c > base){
            c = c - base;
        }

        System.out.println( c - base %5 );
    }
}
