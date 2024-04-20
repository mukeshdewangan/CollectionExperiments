package Leetcode;

import java.util.Stack;

// https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
public class RemoveMinimumParenthesisToMakeItValid {
//    public static void main(String[] args) {
//        RemoveMinimumParenthesisToMakeItValid p = new RemoveMinimumParenthesisToMakeItValid();
//        String str = "lee((t(c))o)de)(";
//        int result = p.minimumParenthesisToRemove(str);
//        int openCount = p.currentParenthesisCount(str);
//        String output = p.validStringAfterRemovingParenthesis(str);
//        System.out.println(str + ": " + result + " opening ( count " +openCount + " final "+ output);
//
////        str = "a)b(c)d";
////        result = p.minimumParenthesisToRemove(str);
////        openCount = p.currentParenthesisCount(str);
////        output = p.validStringAfterRemovingParenthesis(str);
////        System.out.println(str + ": " + result + " opening ( count " +openCount + " final "+ output);
////
////        str = "a)bc)d";
////        result = p.minimumParenthesisToRemove(str);
////        openCount = p.currentParenthesisCount(str);
////        output = p.validStringAfterRemovingParenthesis(str);
////        System.out.println(str + ": " + result + " opening ( count " +openCount + " final "+ output);
////
////        str = "((a)b((c)d";
////        result = p.minimumParenthesisToRemove(str);
////        openCount = p.currentParenthesisCount(str);
////        output = p.validStringAfterRemovingParenthesis(str);
////        System.out.println(str + ": " + result + " opening ( count " +openCount + " final "+ output);
//
//        str = "())()(((";
//        result = p.minimumParenthesisToRemove(str);
//        openCount = p.currentParenthesisCount(str);
//        output = p.validStringAfterRemovingParenthesis(str);
//        System.out.println(str + " : remove " + result + " parenthesis. valid open ( count "
//                + openCount + ". After removing extra parenthesis, final string "+ output);
//    }

    public static void main(String[] args) {
        RemoveMinimumParenthesisToMakeItValid p = new RemoveMinimumParenthesisToMakeItValid();
        String str = "lee((t(c))o)de)(";
        String output = p.stringAfterRemovingParenthesis(str);
        System.out.println(str + ": final "+ output);

        str = "a)b(c)d";
        output = p.stringAfterRemovingParenthesis(str);
        System.out.println(str + ": final "+ output);

        str = "a)bc)d";
        output = p.stringAfterRemovingParenthesis(str);
        System.out.println(str + ": final "+ output);

        str = "((a)b((c)d";
        output = p.stringAfterRemovingParenthesis(str);
        System.out.println(str + ": final "+ output);

        str = "())()(((";
        output = p.stringAfterRemovingParenthesis(str);
        System.out.println(str + ": final "+ output);

        str = "(((";
        output = p.stringAfterRemovingParenthesis(str);
        System.out.println(str + ": final "+ output);

        str = ")))(()";
        output = p.stringAfterRemovingParenthesis(str);
        System.out.println(str + ": final "+ output);
    }
    public int minimumParenthesisToRemove(String input){
        char[] chars = input.toCharArray();
        Stack<Character> stack = new Stack<>();
        int result = 0;
        int index = 0;

        while(index < chars.length){
            // if char == '(' -> put in stack
            if(chars[index] == '(') {
                stack.push(chars[index]);
            }
            else if(chars[index] == ')')  {
                // if char == ')' try to pop if non-empty, if empty then increase the result
                if(!stack.isEmpty())
                    stack.pop();
                else
                    result++;
            }
            index++;
        }
        while (!stack.isEmpty()){
            stack.pop();
            result++;
        }
        return result;
    }

    public int currentParenthesisCount(String input){
        char[] chars = input.toCharArray();
        Stack<Character> stack = new Stack<>();
        int openingCount = 0;

        for (int index = 0; index < chars.length; index++) {
            if(chars[index] == '(') {
                openingCount++;
                stack.push(chars[index]);
            }
            else if(chars[index] == ')')  {
                if(!stack.isEmpty()) {
                    //openingCount--;
                    stack.pop();
                }
            }
        }
        while (!stack.isEmpty()){
            stack.pop();
            openingCount--;
        }
        return Math.max(openingCount, 0);
    }

    public String validStringAfterRemovingParenthesis(String input){
        int validOpeningParenthesis = this.currentParenthesisCount(input);
        int totalParenthesisToPut = validOpeningParenthesis;
        char[] chars = input.toCharArray();
        StringBuilder s = new StringBuilder();
        int parenthesisAlreadyPut = 0;
        for (char aChar : chars) {
            if (aChar == '(' ){
                if(totalParenthesisToPut > 0 && parenthesisAlreadyPut < validOpeningParenthesis) {
                    parenthesisAlreadyPut++;
                    s.append(aChar);
                }
            } else if (aChar == ')'){
                if(parenthesisAlreadyPut > 0 ) {
                    s.append(aChar);
                    totalParenthesisToPut--;
                }
            } else {
                s.append(aChar);
            }
        }    
        return s.toString();
    }


    // This function does not use any other helper function,
    // it uses stack and puts closing parenthesis only if there exists an opening parethesis( i.e. stack in non-empty).
    // At last, if the opening are more, we discard them from the final string
    public String stringAfterRemovingParenthesis(String input){
        char[] chars = input.toCharArray();
        Stack<Character> stack = new Stack<>();
        StringBuilder intermediateString = new StringBuilder();
        for(int index = 0; index< chars.length;index++){
            char ch = chars[index];
            // if char == '(' -> put in stack
            if(ch == '(') {
                stack.push(chars[index]);
                intermediateString.append(ch);
            }
            else if(ch == ')')  {
                // if char == ')' if stack is non-empty then put to ')' and pop the top.
                if(!stack.isEmpty()) {
                    stack.pop();
                    intermediateString.append(ch);
                }
            }
            else intermediateString.append(ch);
        }
        int extraOpeningParenthesis = stack.size();

        int finalSize = intermediateString.length()-extraOpeningParenthesis;
        char[] finalResult = new char[finalSize];
        finalSize--;
        for(int index = intermediateString.length()-1; index>=0; index--){
            char ch = intermediateString.charAt(index) ;
            if(ch  == '('){
                if(extraOpeningParenthesis > 0) {
                    extraOpeningParenthesis--;
                }
                else {
                    finalResult[finalSize] = ch;
                    finalSize--;
                }
            }
            else {
                finalResult[finalSize] = ch;
                finalSize--;
            }
        }
        return new String(finalResult);
    }
}
