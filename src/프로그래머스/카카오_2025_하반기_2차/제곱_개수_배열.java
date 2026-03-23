package 프로그래머스.카카오_2025_하반기_2차;

import java.util.*;

public class 제곱_개수_배열 {
    public static void main(String[] args) {
        제곱_개수_배열 Main = new 제곱_개수_배열();
        int[] arr = {2,2,2};
        long l = 2;
        long r = 2;
        System.out.println(Arrays.toString(Main.solution(arr, l, r)));
    }

    // arr을 이용해서 brr을 만든다.
    // arr의 인덱스 순서대로 arr[i]를 brr에 연속으로 arr[i]개 추가.

    // brr의 부분 배열구간의 양 끝을 나타내는 l, r 주어짐.
    // K = brr의 l,r 구간의 원소 합.
    // C = 길이가 r-l+1 인 brr의 부분 배열 중 합이 K인 부분 배열의 개수

    // N <= 100 000
    // arr[i] <= 100 000
    // brr 모든 원소의 합 <= 1 000 000 000 000 000

    int[] arr;
    long[] len, sum;

    public long[] solution(int[] arr, long l, long r) {
        // brr을 만들어 계산하는 것은 불가능.
        this.arr = arr;
        int N = arr.length;

        // 각 원소로 구간을 만든다. 끝점 기준으로한다.
        len = new long[N + 1];
        sum = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            // 구간 길이 누적
            len[i] = len[i - 1] + arr[i - 1];

            // 구간 합 누적
            long rangeSum = (long) arr[i - 1] * arr[i - 1];
            sum[i] = sum[i - 1] + rangeSum;
        }

        // K 구하기
        long K = getPrefixSum(r) - getPrefixSum(l - 1);

        // C 구하기
        long size = r - l + 1;
        long C = 0;

        long lastIdx = len[N] - size + 1; // 탐색 마지막 인덱스
        // 시작점이나 끝점이 새로운 범위로 넘어가는 시점들을 모음
        // 1-base
        List<Long> points = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            long s = len[i] + 1;
            if (1 <= s && s <= lastIdx + 1) {
                points.add(s);
            }
            long e = len[i] - size + 2;
            if (1 <= e && e <= lastIdx + 1) {
                points.add(e);
            }
        }

        // points 정렬하고 중복되는거 제거
        long[] validPoints = points.stream().distinct().sorted().mapToLong(Long::longValue).toArray();

        // 점 사이는 변화량이 일정하다. 이 구간을 탐색
        for (int i = 0; i < validPoints.length - 1; i++) {
            long start = validPoints[i]; // 구간의 처음 시작점
            long end = validPoints[i + 1] - 1; // 구간의 마지막 시작점
            long count = end - start + 1; // 구간 윈도우 이동 총 개수

            // 구간 찾기
            long leftIdx = start;
            long rightIdx = start + size - 1;
            int leftRange = getRangeIndex(leftIdx);
            int rightRange = getRangeIndex(rightIdx);

            // 현재 윈도우 합
            long currentSum = getPrefixSum(rightIdx) - getPrefixSum(leftIdx - 1);

            // 하나 이동할때 변화량
            long diff = (long) arr[rightRange - 1] - arr[leftRange - 1];

            if (diff == 0) {
                if (currentSum == K) {
                    C += count;
                }
            } else {
                long need = K - currentSum; // 필요한 변화량

                if (need % diff == 0) {
                    long move = need / diff;

                    if (0 <= move && move < count) {
                        C++;
                    }
                }
            }
        }

        long[] answer = {K, C};
        return answer;
    }

    // 0~target 까지 sum
    private long getPrefixSum(long target) {
        if (target == 0) {
            return 0;
        }
        int range = getRangeIndex(target);
        long prefixSum = sum[range - 1];
        long remain = target - len[range - 1];
        prefixSum += remain * arr[range - 1];
        return prefixSum;
    }

    // 몇번째 구간에 있는지 반환
    private int getRangeIndex(long target) {
        int left = 1;
        int right = len.length - 1;
        int answer = right;

        while (left <= right) {
            int mid = (left + right) / 2;

            // lower bound
            if (len[mid] >= target) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }
}
