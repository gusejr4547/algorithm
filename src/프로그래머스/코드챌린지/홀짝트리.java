package 프로그래머스.코드챌린지;

import java.util.*;

/*
0은 짝수

노드 종류 -
홀수노드 노드의 번호가 "홀수"이며 자식 노드의 개수가 "홀수"인 노드
짝수노드 노드의 번호가 "짝수"이며 자식 노드의 개수가 "짝수"인 노드
역홀수노드 노드의 번호가 "홀수"이며 자식 노드의 개수가 "짝수"인 노드
역짝수노드 노드의 번호가 "짝수"이며 자식 노드의 개수가 홀수인 "노드"

홀짝 트리
홀수 노드와 짝수 노드로만 이루어진 트리입니다.
역홀짝 트리
역홀수 노드와 역짝수 노드로만 이루어진 트리입니다.

주어지는 그래프는 포레스트 그래프 >> 트리가 될수있는 그래프

홀짝 트리가 될 수 있는 트리의 개수와 역홀짝 트리가 될 수 있는 트리의 개수
 */
public class 홀짝트리 {
    public static void main(String[] args) {
        홀짝트리 Main = new 홀짝트리();
        int[] nodes = {11, 9, 3, 2, 4, 6};
        int[][] edges = {{9, 11}, {2, 3}, {6, 3}, {3, 4}};
        System.out.println(Arrays.toString(Main.solution(nodes, edges)));
    }

    // 하나의 트리가 홀짝 트리와 역홀짝 트리 두 가지 모두 될 수 있거나 두 가지 모두 될 수 없을 수도 있습니다.
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[2];

        // 간선 정리
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int node : nodes) {
            adj.put(node, new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // 트리 탐색 >> 일단 자식노드라고 생각하고 계산 >> 홀짝 트리인지 역홀짝 트리인지 계속 확인하다가 규칙에 안맞으면 루트노드로 설정하고 다음계산 >>>>
        Map<Integer, Boolean> visit = new HashMap<>(); // 노드 방문 확인

        for (int node : nodes) {
            if (visit.getOrDefault(node, false)) continue;

            TreeClassificationResult result = searchTree(node, visit, adj);
            if (result.isHoljjakTree) {
                answer[0]++;
            }
            if (result.isReverseHoljjakTree) {
                answer[1]++;
            }
        }

        return answer;
    }

    // 전부 루트노드가 아니라고 가정하고
    // 홀짝노드 >> 루트노드변경가능? 불가능하면 역홀짝트리x 왜? 홀짝노드가 포함되니까
    // 역홀짝노드 >>
    private TreeClassificationResult searchTree(int startNode, Map<Integer, Boolean> visit, Map<Integer, List<Integer>> adj) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(startNode);

        boolean isHoljjakTree = true;
        int holjjakTreeRoot = 0;

        boolean isReverseHoljjakTree = true;
        int reverseHoljjakTreeRoot = 0;


        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (visit.getOrDefault(cur, false)) continue;
            visit.put(cur, true);

            int rootChildrenCnt = adj.get(cur).size();
            int nonRootChildrenCnt = adj.get(cur).size() - 1;

            // 무슨 노드인지 판별
            if ((cur + nonRootChildrenCnt) % 2 == 0) {
                // 홀짝 트리 노드임.
                if (reverseHoljjakTreeRoot == 0) {
                    // 루트노드로 설정하고 계산해봄
                    reverseHoljjakTreeRoot++;
                    if ((cur + rootChildrenCnt) % 2 == 0) {
                        // 루트노드로 했을때 홀짝 트리 노드이면
                        isReverseHoljjakTree = false;
                    }
                } else {
                    isReverseHoljjakTree = false;
                }
            }
            if ((cur + nonRootChildrenCnt) % 2 == 1) {
                // 역홀짝 트리 노드임.
                if (holjjakTreeRoot == 0) {
                    holjjakTreeRoot++;
                    if ((cur + rootChildrenCnt) % 2 == 1) {
                        // 루트노드가 역홀짝 트리노드
                        isHoljjakTree = false;
                    }
                } else {
                    isHoljjakTree = false;
                }
            }

            for (int next : adj.get(cur)) {
                if (visit.getOrDefault(next, false)) continue;

                queue.offer(next);
            }
        }

        if (isHoljjakTree && holjjakTreeRoot == 0) {
            isHoljjakTree = false;
        }
        if (isReverseHoljjakTree && reverseHoljjakTreeRoot == 0) {
            isReverseHoljjakTree = false;
        }

        return new TreeClassificationResult(isHoljjakTree, isReverseHoljjakTree);
    }

    class TreeClassificationResult {
        boolean isHoljjakTree, isReverseHoljjakTree;

        public TreeClassificationResult(boolean isHoljjakTree, boolean isReverseHoljjakTree) {
            this.isHoljjakTree = isHoljjakTree;
            this.isReverseHoljjakTree = isReverseHoljjakTree;
        }
    }
}
