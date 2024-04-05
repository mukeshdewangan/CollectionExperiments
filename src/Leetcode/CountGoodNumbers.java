package Leetcode;

public class CountGoodNumbers {
    static int modulo = 1000000007;

    public static void main(String[] arr){
        int n = 4;
        CountGoodNumbers count = new CountGoodNumbers();

        System.out.println(count.countGoodNumbers(n));

    }

    int power(int x, long y){
        long divideBy2 = y/2;

        if( y == 0) return 1;
        if(y==1) return x;

        int temp =  power(x,divideBy2);

        temp %= modulo;

        int res = (temp * temp)%modulo;

        if(y%2 == 1){
            res = (res * x)%modulo;
        }
        return res;
    }
    public int countGoodNumbers(long n) {
        long odd = n/2;

        long even = n - odd;

        int oddPower = power(4, odd);
        int evenPower = power(5, even);

        int result = ((oddPower % modulo) * (evenPower % modulo));

        return result;

    }
}
