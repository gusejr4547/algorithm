package 프로그래머스;

import java.util.ArrayDeque;

public class 네트워크 {
    public static void main(String[] args) {
        네트워크 Main = new 네트워크();
        int n = 3;
        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(Main.solution(n, computers));
    }

    public int solution(int n, int[][] computers) {
        int[] visit = new int[n];
        int count = 1;
        for (int i = 0; i < n; i++) {
            if (visit[i] == 0) {
                bfs(i, count, visit, computers);
                count++;
            }
        }
        return count - 1;
    }

    public void bfs(int start, int num, int[] visit, int[][] computers) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        int n = computers.length;
        while (!queue.isEmpty()) {
            int curr = queue.poll();

            if (visit[curr] == num) continue;
            visit[curr] = num;

            for (int i = 0; i < n; i++) {
                if (computers[curr][i] == 1) {
                    queue.offer(i);
                }
            }
        }
    }
}
