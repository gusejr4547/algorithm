package 프로그래머스;

public class 지페접기 {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        int x = wallet[0];
        int y = wallet[1];
        // 큰거를 x
        if (x < y) {
            int tmp = x;
            x = y;
            y = tmp;
        }

        int a = bill[0];
        int b = bill[1];
        // 큰거를 a
        if (a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        while (x < a || y < b) {
            a = a / 2;
            answer++;

            // 큰거 a
            if (a < b) {
                int tmp = a;
                a = b;
                b = tmp;
            }
        }
        return answer;
    }
}
