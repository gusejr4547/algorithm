package 프로그래머스;

public class 가장_긴_팰린드롬 {
    public static void main(String[] args) {
        가장_긴_팰린드롬 Main = new 가장_긴_팰린드롬();
        String s = "abcdcba";
        System.out.println(Main.solution(s));
    }

    public int solution(String s) {
        int answer = 0;

        String extended = extend(s);

        int[] dp = new int[extended.length()]; // i번째 문자를 중심으로 한 양옆으로 늘릴수있는 최대 길이?
        int r = 0; // 이전까지 팰린드롬의 끝나는 index 제일 큰값
        int c = 0; // r값의 중심 index

        for (int i = 0; i < extended.length(); i++) {
            // dp를 이용한 초기값
            if (r < i) {
                dp[i] = 0;
            } else {
                // 팰린드롬 범위 안에 있으면 최소 길이 설정
                dp[i] = Math.min(dp[2 * c - i], r - i);
            }

            // 이후 비교
            while (i - dp[i] - 1 >= 0 && i + dp[i] + 1 < extended.length()) {
                if (extended.charAt(i - dp[i] - 1) == extended.charAt(i + dp[i] + 1)) {
                    dp[i]++;
                } else {
                    break;
                }
            }

            // r, c 갱신
            if (r < dp[i] + i) {
                r = dp[i] + i;
                c = i;
            }
        }

        // dp에서 가장 큰 값, #으로 확장했기 때문에 2배 안해도 됨
        for (int i = 0; i < dp.length; i++) {
            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }

    private String extend(String s) {
        StringBuilder sb = new StringBuilder("#");
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i)).append("#");
        }

        return sb.toString();
    }
}
