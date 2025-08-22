package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P31662_Card_Counting_Club {
    static int N, M, P;
    static String[] names;
    static PriorityQueue<Integer>[] cards;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        names = new String[N];
        cards = new PriorityQueue[N];
        for (int i = 0; i < N; i++) {
            cards[i] = new PriorityQueue<>((o1, o2) -> o1 - o2);
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            names[i] = st.nextToken();
            for (int j = 0; j < M; j++) {
                cards[i].offer(Integer.parseInt(st.nextToken()));
            }
        }

        StringBuilder sb = new StringBuilder();
        long player = 0;
        long endMask = (1L << N) - 1;
        while (player != endMask) {
            int win = -1;
            int value = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                if (!cards[i].isEmpty()) {
                    int c = cards[i].poll();
                    if (win == -1) {
                        win = i;
                        value = c;
                    } else {
                        if (value > c) {
                            cards[win].offer(value + P);
                            win = i;
                            value = c;
                        } else if (value == c) {
                            // 음수면 i가 더 작음
                            if (names[i].compareTo(names[win]) < 0) {
                                cards[win].offer(value + P);
                                win = i;
                            } else {
                                cards[i].offer(c + P);
                            }
                        } else {
                            cards[i].offer(c + P);
                        }
                    }
                }
            }
            if (cards[win].isEmpty()) {
                player |= (1L << win);
                sb.append(names[win]).append(" ");
            }
        }

        System.out.println(sb);
    }
}
