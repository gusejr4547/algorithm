package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2930_힙 {
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            int N = Integer.parseInt(br.readLine());

            MaxHeap heap = new MaxHeap();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int op = Integer.parseInt(st.nextToken());
                if (op == 1) {
                    int value = Integer.parseInt(st.nextToken());
                    heap.offer(value);
                } else if (op == 2) {
                    if (heap.isEmpty()) {
                        sb.append(-1).append(" ");
                    } else {
                        sb.append(heap.poll()).append(" ");
                    }
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static class MaxHeap {
        int[] arr;
        int cnt = 0;

        MaxHeap() {
            arr = new int[100001];
        }

        public void offer(int n) {
            arr[++cnt] = n;
            int node = cnt;
            while (node > 1) {
                int parent = getParent(node);
                if (arr[parent] < arr[node]) {
                    int temp = arr[parent];
                    arr[parent] = arr[node];
                    arr[node] = temp;
                    node = parent;
                } else {
                    break;
                }
            }
        }

        public int poll() {
            int value = arr[1];

            arr[1] = arr[cnt];
            arr[cnt] = 0;
            cnt--;

            heapify();

            return value;
        }

        public boolean isEmpty() {
            return cnt == 0;
        }

        private void heapify() {
            int node = 1;
            while (getRight(node) <= cnt) {
                int left = getLeft(node);
                int right = getRight(node);
                int max = node;

                if (arr[max] < arr[left]) {
                    max = left;
                }
                if (arr[max] < arr[right]) {
                    max = right;
                }

                if (max != node) {
                    int temp = arr[max];
                    arr[max] = arr[node];
                    arr[node] = temp;
                    node = max;
                } else {
                    break;
                }
            }
        }


        private int getLeft(int node) {
            return node * 2;
        }

        private int getRight(int node) {
            return node * 2 + 1;
        }

        private int getParent(int node) {
            return node / 2;
        }

    }
}
