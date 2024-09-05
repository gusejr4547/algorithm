package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 트리의_순회 {
    static int[] inOrder, postOrder, preOrder;
    static int idx;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        inOrder = new int[n];
        postOrder = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }

        // pre : 가운데, 왼쪽, 오른쪽
        // in : 왼, 가운데, 오
        // post : 왼, 오, 가운데
        idx = 0;
        preOrder = new int[n];
        int root = postOrder[n - 1];
        preOrder[0] = root;

        getPreOrder(0, n - 1, 0, n - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(preOrder[i]).append(" ");
        }

        System.out.println(sb.toString());
    }

    private static void getPreOrder(int is, int ie, int ps, int pe) {
        if (is <= ie && ps <= pe) {
            int root = postOrder[pe];
            preOrder[idx] = root;
            idx++;

            int mid = 0;
            for (int i = is; i <= ie; i++) {
                if (inOrder[i] == root) {
                    mid = i;
                    break;
                }
            }

            getPreOrder(is, mid - 1, ps, ps + (mid - 1 - is));
            getPreOrder(mid + 1, ie, ps + (mid - is), pe - 1);
        }
    }
}
