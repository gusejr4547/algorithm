package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P12931_두_배_더하기 {
    static int N;
    static int[] end;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        end = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            end[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(make());
    }

    private static int make() {
        int count = 0;

        while (!isZero()) {
            // 홀수 짝수로 변경
            for (int i = 0; i < N; i++) {
                if (end[i] % 2 != 0) {
                    end[i]--;
                    count++;
                }
            }

            if (isZero()) {
                break;
            }

            // 2로 나누기
            for (int i = 0; i < N; i++) {
                end[i] /= 2;
            }
            count++;
        }
        return count;
    }

    private static boolean isZero() {
        for (int i = 0; i < N; i++) {
            if (end[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
