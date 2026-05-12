package 프로그래머스;

public class 실력_격차 {
    public static void main(String[] args) {

    }

    // N명의 학생 점수 scores
    // 가장 높은 점수와 가장 낮은 점수의 차이를 구하시오
    // 점수 1~1000만
    // N <= 10만

    public int solution(int N, int[] scores) {
        int answer = 0;
        int max = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            max = Math.max(max, scores[i]);
            min = Math.min(min, scores[i]);
        }
        answer = max - min;
        return answer;
    }
}
