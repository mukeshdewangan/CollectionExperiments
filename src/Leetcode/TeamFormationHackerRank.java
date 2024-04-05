package Leetcode;

import java.util.*;

// https://www.hackerrank.com/x/library/hackerrank/all/questions/313920
/*
Given a list of employees where each is assigned a numeric evaluation score, use the selection process below to find the sum of scores of selected employees.
The employee with the highest score among the first k employees or the last k employees in the score list is selected.
The selected employee is removed from the score list.
The process continues to select the next employee until the team_size is achieved.
 */
public class TeamFormationHackerRank {
    private long teamFormation(List<Integer> score, int team_size, int k) {
        long sum = 0L;
        if(team_size == score.size()){
            sum = score.stream().mapToInt(Integer::intValue).sum();
            System.out.println(" Returning at the start since team_size == score.size() with sum " + sum);
            return sum;
        }

        int lastIndex = score.size()-1;
        PriorityQueue<Integer> frontQueue = new PriorityQueue<>(k, (a,b)-> b-a);
        PriorityQueue<Integer> backQueue = new PriorityQueue<>(k, (a,b)-> b-a);
        for (int i =0;i<k;i++){
            frontQueue.add(score.get(i));
            backQueue.add(score.get(lastIndex - i));
        }

//        while(!frontQueue.isEmpty()){
//            Integer element = frontQueue.poll();
//            System.out.println(element+ " ");
//        }
//        System.out.println("BACK Queue ");
//        while(!backQueue.isEmpty()){
//            Integer element = backQueue.poll();
//            System.out.println(element+ " ");
//        }
        int frontRemoval = k-1;
        int backRemoval = score.size() - k ;

        while( team_size > 0 &&  !frontQueue.isEmpty() && !backQueue.isEmpty()  ){
            if(frontQueue.peek() >= backQueue.peek()){
                int element = frontQueue.poll();
                System.out.println( element);
                sum += element;
                frontRemoval++;
                if(frontRemoval<backRemoval) frontQueue.add(score.get(frontRemoval));
            }
            else {
                int element = backQueue.poll();
                System.out.println( element);
                sum += element;
                backRemoval--;
                if(frontRemoval<backRemoval) backQueue.add(score.get(backRemoval));
            }
            team_size--;
        }

        if(!frontQueue.isEmpty() & team_size > 0){
            while ( team_size> 0 ){
                sum += frontQueue.poll();
                team_size--;
            }
        }

        if(!backQueue.isEmpty() & team_size > 0){
            while (team_size> 0){
                sum += backQueue.poll();
                team_size--;
            }
        }

        System.out.println( sum);
        return sum;
    }


    private long teamFormation2(List<Integer> score, int team_size, int k){
        long sum=0;

        Integer fmax=0;
        Integer rmax=0;
        Integer max=0;
        int index=0;
        List<Integer> fscore=new ArrayList<Integer>();
        List<Integer> rscore=new ArrayList<Integer>();
        for(int j=0;j<team_size;j++){
            if(score.size()<k)
                break;
            for(int i=0;i<k;i++)
            {

                fscore.add(score.get(i));
                rscore.add(score.get(score.size()-i-1));
            }
            fmax = Collections.max(fscore);
            rmax = Collections.max(rscore);
            if(fmax > rmax)
            {
                max=fmax;
                sum+=max;
                index=fscore.indexOf(fmax);
                score.remove(index);
            }
            else{
                max=rmax;
                sum+=max;
                index=rscore.indexOf(rmax);
                score.remove(score.size()-index-1);
            }
            fscore.clear();
            rscore.clear();
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] input = {6, 18, 8, 14, 10, 12, 18, 9};
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            list.add(input[i]);
        }
        TeamFormationHackerRank team = new TeamFormationHackerRank();
        team.teamFormation(list, 8, 3);
    }
}
