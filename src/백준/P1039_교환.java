package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P1039_교환 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char[] nArr = st.nextToken().toCharArray();
        int N = Integer.parseInt(new String(nArr));
        int K = Integer.parseInt(st.nextToken());

        solution(N, K);
    }

    private static void solution(int n, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> nextPQ = new PriorityQueue<>(Comparator.reverseOrder());
        pq.offer(n);
        Set<Integer> made = new HashSet<>();

        for (int phase = 0; phase < k; phase++) {
            made.clear();
            while (!pq.isEmpty()) {
                int num = pq.poll();

                char[] arr = String.valueOf(num).toCharArray();
                // num에 대한 모든 교환
                for (int i = 0; i < arr.length - 1; i++) {
                    for (int j = i + 1; j < arr.length; j++) {
                        if (i == 0 && arr[j] == '0') {
                            continue;
                        }
                        // 교환
                        char tmp = arr[j];
                        arr[j] = arr[i];
                        arr[i] = tmp;
                        int nextNum = Integer.parseInt(String.valueOf(arr));
                        if (!made.contains(nextNum)) {
                            made.add(nextNum);
                            nextPQ.offer(nextNum);
                        }
                        // 되돌리기
                        arr[i] = arr[j];
                        arr[j] = tmp;
                    }
                }

            }
            pq = new PriorityQueue<>(nextPQ);
            nextPQ.clear();
        }

        if (pq.isEmpty()) {
            System.out.println(-1);
        } else {
            System.out.println(pq.peek());
        }
    }
}
