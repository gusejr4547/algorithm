package 프로그래머스.카카오_2025_하반기_1차;

public class 리프_노드_수_최대화 {
    public static void main(String[] args) {
        리프_노드_수_최대화 Main = new 리프_노드_수_최대화();
        int dist_limit = 3;
        int split_limit = 100;
        System.out.println(Main.solution(dist_limit, split_limit));
    }

    // 1. 루트 노드와 리프 노드를 제외한 나머지 노드를 분배 노드라고 하며, 분배 노드는 자식 노드를 2개 또는 3개를 갖습니다.
    // 2. 분배 노드는 최대 dist_limit개 존재
    // 3. 트리에서 같은 깊이에 있는 분배 노드의 자식 노드 수는 모두 같아야 합니다.
    // 4. 모든 리프 노드는 분배도라는 값을 갖습니다.
    // 분배도는 해당 리프 노드의 부모 노드에서 루트 노드까지의 최단 경로 상에 있는 모든 노드의 자식 노드 개수의 곱과 같습니다.
    // 5. 모든 리프 노드의 분배도는 split_limit보다 작거나 같아야 합니다.
    // 리프 노드를 가능한 한 많이 만들기

    // dist_limit <= 1_000_000_000
    // split_limit <= 1_000_000_000

    long max = 0;

    public int solution(int dist_limit, int split_limit) {
        // 기본으로 리프노드는 1이다.
        max = 1;

        dfs(1, dist_limit, 1, 1, split_limit);
        int answer = (int) max;
        return answer;
    }

    private void dfs(long currentDepthNode, long distLeft, long splitScore, long leafNodeCnt, long split_limit) {
        // 리프 노드 수 갱신
        max = Math.max(max, leafNodeCnt);

        // 분배노드 없으면 종료
        if (distLeft == 0) {
            return;
        }

        // 현재 깊이의 노드를 전부 분배노드로 변경을 못하는 경우 여기서 distLeft를 전부 사용하는게 이득이다.
        if (currentDepthNode >= distLeft) {
            // 자식노드 3개짜리
            if (splitScore * 3 <= split_limit) {
                max = Math.max(max, leafNodeCnt + distLeft * 2);
            }
            // 자식노드 2개짜리
            else if (splitScore * 2 <= split_limit) {
                max = Math.max(max, leafNodeCnt + distLeft);
            }
            return;
        }

        // 3개
        if (splitScore * 3 <= split_limit) {
            long use = currentDepthNode;
            dfs(use * 3, distLeft - use, splitScore * 3, leafNodeCnt + use * 2, split_limit);
        }

        // 2개
        if (splitScore * 2 <= split_limit) {
            long use = currentDepthNode;
            dfs(use * 2, distLeft - use, splitScore * 2, leafNodeCnt + use, split_limit);
        }
    }
}
