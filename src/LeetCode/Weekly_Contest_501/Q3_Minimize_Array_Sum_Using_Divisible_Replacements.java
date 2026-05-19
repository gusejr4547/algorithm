package LeetCode.Weekly_Contest_501;

import java.util.*;

public class Q3_Minimize_Array_Sum_Using_Divisible_Replacements {
    // 최대한 작은수로 만들기

    // nums.length <= 100 000
    // nums[i] <= 100 000

    public long minArraySum(int[] nums) {
        boolean[] isExist = new boolean[100_001];
        TreeSet<Integer> set = new TreeSet<>();
        for(int num : nums){
            set.add(num);
            isExist[num] = true;
        }

        int[] check = new int[100_001];

        for(int num : set){
            if(check[num] != 0){
                continue;
            }

            for(int i=num; i<=100_000; i+=num){
                if(isExist[i] && check[i] == 0){
                    check[i] = num;
                }
            }
        }

        // nums 최종 값 찾기
        long answer = 0;
        for(int num : nums){
            answer += check[num];
        }

        return answer;
    }
}
