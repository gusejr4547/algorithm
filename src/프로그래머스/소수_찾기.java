package 프로그래머스;

import java.util.*;

public class 소수_찾기 {
    int N;
    HashSet<Integer> numSet;
    boolean[] visit;

    public int solution(String numbers) {
        N = numbers.length();
        numSet = new HashSet<>();
        visit = new boolean[N];

        dfs(numbers, "", 0);

        int answer = 0;

        for (int num : numSet) {
            if (isPrime(num)) {
                answer++;
            }
        }

        return answer;
    }

    private boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    private void dfs(String numbers, String s, int depth) {
        if (depth == N) {
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visit[i]) {
                visit[i] = true;
                // 추가
                numSet.add(Integer.parseInt(s + numbers.charAt(i)));
                dfs(numbers, s + numbers.charAt(i), depth + 1);
                visit[i] = false;
            }
        }
    }
}
