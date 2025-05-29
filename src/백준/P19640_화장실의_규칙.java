package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P19640_화장실의_규칙 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayDeque<Person>[] line = new ArrayDeque[M];
        for (int i = 0; i < M; i++) {
            line[i] = new ArrayDeque<>();
        }

        PriorityQueue<Person> waitingQueue = new PriorityQueue<>((o1, o2) -> {
            if (o1.workDay == o2.workDay) {
                if (o1.hurryValue == o2.hurryValue) {
                    return Integer.compare(o1.lineNum, o2.lineNum);
                }
                return Integer.compare(o2.hurryValue, o1.hurryValue);
            }
            return Integer.compare(o2.workDay, o1.workDay);
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            line[i % M].offer(new Person(i, i % M, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        for (int i = 0; i < M; i++) {
            if (!line[i].isEmpty()) {
                waitingQueue.offer(line[i].poll());
            }
        }

        int answer = 0;
        while (!waitingQueue.isEmpty()) {
            Person next = waitingQueue.poll();
            if (next.order == K) {
                break;
            }
            answer++;

            if (!line[next.lineNum].isEmpty()) {
                waitingQueue.offer(line[next.lineNum].poll());
            }
        }

        System.out.println(answer);
    }

    private static class Person {
        int order, lineNum;
        int workDay, hurryValue;

        public Person(int order, int lineNum, int workDay, int hurryValue) {
            this.order = order;
            this.lineNum = lineNum;
            this.workDay = workDay;
            this.hurryValue = hurryValue;
        }
    }
}
