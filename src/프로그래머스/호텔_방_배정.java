package 프로그래머스;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 호텔_방_배정 {
    public static void main(String[] args) {
        호텔_방_배정 Main = new 호텔_방_배정();
        long k = 1000000;
//        long[] room_number = {1, 3, 4, 1, 3, 1};

        long[] room_number = new long[200_000];
        for (int i = 0; i < 100000; i++) {
            room_number[i] = 1;
        }
        int v = 2;
        for (int i = 100000; i < 200000; i++) {
            room_number[i] = v;
            v++;
        }
        long start = System.currentTimeMillis();
        System.out.println(Arrays.toString(Main.solution(k, room_number)));
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000);
    }

    Map<Long, Long> parentMap;

    public long[] solution(long k, long[] room_number) {
        // k 전체방 개수
        long[] answer = new long[room_number.length];
        parentMap = new HashMap<>();

        for (int i = 0; i < room_number.length; i++) {
            long want = room_number[i];

            // map에 want가 없으면 빈방
            answer[i] = find(want);
//            if (!parentMap.containsKey(want)) {
//                answer[i] = want;
//                union(want, want + 1);
//            } else {
//                // map을 통해 들어갈 수 있는 방을 찾는다.
//                long next = find(want);
//                answer[i] = next;
//                union(next, next + 1);
//            }
        }

        return answer;
    }

    private long find(long x) {
        if (!parentMap.containsKey(x)) {
            parentMap.put(x, x + 1);
            return x;
        }
        long next = find(parentMap.get(x));
        parentMap.put(x, next + 1);
        return next;
    }

    private void union(long x, long y) {
        long pX = find(x);
        long pY = find(y);

        if (pX < pY) {
            parentMap.put(pX, pY);
        } else if (pX > pY) {
            parentMap.put(pY, pX);
        }
    }
}
