package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P12026_BOJ_거리 {
    static int N;
    static String str;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        str = br.readLine();

        int[] energy = new int[N];
        Arrays.fill(energy, Integer.MAX_VALUE);
        energy[0] = 0;

        for (int i = 0; i < N - 1; i++) {
            char c = str.charAt(i);
            if (energy[i] == Integer.MAX_VALUE) {
                continue;
            }
            for (int j = i + 1; j < N; j++) {
                if (c == 'B' && str.charAt(j) == 'O') {
                    energy[j] = Math.min(energy[j], energy[i] + (j - i) * (j - i));
                }
                if (c == 'O' && str.charAt(j) == 'J') {
                    energy[j] = Math.min(energy[j], energy[i] + (j - i) * (j - i));
                }
                if (c == 'J' && str.charAt(j) == 'B') {
                    energy[j] = Math.min(energy[j], energy[i] + (j - i) * (j - i));
                }
            }
        }
//        System.out.println(Arrays.toString(energy));
        System.out.println(energy[N - 1] == Integer.MAX_VALUE ? -1 : energy[N - 1]);
    }
}
