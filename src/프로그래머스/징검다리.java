package 프로그래머스;

import java.util.Arrays;

public class 징검다리 {
    public static void main(String[] args) {
        징검다리 Main = new 징검다리();
        int distance = 25;
        int[] rocks = {2, 14, 11, 21, 17};
        int n = 2;
        System.out.println(Main.solution(distance, rocks, n));
    }

    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        // rocks 위치 정렬
        Arrays.sort(rocks);

        int[] rockDist = new int[rocks.length + 1];
        int start = 0;
        int idx = 0;
        while (start < distance) {
            rockDist[idx] = rocks[idx] - start;
            start = rocks[idx];
            idx++;

            if (idx >= rocks.length) {
                rockDist[idx] = distance - start;
                break;
            }
        }

        int maxDist = distance;
        int minDist = 0;
        while (minDist <= maxDist) {
            int mid = (maxDist + minDist) / 2;

            // 최소 거리가 mid가 나오려면 몇개 바위 제거?
            int deleteCnt = 0;
            start = 0;
            idx = 0;
            while (true) {
                int dist = rocks[idx] - start;
                if (dist < mid) {
                    // 계속 제거
                    deleteCnt++;
                    idx++;
                } else {
                    start = rocks[idx];
                    idx++;
                }

                if (idx >= rocks.length) {
                    dist = distance - start;
                    if (dist < mid) {
                        deleteCnt++;
                    }
                    break;
                }
            }

            if (deleteCnt > n) {
                maxDist = mid - 1;
            } else {
                minDist = mid + 1;
                answer = mid;
            }
        }

        return answer;
    }
}