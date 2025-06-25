package 코드트리;

import java.util.*;

public class 증가했다가_감소하는_부분_수열 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.
        int[] lis = new int[n];
        int[] lisArr = new int[n];
        lis[0] = arr[0];
        int max = 0;
        for (int i = 1; i < n; i++) {
            if (lis[max] < arr[i]) {
                lis[++max] = arr[i];
                lisArr[i] = max;
            } else {
                int pos = binarySearch(0, max, arr[i], lis);
                lis[pos] = arr[i];
                lisArr[i] = pos;
            }
        }

        // System.out.println(Arrays.toString(lisArr));

        int[] revLis = new int[n];
        int[] revLisArr = new int[n];
        max = 0;
        revLis[0] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (revLis[max] < arr[i]) {
                revLis[++max] = arr[i];
                revLisArr[i] = max;
            } else {
                int pos = binarySearch(0, max, arr[i], revLis);
                revLis[pos] = arr[i];
                revLisArr[i] = pos;
            }
        }

        // System.out.println(Arrays.toString(revLisArr));

        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, lisArr[i] + 1 + revLisArr[i] + 1 - 1);
        }

        System.out.println(answer);
    }

    private static int binarySearch(int start, int end, int key, int[] arr) {
        while (start < end) {
            int mid = (start + end) / 2;

            if (arr[mid] < key) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return end;
    }
}
