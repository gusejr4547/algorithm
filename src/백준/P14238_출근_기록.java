package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P14238_출근_기록 {
    static String answer;
    static int len;
    static boolean[][][][][] visit;
    static int[] cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        len = S.length();
        cnt = new int[3];
        for (char s : S.toCharArray()) {
            if (s == 'A') {
                cnt[0]++;
            } else if (s == 'B') {
                cnt[1]++;
            } else {
                cnt[2]++;
            }
        }

        answer = "";
        visit = new boolean[cnt[0] + 1][cnt[1] + 1][cnt[2] + 1][4][4];
        // A 매일 출근 가능, B 출근 다음날 쉬기, C 출근 다음, 다다음 쉬기
        find(0, 0, 0, 0, 0, 0, new char[len]);

        System.out.println(answer.length() == 0 ? "-1" : answer);
    }

    private static void find(int length, int a, int b, int c, int prev, int prevPrev, char[] arr) {
        if (answer.length() != 0) {
            return;
        }

//        System.out.println(new String(arr));

        if (length == len) {
            answer = new String(arr);
            return;
        }

        if (visit[a][b][c][prevPrev][prev]) {
            return;
        }
        visit[a][b][c][prevPrev][prev] = true;


        if (a < cnt[0]) {
            arr[length] = 'A';
            find(length + 1, a + 1, b, c, 0, prev, arr);
        }

        if (b < cnt[1] && prev != 1) {
            arr[length] = 'B';
            find(length + 1, a, b + 1, c, 1, prev, arr);
        }

        if (c < cnt[2] && prev != 2 && prevPrev != 2) {
            arr[length] = 'C';
            find(length + 1, a, b, c + 1, 2, prev, arr);
        }
    }
}
