package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P34928_PAUL_문자열_Hard {
    static int N;
    static String str;
    static List<Integer>[] idxList;
    static boolean answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        str = br.readLine();

        idxList = new List[4];
        for (int i = 0; i < 4; i++) {
            idxList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            if ('P' == str.charAt(i)) {
                idxList[0].add(i);
            } else if ('A' == str.charAt(i)) {
                idxList[1].add(i);
            } else if ('U' == str.charAt(i)) {
                idxList[2].add(i);
            } else if ('L' == str.charAt(i)) {
                idxList[3].add(i);
            }
        }

        // P A L U 조합...
        // P 가능?
        boolean[] canMake1 = new boolean[idxList[0].size()];
        for (int i = 0; i < idxList[0].size(); i++) {
            if (idxList[0].get(i) % 2 == 0) {
                canMake1[i] = true;
            }
        }
        boolean[] canMake2 = new boolean[idxList[1].size()];
        for (int i = 0; i < idxList[1].size(); i++) {
            int cur = idxList[1].get(i);
            for (int j = 0; j < idxList[0].size(); j++) {
                int prev = idxList[0].get(j);
                if (canMake1[j] && prev < cur) {
                    if ((cur - prev - 1) % 2 == 0) {
                        canMake2[i] = true;
                        break;
                    }
                }
            }
        }

        boolean[] canMake3 = new boolean[idxList[2].size()];
        for (int i = 0; i < idxList[2].size(); i++) {
            int cur = idxList[2].get(i);
            for (int j = 0; j < idxList[1].size(); j++) {
                int prev = idxList[1].get(j);
                if (canMake2[j] && prev < cur) {
                    if ((cur - prev - 1) % 2 == 0) {
                        canMake3[i] = true;
                        break;
                    }
                }
            }
        }

        boolean[] canMake4 = new boolean[idxList[3].size()];
        for (int i = 0; i < idxList[3].size(); i++) {
            int cur = idxList[3].get(i);
            for (int j = 0; j < idxList[2].size(); j++) {
                int prev = idxList[2].get(j);
                if (canMake3[j] && prev < cur) {
                    if ((cur - prev - 1) % 2 == 0) {
                        canMake4[i] = true;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < idxList[3].size(); i++) {
            if (canMake4[i] && (N - idxList[3].get(i) - 1) % 2 == 0) {
                answer = true;
                break;
            }
        }

        System.out.println(answer ? "YES" : "NO");
    }

    private static void dfs(int PAULIdx, int prev) {
        // 조기 종료
        if (answer) {
            return;
        }

        // 마지막
        if (PAULIdx == 4) {
            // 거리가 2의 배수?
            if ((N - prev) % 2 == 0) {
                answer = true;
            }
            return;
        }

        for (int idx : idxList[PAULIdx]) {
            if (prev > idx) {
                continue;
            }

            // 거리가 2의 배수?
            if ((idx - prev) % 2 == 0) {
                dfs(PAULIdx + 1, idx + 1);
            }

            // 조기 종료
            if (answer) {
                return;
            }
        }
    }
}
