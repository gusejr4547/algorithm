package 프로그래머스;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 순위 {
    public static void main(String[] args) {
        순위 Main = new 순위();
        int n = 5;
        int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        System.out.println(Main.solution(n, results));
    }

    public int solution(int n, int[][] results) {
        int answer = n;

        int[][] graph = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                graph[i][j] = n;
                if (i == j) graph[i][j] = 0;
            }
        }

        for (int[] result : results) {
            graph[result[0]][result[1]] = 1;
            graph[result[1]][result[0]] = -1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= n; j++) {
                    if (graph[i][k] == 1 && graph[k][j] == 1) {
                        graph[i][j] = 1;
                        graph[j][i] = -1;
                    }
                }
            }
        }

//        for (int[] arr : graph) {
//            System.out.println(Arrays.toString(arr));
//        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] == n) {
                    answer--;
                    break;
                }
            }
        }

        return answer;
    }
}
