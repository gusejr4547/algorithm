package 프로그래머스.KAKAO_WINTER_INTERNSHIP_2024;

public class 산_모양_타일링 {
    public static void main(String[] args) {
        산_모양_타일링 Main = new 산_모양_타일링();
        int n = 4;
        int[] tops = {1, 1, 0, 1};
        System.out.println(Main.solution(n, tops));
    }

    /*
    DP
    위쪽 정삼각형과 함께 마름모 타일로 덮기
    왼쪽 정삼각형과 함께 마름모 타일로 덮기
    오른쪽 정삼각형과 함께 마름모 타일로 덮기
    정삼각형 타일로 덮기

    a[k] = k번째 아래 방향 정삼각형까지 덮되, k번째 아래 방향 정삼각형을 덮는 방법이 3번 방법인 경우의 수
    b[k] = k번째 아래 방향 정삼각형까지 덮되, k번째 아래 방향 정삼각형을 덮는 방법이 3번 방법이 아닌 경우의 수
     */
    public int solution(int n, int[] tops) {
        int[] a = new int[n + 1];
        int[] b = new int[n + 1];
        a[1] = 1;
        b[1] = tops[0] == 1 ? 3 : 2;

        for (int k = 2; k <= n; k++) {
            if (tops[k - 1] == 1) {
                a[k] = (a[k - 1] + b[k - 1]) % 10007;
                b[k] = (a[k - 1] * 2 + b[k - 1] * 3) % 10007;
            } else {
                a[k] = (a[k - 1] + b[k - 1]) % 10007;
                b[k] = (a[k - 1] + b[k - 1] * 2) % 10007;
            }
        }

        return (a[n] + b[n]) % 10007;
    }
}
