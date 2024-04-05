package Leetcode;

//https://leetcode.com/problems/container-with-most-water/description/
public class ContainerWithMaxArea {
    public static void main(String[] args) {
        int[] height =  {1,8,6,2,5,4,8,3,7};

        //int[] leftMax = {1,8,8,8,8,8,8,8,8};
//        int[] rightMax ={8,8,8,8,8,8,8,7,7};
//                         0 1 2 3 4 5 6 7 8
        ContainerWithMaxArea c = new ContainerWithMaxArea();

        System.out.println( c.maxArea2(height));
        int[] height2 =  {1,1};
        System.out.println( c.maxArea2(height2));
        //8*5 = 40
        //7*6 = 42



    }
    public int maxArea(int[] height) {
        int[] leftMax = new int[height.length];
        leftMax[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i-1]);
        }

        int[] rightMax = new int[height.length];
        rightMax[height.length - 1] = height[height.length -1];
        for (int i = height.length-2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i+1]);
        }

        int left =0;
        int right = height.length-1;
        int currentMaxArea = 0;
        while( left < right){
            int currentHeight = Math.min(leftMax[left] , rightMax[right]);
            int area = currentHeight * (right - left);
            currentMaxArea = Math.max(currentMaxArea, area);
            if(leftMax[left] < rightMax[right]){
                left++;
            }
            if(leftMax[left] > rightMax[right]) {
                right--;
            }
            else
                break;
        }
        return currentMaxArea;
    }

    public int maxArea2(int[] height) {
        int left =0;
        int right = height.length - 1;
        int maxArea = 0;
        while(left<right){
            int currentArea;
            if(height[left]< height[right]){
                currentArea = height[left]*(right-left);
                left++;
            }
            else {
                currentArea = height[right]*(right-left);
                right--;
            }
            maxArea = Math.max(currentArea, maxArea);
        }
        return maxArea;
    }
}
