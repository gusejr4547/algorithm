package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P1256_K번째_접미어 {
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int k = Integer.parseInt(br.readLine());
            String word = br.readLine();

            String[] arr = new String[word.length()];
            for (int i = 0; i < word.length(); i++) {
                arr[i] = word.substring(i);
            }

            Arrays.sort(arr);

            sb.append("#").append(tc).append(" ");

            if (k > word.length()) {
                sb.append("none");
            } else {
                sb.append(arr[k - 1]);
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }
}
