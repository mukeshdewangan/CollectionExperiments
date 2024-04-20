package Leetcode;

import java.util.Stack;

//https://leetcode.com/problems/basic-calculator-ii/description/
public class BasicCalculatorII {
    public static void main(String[] args) {
        BasicCalculatorII bc = new BasicCalculatorII();
        String str = "3+2*2";
        System.out.println(bc.calculate(str));
        str = " 3/2 ";
        System.out.println(bc.calculate(str));
        str = " 3+5 / 2 ";
        System.out.println(bc.calculate(str));

        str = "93/3*3+15/2";
        System.out.println(bc.calculate(str));
    }
    public int calculate(String s) {
        char[] chars = s.toCharArray();

        int curr = 0;
        char op = '+'; // This is important
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if(Character.isDigit(chars[i])) {
             curr = curr*10 + chars[i]-'0';
            }

            if(!Character.isDigit(chars[i]) && chars[i] != ' ' || i == chars.length-1){
                if(op == '+')
                    stack.push(curr);
                if(op == '-')
                    stack.push(-curr);
                if (op== '*' )
                    stack.push(stack.pop() * curr);
                if( op == '/')
                    stack.push(stack.pop() / curr);
                op = chars[i];
                curr = 0;
            }
        }
        int sum = 0;
        while (!stack.isEmpty())
            sum += stack.pop();
        return sum;
    }
}
