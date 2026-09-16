package 프로그래머스;

public class _2개_이하로_다른_비트 {
    public long[] solution(long[] numbers) {
        // numbers.length <= 100 000
        int N = numbers.length;
        long[] answer = new long[N];

        for (int i = 0; i < N; i++) {
            long num = numbers[i];
            // 짝수일 때 +1 하면 bit 1개 바뀜
            if (num % 2 == 0) {
                answer[i] = num + 1;
            }
            // 홀수일 때
            else {
                // 가장 작은 bit 0인거 찾아서 1로 변경하고 그 전 bit 0으로 만들기
                long mask = 1;
                while ((mask & num) != 0) {
                    mask <<= 1;
                }

                answer[i] = num + mask - (mask >> 1);
            }
        }

        return answer;
    }
}
