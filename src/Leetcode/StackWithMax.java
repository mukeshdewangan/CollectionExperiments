package Leetcode;
import java.util.*;

/*
Implement Stack
push -
pop
max
List<>
* */
public class StackWithMax {
    public static void main(String[] args) {
        MyStack stack = new MyStack();

        stack.push(4);
        stack.push(23);
        stack.push(10);
        stack.push(34);

        stack.push(4);
        //System.out.println( stack.getMin());
        System.out.println(stack.getMax());
        stack.pop();
        System.out.println( stack.getMax());
        stack.pop();
        System.out.println(stack.getMax());
        //System.out.println( stack.getMin());
        stack.pop();
        System.out.println(stack.getMax());
        stack.pop();
        System.out.println(stack.getMax());
        //System.out.println( stack.getMin());
    }
}

class MyStack{
    static class Pair{
        int value;
        int currenMax;
        public Pair(int val, int currentMax){
            this.value = val;
            this.currenMax = currentMax;
        }
    }
    List<Pair> list = new ArrayList<>();
    //List<Integer> maxList = new ArrayList<>();
    //Map<Integer, Integer > sortedNums = new TreeMap<>();

    public void push(Integer num){
        Pair p;
        if(list.isEmpty() || list.get(list.size() - 1).currenMax <= num) {
             p = new Pair(num , num);
        }
        else{
            p = new Pair( num, list.get(list.size() - 1).currenMax);
        }

        list.add(p);

//        if(maxList.isEmpty() || maxList.get(maxList.size() - 1) <= num) {
//            maxList.add(num);
//        }
//        else{
//            maxList.add(maxList.get(maxList.size()-1));
//        }
//
//        sortedNums.put(num, sortedNums.getOrDefault(num, 0) + 1);
        // push to another log n
    }

    public Integer pop(){
        if(list.isEmpty()) throw new RuntimeException("Empty Stack");
        Integer top = list.get(list.size()-1).value;
        list.remove(list.size()-1);

//        int count =  sortedNums.get(top) -1;
//        //if(count == maxList.get(maxList.size()-1))
//        maxList.remove(maxList.size()-1);
//        if(count == 0) sortedNums.remove(top);
//        else sortedNums.put(top, count);
        return top;
    }

    public Integer peek(){
        if(list.isEmpty()) throw new RuntimeException("Empty Stack");
        Integer top = list.get(list.size()-1).value;
        return top;
    }

//    public Integer getMin(){
//        int val = 0;
//        for (Map.Entry<Integer,Integer> entry: sortedNums.entrySet()) {
//            val = entry.getKey();
//            break;
//        }
//        return val;
//    }

    public Integer getMax(){
        //int val = 0;
        if(!list.isEmpty())
            return list.get(list.size()-1).currenMax;
            //return maxList.get(maxList.size()-1);
        else throw new RuntimeException("Empty");
    }
}