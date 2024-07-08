package 프로그래머스;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 호텔_대실 {
    public static void main(String[] args) {
        호텔_대실 Main = new 호텔_대실();

        String[][] book_time = {{"15:00", "17:00"}, {"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
        System.out.println(Main.solution(book_time));
    }

    // 퇴실하고 10분 청소시간
    public int solution(String[][] book_time) {
        // hh:mm -> 분으로 변경
        int[][] bookTime = toMinute(book_time);

        Arrays.sort(bookTime, (o1, o2) -> o1[0] - o2[0]);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        for (int[] book : bookTime) {
            if (pq.isEmpty()) {
                pq.offer(book);
            } else {
                int[] room = pq.peek();

                if (book[0] >= room[1]) {
                    pq.poll();
                }
                pq.offer(book);
            }
        }

        return pq.size();
    }

    private int[][] toMinute(String[][] book_time) {
        int[][] result = new int[book_time.length][2];

        for (int i = 0; i < book_time.length; i++) {
            result[i][0] = hourToMinute(book_time[i][0]);
            result[i][1] = hourToMinute(book_time[i][1]) + 10; // 청소시간 +10
        }

        return result;
    }

    private int hourToMinute(String hour) {
        String[] time = hour.split(":");
        return Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
    }
}
