package 프로그래머스;

public class 점프와_순간_이동 {
    public static void main(String[] args) {

    }

    public int solution(int n) {
        int ans = 0;

        // k칸 점프 or 현재까지 거리 x 2 위치로 순간이동
        // 앞으로 K 칸을 점프하면 K 만큼의 건전지 사용
        // N칸 떨어진 곳으로 가려고 함
        // 건전지 사용 최소

        // 2로 나눈 나머지 => 나머지 있으면 +1, 다시 2로 나누기
        while (n != 0) {
            if (n % 2 == 1) {
                ans++;
            }
            n /= 2;
        }

        return ans;
    }
}
