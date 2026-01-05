package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P34929_중앙값의_함정 {
    // 칠판에 적힌 세 수를 골라 지우고 세 수의 중앙값을 쓴다 => 반복
    // 마지막 남은 수가 최대.
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 홀수로 주어짐
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.naturalOrder());
        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(st.nextToken()));
        }

        StringBuilder sb = new StringBuilder();
        // 숫자 작은거를 먼저 없애기
        while (pq.size() > 1) {
            // 3개 뽑아서
            sb.append(pq.poll()).append(' ');
            Integer median = pq.poll();
            sb.append(median).append(' ');
            sb.append(pq.poll()).append('\n');
            // 중앙값 다시 넣기
            pq.offer(median);
        }

        // 출력
        // 첫째 줄에 칠판에 마지막으로 남은 정수의 최댓값을 출력한다.
        // 둘째 줄부터 마지막으로 남은 정수가 최대가 되도록 하는 시행을 한 줄에 하나씩 순서대로 출력한다.
        // 각 시행은 시행에서 고른 세 수를 순서에 관계없이 공백으로 구분해 출력한다. 그런 방법이 여러 개라면 그중 아무거나 출력한다.
        System.out.println(pq.poll());
        System.out.println(sb);
    }
}
