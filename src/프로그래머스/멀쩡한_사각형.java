package 프로그래머스;

public class 멀쩡한_사각형 {
    public static void main(String[] args) {

    }

    public long solution(int w, int h) {
        long answer = (long) w * h;

        // 하나의 구간 크기는 w,h 의 gcd와 관련있음.
        int cnt = gcd(w, h);

        int a = w / cnt;
        int b = h / cnt;

        // 하나 구간 안에 몇개 사라짐?
        long remove = 1 + (a - 1) + (b - 1);

        answer -= cnt * remove;
        return answer;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
