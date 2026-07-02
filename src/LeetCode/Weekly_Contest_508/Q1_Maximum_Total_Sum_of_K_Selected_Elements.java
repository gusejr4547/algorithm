package LeetCode.Weekly_Contest_508;

import java.util.Arrays;

public class Q1_Maximum_Total_Sum_of_K_Selected_Elements {
    // nums에서 정확히 k개 선택
    // 선택한 수를 그냥 sum에 더하던지, mul 곱하고 sum에 더하기
    // mul을 곱하면 mul은 -1 된다.
    public long maxSum(int[] nums, int k, int mul) {
        // 큰 수 부터 mul을 곱하는게 좋다.
        Arrays.sort(nums);
        int idx = nums.length - 1;
        long sum = 0;
        while (k-- > 0) {
            long t = (long) nums[idx];
            if (mul > 0) {
                sum += t * mul;
                mul--;
            } else {
                sum += t;
            }
            idx--;
        }
        return sum;
    }
}
