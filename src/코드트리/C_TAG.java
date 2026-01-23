package 코드트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class C_TAG {
    static int N, M;
    static char[][] A, B;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new char[N][M];
        for (int i = 0; i < N; i++) {
            A[i] = br.readLine().toCharArray();
        }
        B = new char[N][M];
        for (int i = 0; i < N; i++) {
            B[i] = br.readLine().toCharArray();
        }

        // 3개의 자리를 선택해서 A 그룹에서 나오는 문자와 B그룹에서 나오는 문자가 전부 다른 경우의 수
        // N <= 500
        // M <= 50

        // List<Set<String>> setList = new ArrayList<>();

        // 50C3 = 50 49 48 / 6
        // A로 모든 경우의 수 만들기
        long answer = 0;
        for (int i = 0; i < M; i++) {
            for (int j = i + 1; j < M; j++) {
                for (int k = j + 1; k < M; k++) {
                    Set<String> set = new HashSet<>();
                    for (int l = 0; l < N; l++) {
                        set.add("" + A[l][i] + A[l][j] + A[l][k]);
                    }

                    if (check(i, j, k, set)) {
                        answer++;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean check(int i, int j, int k, Set<String> set) {
        for (int l = 0; l < N; l++) {
            String str = "" + B[l][i] + B[l][j] + B[l][k];
            if (set.contains(str)) {
                return false;
            }
        }
        return true;
    }
}
