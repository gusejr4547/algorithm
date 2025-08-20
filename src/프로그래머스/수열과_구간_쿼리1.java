package 프로그래머스;

public class 수열과_구간_쿼리1 {
    public static void main(String[] args) {

    }

    public int[] solution(int[] arr, int[][] queries) {
        int[] prefix = new int[arr.length + 1];
        for (int[] query : queries) {
            int s = query[0];
            int e = query[1];

            prefix[s] += 1;
            prefix[e + 1] -= 1;
        }

        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i] + prefix[i - 1];
        }

        return prefix;
    }
}
