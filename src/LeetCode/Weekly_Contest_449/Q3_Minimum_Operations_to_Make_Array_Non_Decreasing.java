package LeetCode.Weekly_Contest_449;

public class Q3_Minimum_Operations_to_Make_Array_Non_Decreasing {
    public static void main(String[] args) {

    }

    // nums의 sub array를 선택해서 원소에 x를 더하기 반복
    // 오름차순으로 nums를 만들기

    public long minOperations(int[] nums) {
        long answer = 0;
        int N = nums.length;

        // 앞에꺼보다 감소하면 차이만큼 더해야함.

        for (int i = 1; i < N; i++) {
            if (nums[i - 1] > nums[i]) {
                answer += nums[i - 1] - nums[i];
            }
        }

        return answer;
    }
}
