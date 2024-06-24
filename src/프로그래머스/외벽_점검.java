package 프로그래머스;

import java.util.ArrayList;
import java.util.List;

public class 외벽_점검 {
    public static void main(String[] args) {
        외벽_점검 Main = new 외벽_점검();
        int n = 12;
        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};
        System.out.println(Main.solution(n, weak, dist));
    }

    //빠른 공사 진행을 위해 점검 시간을 1시간으로 제한
    //최소한의 친구들을 투입
    List<int[]> weakOrderList;
    int answer;

    public int solution(int n, int[] weak, int[] dist) {
        weakOrderList = new ArrayList<>();
        answer = Integer.MAX_VALUE;
        // 각 취약점에서 다른 취약점 까지 거리

        // 취약점 순서 정하기
        makeWeakOrder(weak, n);

        // 친구들 투입 순서 정하기
        makeDistOrder(0, new int[dist.length], new boolean[dist.length], dist);

        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }

        return answer;
    }

    public int work(int[] weakOrder, int[] distOrder) {
        int curWeakIdx = 0;

        for (int i = 0; i < distOrder.length; i++) {
            int nextPos = weakOrder[curWeakIdx] + distOrder[i];
            while (curWeakIdx < weakOrder.length) {
                if (nextPos < weakOrder[curWeakIdx]) {
                    break;
                }
                curWeakIdx++;
            }
            if (curWeakIdx == weakOrder.length) {
                return i + 1;
            }
        }
        return -1;
    }

    // 순열
    public void makeDistOrder(int idx, int[] distOrder, boolean[] useDist, int[] dist) {
        if (idx == dist.length) {
            // 현재 dist 순서로 취약점 탐색
            for (int[] weakOrder : weakOrderList) {
                int res = work(weakOrder, distOrder);
                if (res != -1) {
                    answer = Math.min(answer, res);
                }
            }
        }
        for (int i = 0; i < dist.length; i++) {
            if (!useDist[i]) {
                useDist[i] = true;
                distOrder[idx] = dist[i];
                makeDistOrder(idx + 1, distOrder, useDist, dist);
                useDist[i] = false;
            }
        }
    }

    public void makeWeakOrder(int[] weak, int n) {
        int[] weakOrder = new int[weak.length];
        for (int i = 0; i < weak.length; i++) {
            for (int next = 0; next < weak.length; next++) {
                int nextIdx = (i + next) % weak.length;
                weakOrder[next] = i <= nextIdx ? weak[nextIdx] : weak[nextIdx] + n;
            }
            weakOrderList.add(weakOrder.clone());
        }
    }
}
