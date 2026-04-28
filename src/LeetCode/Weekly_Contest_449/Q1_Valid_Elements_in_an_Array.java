package LeetCode.Weekly_Contest_449;

import java.util.ArrayList;
import java.util.List;

public class Q1_Valid_Elements_in_an_Array {
    public static void main(String[] args) {

    }

    public List<Integer> findValidElements(int[] nums) {
        List<Integer> answer = new ArrayList<>();
        int N = nums.length;
        int[] leftMAX = new int[N];
        leftMAX[0] = nums[0];
        for (int i = 1; i < N; i++) {
            leftMAX[i] = Math.max(leftMAX[i - 1], nums[i]);
        }
        int[] rightMAX = new int[N];
        rightMAX[N - 1] = nums[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            rightMAX[i] = Math.max(rightMAX[i + 1], nums[i]);
        }

        for (int i = 0; i < N; i++) {
            if (i == 0 || i == N - 1) {
                answer.add(nums[i]);
            } else if (nums[i] > leftMAX[i - 1] || nums[i] > rightMAX[i + 1]) {
                answer.add(nums[i]);
            }
        }

        return answer;
    }
}
