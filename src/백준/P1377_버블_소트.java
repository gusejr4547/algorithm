package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P1377_버블_소트 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Node[] arr = new Node[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new Node(Integer.parseInt(br.readLine()), i);
        }

        Arrays.sort(arr, (o1, o2) -> o1.num - o2.num);

        int maxFrontMove = 0;
        for (int i = 0; i < N; i++) {
            maxFrontMove = Math.max(maxFrontMove, arr[i].idx - i);
        }

        System.out.println(maxFrontMove + 1);
    }

    static class Node {
        int num, idx;

        public Node(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }
}
