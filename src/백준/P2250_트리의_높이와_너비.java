package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2250_트리의_높이와_너비 {
    static Node[] tree;
    static int col, maxLevel;
    static int[] minCol, maxCol;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new Node[N + 1];
        col = 1;
        minCol = new int[N + 1]; // level의 최소열
        maxCol = new int[N + 1]; // level의 최대열
        for (int i = 1; i <= N; i++) {
            tree[i] = new Node(0, 0, 0);
            minCol[i] = Integer.MAX_VALUE;
            maxCol[i] = Integer.MIN_VALUE;
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int nNum = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            tree[nNum].left = left;
            tree[nNum].right = right;

            // 부모
            if (left != -1) {
                tree[left].parent = nNum;
            }
            if (right != -1) {
                tree[right].parent = nNum;
            }
        }

        int rootNode = -1;
        // 루트 노드는 parent가 없는 노드
        for (int i = 1; i <= N; i++) {
            if (tree[i].parent == 0) {
                rootNode = i;
                break;
            }
        }

        // 중위순회
        // 각 노드의 level과 열의 위치를 저장한다
        inOrder(rootNode, 1);

        // 출력
        int answerLevel = 1;
        int maxWidth = 0;

        for (int level = 1; level <= maxLevel; level++) {
            int width = maxCol[level] - minCol[level] + 1;

            if (maxWidth < width) {
                maxWidth = width;
                answerLevel = level;
            }
        }

        System.out.println(answerLevel + " " + maxWidth);
    }

    private static void inOrder(int node, int level) {
        // 왼쪽
        if (tree[node].left != -1) {
            inOrder(tree[node].left, level + 1);
        }

        // 현재노드
        // level의 최소열 갱신
        minCol[level] = Math.min(minCol[level], col);
        // level의 최대열 갱신
        maxCol[level] = Math.max(maxCol[level], col);
        // 다음 열
        col++;

        maxLevel = Math.max(maxLevel, level);

        // 오른쪽
        if (tree[node].right != -1) {
            inOrder(tree[node].right, level + 1);
        }
    }

    private static class Node {
        int left, right, parent;

        public Node(int left, int right, int parent) {
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }
}
