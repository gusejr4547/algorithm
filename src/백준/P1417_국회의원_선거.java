package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class P1417_국회의원_선거 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int myTicket = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 1; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int answer = 0;
        while (!pq.isEmpty() && pq.peek() >= myTicket) {
            pq.offer(pq.poll() - 1);
            answer++;
            myTicket++;
        }

        System.out.println(answer);
    }
}
