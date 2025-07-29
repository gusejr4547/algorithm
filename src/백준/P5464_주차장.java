package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P5464_주차장 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] fee = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            fee[i] = Integer.parseInt(br.readLine());
        }
        int[] weight = new int[M + 1];
        for (int i = 1; i <= M; i++) {
            weight[i] = Integer.parseInt(br.readLine());
        }

        ArrayDeque<Integer> waiting = new ArrayDeque<>();
        PriorityQueue<Integer> emptyPlace = new PriorityQueue<>((o1, o2) -> o1 - o2);
        for (int i = 1; i <= N; i++) {
            emptyPlace.offer(i);
        }

        int[] car = new int[M + 1];

        int answer = 0;

        for (int i = 0; i < M * 2; i++) {
            int inout = Integer.parseInt(br.readLine());
            if (inout > 0) {
                if (!emptyPlace.isEmpty()) {
                    int p = emptyPlace.poll();
                    car[inout] = p;
                } else {
                    waiting.offer(inout);
                }
            } else if (inout < 0) {
                inout *= -1;
                int p = car[inout];
                answer += fee[p] * weight[inout];

                emptyPlace.offer(p);

                if (!waiting.isEmpty()) {
                    int num = waiting.poll();
                    emptyPlace.poll();
                    car[num] = p;
                }
            }
        }

        System.out.println(answer);
    }
}
