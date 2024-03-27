package 프로그래머스;

public class 스타_수열 {
    public static void main(String[] args) {
        스타_수열 Main = new 스타_수열();
        int[] a = {5, 2, 3, 3, 5, 3};
        System.out.println(Main.solution(a));
    }

    // 길이 2이상 짝수
    public int solution(int[] a) {
        int[] n = new int[a.length];

        for (int num : a) {
            n[num]++;
        }

        int answer = 0;

        for (int i = 0; i < n.length; i++) {
            if (n[i] * 2 <= answer) continue;
            int starLen = 0;
            for (int j = 0; j < a.length - 1; j++) {
                if ((a[j] == i || a[j + 1] == i) && a[j] != a[j + 1]) {
                    starLen += 2;
                    j++;
                }
            }

            answer = Math.max(answer, starLen);
        }

        return answer;
    }
}
