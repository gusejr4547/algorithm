package 코드트리.HSAT;

import java.util.ArrayDeque;
import java.util.Scanner;

public class 업무_처리 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int H = sc.nextInt();
        int K = sc.nextInt();
        int R = sc.nextInt();
        int leafCount = (int) Math.pow(2, H);

        int leafNodeNum = 1 << H;

        ArrayDeque<Integer>[][] taskQ = new ArrayDeque[(1 << (H + 1))][2];
        for (int i = 0; i < taskQ.length; i++) {
            taskQ[i][0] = new ArrayDeque<>();
            taskQ[i][1] = new ArrayDeque<>();
        }

        for (int i = 0; i < leafCount; i++) {
            for (int j = 0; j < K; j++) {
                int task = sc.nextInt();
                taskQ[leafNodeNum + i][0].offer(task);
            }
        }

        int answer = 0;
        for (int t = 0; t < R; t++) {
            int w = t % 2;

            // 처리 완료
            if (!taskQ[1][w].isEmpty()) {
                answer += taskQ[1][w].poll();
            }

            // 위에 노드
            for (int node = 2; node < (1 << H); node++) {
                if (!taskQ[node][w].isEmpty()) {
                    int task = taskQ[node][w].poll();
                    if (node % 2 == 0) {
                        taskQ[node / 2][0].offer(task);
                    } else {
                        taskQ[node / 2][1].offer(task);
                    }
                }
            }

            // 리프 노드
            for (int node = 1 << H; node < taskQ.length; node++) {
                if (!taskQ[node][0].isEmpty()) {
                    int task = taskQ[node][0].poll();
                    if (node % 2 == 0) {
                        taskQ[node / 2][0].offer(task);
                    } else {
                        taskQ[node / 2][1].offer(task);
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
