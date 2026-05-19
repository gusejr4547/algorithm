package LeetCode.Weekly_Contest_501;

public class Q1_Concatenate_Array_With_Reverse {
    public int[] concatWithReverse(int[] nums) {
        int N = nums.length;
        int[] answer = new int[N*2];
        for(int i=0; i<N; i++){
            answer[i] = nums[i];
            answer[N*2-1-i] = nums[i];
        }

        return answer;
    }
}
