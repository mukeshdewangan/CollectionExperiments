package Leetcode;

import java.util.Arrays;

public class ListenersAndTowers {

    static int getMaximumRangeToSet(int[] listeners, int[] towers){
        Arrays.sort(listeners);
        Arrays.sort(towers);

        // for each listener, get the minimum distance from the current and next tower
        // if next tower is selected then increment the current tower
        int listenerCounter = 0;
        int towerCounter = 0;
        int maximumDistance = Integer.MIN_VALUE;
        while( listenerCounter < listeners.length) {
            int currentDis = Math.abs(listeners[listenerCounter] - towers[towerCounter]);
            int nextDis = 0;
            if (towerCounter+1 < towers.length ){
                nextDis = Math.abs(listeners[listenerCounter] - towers[towerCounter + 1]);
                if(nextDis < currentDis ) {
                    towerCounter++;
                    currentDis = nextDis;
                }
            }

            maximumDistance = Math.max(maximumDistance , currentDis);
            listenerCounter++;
        }
        return maximumDistance;
    }

    public static void main(String[] args) {
        int[] listeners = {3,1,8,11,15};
        int[] towers = {7,4,9,30};
        System.out.println(getMaximumRangeToSet(listeners, towers));
    }
}
