package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P14719_빗물 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] heights = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        int[] leftMax = new int[W];
        int[] rightMax = new int[W];

        for (int i = 1; i < W; i++) {
            if (i == 1) leftMax[i] = heights[i - 1];
            else leftMax[i] = Math.max(leftMax[i - 1], heights[i - 1]);
        }

        for (int i = W - 2; i >= 0; i--) {
            if (i == W - 2) rightMax[i] = heights[i + 1];
            else {
                rightMax[i] = Math.max(rightMax[i + 1], heights[i + 1]);
            }
        }

//        System.out.println(Arrays.toString(leftMax));
//        System.out.println(Arrays.toString(rightMax));

        int answer = 0;
        for (int i = 1; i < W - 1; i++) {
            int left = leftMax[i];
            int right = rightMax[i];
            int height = heights[i];

            int max = Math.min(left, right);

            if (height < max) {
                answer += max - height;
            }
        }

        System.out.println(answer);
    }
}
