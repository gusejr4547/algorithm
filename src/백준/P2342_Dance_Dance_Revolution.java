package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P2342_Dance_Dance_Revolution {
    static List<Integer> move;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        move = new ArrayList<>();

        while (st.hasMoreTokens()) {
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) break;
            move.add(n);
        }

        dp = new int[5][5][move.size()];

        System.out.println(DDR(0, 0, 0));
    }

    public static int DDR(int left, int right, int step) {
        if (step == move.size())
            return 0;

        if (dp[left][right][step] != 0)
            return dp[left][right][step];

        int leftMove = energy(left, move.get(step)) + DDR(move.get(step), right, step + 1);
        int rightMove = energy(right, move.get(step)) + DDR(left, move.get(step), step + 1);

        return dp[left][right][step] = Math.min(leftMove, rightMove);
    }

    public static int energy(int curr, int next) {
        int diff = Math.abs(curr - next);
        if (curr == 0) return 2;
        else if (diff == 0) return 1;
        else if (diff == 1 || diff == 3) return 3;
        else return 4;
    }
}
