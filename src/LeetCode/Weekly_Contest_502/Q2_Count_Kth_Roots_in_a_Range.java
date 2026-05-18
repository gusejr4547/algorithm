package LeetCode.Weekly_Contest_502;

public class Q2_Count_Kth_Roots_in_a_Range {
    // y = x^k;
    // y 몇개있는지 구해라. y는 [l, r] 범위
    // 0 <= l <= r <= 10^9
    // k <= 30

    public int countKthRoots(int l, int r, int k) {
        // 왼쪽 끝 찾기 => l보다 크거나 같은 가장 작은 정수
        int a = -1;
        int left = 0;
        int right = 1_000_000_000;
        while (left <= right) {
            int mid = (left + right) / 2;

            // mid^k가 l보다 큰가?
            if (Math.pow(mid, k) >= l) {
                a = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // System.out.println(a);

        // 오른쪽 끝 찾기 => r보다 작거나 같은 가장 큰 정수
        int b = -1;
        left = 0;
        right = 1_000_000_000;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (Math.pow(mid, k) <= r) {
                b = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // System.out.println(b);
        if (a == -1 || b == -1) {
            return 0;
        }
        return b - a + 1;
    }
}
