package Leetcode;

import java.util.*;

public class LeaderBoard {
    private final Map<Integer, List<String>> scopeUsersMap = new TreeMap<>(Comparator.reverseOrder());
    private final Map<String, Integer> userScoreMap = new HashMap<>();

    public static void main(String[] args) {
        LeaderBoard lb = new LeaderBoard();
        String  first = "first";
        String second = "second";
        String third = "third";
        lb.addUser(first);
        lb.addUser(second);
        lb.addUser(third);

        lb.incrementViews(first);lb.incrementViews(first);lb.incrementViews(first);lb.incrementViews(first);
        lb.incrementViews(second);lb.incrementViews(second);lb.incrementViews(second);lb.incrementViews(second);lb.incrementViews(second);
        lb.incrementViews(third);lb.incrementViews(third);lb.incrementViews(third);

        List<String> list = lb.getTop(1);
        list.forEach(System.out::println);
//        lb.incrementViews(third);
//        lb.incrementViews(third);
//        lb.incrementViews(third);
//        lb.incrementViews(third);
//        lb.incrementViews(third);

        //
        lb.decrementViews(second);
        lb.decrementViews(second);
        lb.decrementViews(second);
        //lb.removeUser(second);

        //lb.removeUser(third);
        //lb.removeUser(first);

        list = lb.getTop(1);
        list.forEach(System.out::println);
    }

    public void incrementViews(String userId){
        Integer updated = userScoreMap.get(userId) + 1;
        addUser(userId, updated);
    }

    public void decrementViews(String userId){
        Integer updated = userScoreMap.get(userId) - 1;
        if(updated <=0){
            updated =0;
        }
        addUser(userId, updated);
    }

    public void addUser(String userId){
        addUser(userId, 0);
    }

    private void addUser(String userId, Integer newScore){
        removeUser(userId);
        userScoreMap.put(userId, newScore);
        scopeUsersMap.computeIfAbsent(newScore, k-> new ArrayList<>()).add(userId);
    }

    public void removeUser(String userId){
        if(userScoreMap.containsKey(userId)){
            Integer score = userScoreMap.get(userId);
            List<String> strings = scopeUsersMap.get(score);
            strings.remove(userId);
            if(strings.isEmpty()){
                scopeUsersMap.remove(score);
            }
        }
    }

    public List<String> getTop(int k){
        int remainingSize = k;
        List<String> topList = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : scopeUsersMap.entrySet()) {
            if (remainingSize > entry.getValue().size()){
                remainingSize -=entry.getValue().size();
                topList.addAll(entry.getValue());
            }
            else {
                List<String> subList = entry.getValue().subList(0,remainingSize);
                topList.addAll(subList);
                remainingSize -=entry.getValue().size();
                break;
            }
        }
        return topList;
    }
}
