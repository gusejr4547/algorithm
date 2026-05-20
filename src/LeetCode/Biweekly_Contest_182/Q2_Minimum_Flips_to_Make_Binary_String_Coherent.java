package LeetCode.Biweekly_Contest_182;

public class Q2_Minimum_Flips_to_Make_Binary_String_Coherent {
    // "011" or "110" 를 subsequences로 가지지 않으면 coherent하다
    // 나는 0->1 1->0 으로 변경할 수 있다.
    // 최소 변경으로 coherent하게 만들기

    // s.length <= 100 000

    public int minFlips(String s) {
        int N = s.length();
        int count0 = 0;
        int count1 = 0;
        for (int i = 0; i < N; i++) {
            if (s.charAt(i) == '0') {
                count0++;
            } else {
                count1++;
            }
        }
        int answer = Integer.MAX_VALUE;
        // 1로만 이루어짐
        answer = Math.min(answer, count0);

        // 1이 최대 1개
        answer = Math.min(answer, Math.max(0, count1 - 1));

        // 양끝이 1이고 중간이 전부 0
        if (N >= 2) {
            int cnt = 0;

            if (s.charAt(0) == '0') {
                cnt++;
            }
            if (s.charAt(N - 1) == '0') {
                cnt++;
            }

            for (int i = 1; i < N - 1; i++) {
                if (s.charAt(i) == '1') {
                    cnt++;
                }
            }
            answer = Math.min(answer, cnt);
        }

        return answer;
    }
}
