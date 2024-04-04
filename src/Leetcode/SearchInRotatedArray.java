package Leetcode;

// https://leetcode.com/problems/search-in-rotated-sorted-array/description/
public class SearchInRotatedArray {
    public static void main(String[] args) {
        int[] nums = {6,7,0,1,2,3,4,5};
        int target = 0;
        SearchInRotatedArray s = new SearchInRotatedArray();
        System.out.println(s.search(nums, target));
    }

    public int search(int[] nums, int target) { // {7,8,0,1,2,3,4,5,6};
        int low = 0;
        int high = nums.length-1; // 7
        while(low <= high){ // low = 0, high = 7
            int mid = low + (high - low)/2; // mid = 3
            if(nums[mid] == target) return mid; //
            // if left part is sorted
            if(nums[low] <= nums[mid]){
                if(nums[low] <= target && nums[mid] > target){
                    high = mid-1; // high = 4
                }
                else{
                    low = mid+1; // low = 2
                }
            }
            // if right part is sorted
            else if(nums[mid+1] <= nums[high]){
                if(nums[mid+1] <= target && nums[high] >= target){
                    low = mid+1;
                }
                else{
                    high = mid-1 ; //
                }
            }
        }
        return -1;
    }
}
