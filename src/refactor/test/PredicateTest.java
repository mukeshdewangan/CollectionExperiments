package refactor.test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class PredicateTest{
    public static List<Integer> findDuplicates(int[] nums) {
        //1,1,3
        //for(int i =0 ; i < nums;i++){
        int len = nums.length;
        int i=0;
        int element = nums[i];
        while(i < len) {//i =0
            // ele = 1
            if( nums[element-1] == -1 ) // nums[0] ==-1
            {
                nums[element-1]--;i++;
                element = nums[i];
            }
            else if(nums[element-1] == -2){
                i++;
                element = nums[i];
            }
            else //if(nums[element-1]!= -1 ){
            {
                int tempVal = nums[element-1];// tempVal = 1
                nums[element-1] = -1; //nums[0] = -1
                element = tempVal;
            }
            //if (element - 1 < 0 || element - 1 > len) {
                while (element - 1 < 0 || element - 1 > len) {
                    {
                        i++;
                        element = nums[i];
                    }

                }
            //}
        }
        List<Integer> res = new ArrayList<Integer>();
        for(int j =0 ; j < nums.length ;j++){
            if(nums[j]==-2){
                res.add(j+1);
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        List<Integer> res = findDuplicates(nums);
        for (Integer i: res) {
            System.out.println(i);
        }
    }
    public static void main1(String[] args) {
        Map<String,String> buffers = new HashMap<>();
        buffers.put("file1", "PathABC");
        buffers.put("file2", "PathABCDEFG");
        buffers.put("file3", "XYZPathABC");

        Map<String,String> softDel = new HashMap<>();
        buffers.put("file1", "SOFT DELETE");
        PredicateConcrete pc = new PredicateConcrete(Instant.now().toEpochMilli(),buffers,softDel,0);
        System.out.println(pc.canFlush());

        buffers.put("file4", "LMNXYZPathABC");
        System.out.println(pc.canFlush());
    }
}

class PredicateConcrete  {
    private long lastCheckedAt;
    private Map<String,String> buffers;
    private Map<String,String> softDelete;
    private long totalNumOfRecords;

    public PredicateConcrete(long lastCheckedAt, Map<String, String> buffers, Map<String, String> softDelete, long totalNumOfRecords){
        this.lastCheckedAt = lastCheckedAt;
        this.buffers = buffers;
        this.softDelete = softDelete;
        this.totalNumOfRecords = totalNumOfRecords;
    }
    public PredicateConcrete(){
        lastCheckedAt = Instant.now().toEpochMilli();
        buffers = new HashMap<>();
        softDelete = new HashMap<>();
        totalNumOfRecords = 0;
    }

    public boolean canFlush() {
        return buffers.size()>3;
    }
}

class StandardOutput{
    private long lastCheckedAt;
    private final Map<String,String> buffers;
    private final Map<String,String> softDelete;
    private long totalNumOfRecords;

    public StandardOutput(long lastCheckedAt, Map<String, String> buffers, Map<String, String> softDelete, long totalNumOfRecords){
        this.lastCheckedAt = lastCheckedAt;
        this.buffers = buffers;
        this.softDelete = softDelete;
        this.totalNumOfRecords = totalNumOfRecords;
    }

    public StandardOutput(){
        lastCheckedAt = Instant.now().toEpochMilli();
        buffers = new HashMap<>();
        softDelete = new HashMap<>();
        totalNumOfRecords = 0;
    }
}
