package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class P17255_N으로_만들기 {
    static HashSet<String> record;
    static char[] numArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        numArr = br.readLine().toCharArray();
        record = new HashSet<>();

        for (int i = 0; i < numArr.length; i++) {
            dfs(i, i, numArr[i] + "", numArr[i] + "");
        }

        System.out.println(record.size());
    }

    public static void dfs(int left, int right, String num, String path) {
        if (left == 0 && right == numArr.length - 1) {
            record.add(path);
            return;
        }
        if (left > 0) {
            String next = numArr[left - 1] + num;
            dfs(left - 1, right, next, path + next);
        }
        if (right < numArr.length - 1) {
            String next = num + numArr[right + 1];
            dfs(left, right + 1, next, path + next);
        }
    }
}
