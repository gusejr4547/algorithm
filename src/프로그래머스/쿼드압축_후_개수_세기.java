package 프로그래머스;

import java.util.Arrays;

public class 쿼드압축_후_개수_세기 {
    public static void main(String[] args) {
        쿼드압축_후_개수_세기 Main = new 쿼드압축_후_개수_세기();
        int[][] arr = {{1, 1, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 1}, {1, 1, 1, 1}};
        System.out.println(Arrays.toString(Main.solution(arr)));
    }

    int[] answer;
    int[][] arr;

    public int[] solution(int[][] arr) {
        answer = new int[2];
        this.arr = arr;
        int n = arr.length;
        dfs(0, 0, n);

        return answer;
    }

    private void dfs(int r, int c, int size) {
        int cnt = 0;
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (arr[i][j] == 1) {
                    cnt++;
                }
            }
        }

        int total = size * size;

        // 같음.
        if (cnt == total || cnt == 0) {
            if (cnt > 0) {
                answer[1]++;
            } else {
                answer[0]++;
            }
            return;
        }
        // 다름 => dfs
        else {
            dfs(r, c, size / 2);
            dfs(r + size / 2, c, size / 2);
            dfs(r, c + size / 2, size / 2);
            dfs(r + size / 2, c + size / 2, size / 2);
        }
    }
}
