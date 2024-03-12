package 프로그래머스;

public class 입국심사 {
    public static void main(String[] args) {
        입국심사 Main = new 입국심사();
        int n = 6;
        int[] times = {7, 10};
        System.out.println(Main.solution(n, times));
    }

    public long solution(int n, int[] times) {
        long left = 0;
        long right = 1_000_000_000_000_000_000L;
        long answer = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            long peopleCnt = 0;
            for (int time : times) {
                peopleCnt += mid / time;
            }

            if (peopleCnt < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }
}
