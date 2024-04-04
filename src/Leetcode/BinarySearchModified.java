package Leetcode;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class BinarySearchModified {
    public static void main_1(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for(int i=0;i<10;i++){
            int num = (int)(Math.random()*10);
            priorityQueue.add(num);
        }

        while(!priorityQueue.isEmpty()){
            int num = priorityQueue.poll();
            //System.out.println(num + ",");
        }

        LocalDate past = LocalDate.parse("1970-12-27");
        LocalDate now = LocalDate.now();

        LocalDate finalDate = now.compareTo(past) < 1 ? now.minusDays(1) : past;
        System.out.printf("diff " + finalDate);
        //System.out.println(priorityQueue);
    }

    public static void main_binarySearch(String[] args) {
        int[] arr = new int[]{-9,2,4,19,24,999};
        System.out.println("Binary Search");
        System.out.println("5 " + getIndexOfElement(arr, 5));
        System.out.println("4 " + getIndexOfElement(arr, 4));
        System.out.println("-9 " + getIndexOfElement(arr, -9));
        System.out.println("-90 " + getIndexOfElement(arr, -90));
        System.out.println("999 " + getIndexOfElement(arr, 999));
        System.out.println("10000 " + getIndexOfElement(arr, 10000));
        System.out.println("19 " + getIndexOfElement(arr, 19));
        System.out.println("24 " + getIndexOfElement(arr, 24));
    }


    public static void main(String[] args) {
        int[] arr = new int[]{-9,2,4,19,24,999};
        for (int i: arr) System.out.print(i + ",");
        System.out.println("Binary Search - Get index of element whose value is smaller or equal to search element");
        System.out.println("5 " + getIndexOfElementSmallerThanOrEqual(arr, 5));
        System.out.println("4 " + getIndexOfElementSmallerThanOrEqual(arr, 4));
        System.out.println("-9 " + getIndexOfElementSmallerThanOrEqual(arr, -9));
        System.out.println("-90 " + getIndexOfElementSmallerThanOrEqual(arr, -90));
        System.out.println("999 " + getIndexOfElementSmallerThanOrEqual(arr, 999));
        System.out.println("10000 " + getIndexOfElementSmallerThanOrEqual(arr, 10000));
        System.out.println("19 " + getIndexOfElementSmallerThanOrEqual(arr, 19));
        System.out.println("24 " + getIndexOfElementSmallerThanOrEqual(arr, 24));
        System.out.println("25 " + getIndexOfElementSmallerThanOrEqual(arr, 25));
    }

    public static void main_123(String[] args) {
        List<Integer> arr = Arrays.asList(-7,2,3,16,51,79);
        for (int i: arr) System.out.print(i + ",");

        System.out.println(getIndexGreaterThanOrEqual(arr,4));
        System.out.println(getIndexGreaterThanOrEqual(arr,-90));
        System.out.println(getIndexGreaterThanOrEqual(arr,100));
        System.out.println(getIndexGreaterThanOrEqual(arr,51));
        System.out.println(getIndexGreaterThanOrEqual(arr,2));
        System.out.println(getIndexGreaterThanOrEqual(arr,79));
        System.out.println(getIndexGreaterThanOrEqual(arr,9179));
    }

    private static int getIndexOfElement(int[] arr, int num){
        int start = 0;
        int end = arr.length-1;
        int mid;

        while(start<=end){
            mid = start+ (end - start)/2;
            if(arr[mid] == num)
                return mid;
            else if(arr[mid] > num)
                end = mid-1;
            else
                start = mid+1;
        }
        return -1;
    }

    private static int getIndexOfElementSmallerThanOrEqual(int[] arr, int search){
        int result = -1;
        int low = 0; int high = arr.length -1;
        int mid = 0;
        while(low <=high){
            mid = low + (high - low)/2;
            if(arr[mid] == search) return mid;
            if(arr[mid] < search) {
                result = mid;
                low = mid+1;
            }
            else {
                high = mid-1;
            }
        }
        return result;
    }

    private static int getIndexGreaterThanOrEqual(List<Integer> arr, int search){
        int low = 0; int high = arr.size();

        int result = -1;
        while(low < arr.size() && low <= high){
            int mid = low + (high - low)/2;
            if(arr.get(mid) <= search){
                result = mid;
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        return result;
    }
}
