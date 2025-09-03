package 코드트리.HSAT;

import java.util.Arrays;
import java.util.Scanner;

public class 자동차_테스트 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = sc.nextInt();
        int[] queries = new int[q];
        for (int i = 0; i < q; i++)
            queries[i] = sc.nextInt();

        // 오름차순 정렬
        Arrays.sort(a);

        for (int query : queries) {
            int idx = binarySearch(a, query);

            if (idx != -1) {
                System.out.println(idx * (n - 1 - idx));
            } else {
                System.out.println(0);
            }
        }
    }

    private static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
