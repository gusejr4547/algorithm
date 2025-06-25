package 코드트리;

import java.util.Scanner;

public class 최대_감소_부분_수열 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.
        int[] lds = new int[n];
        lds[0] = arr[0];
        int end = 0;
        for(int i=1; i<n; i++){
            if(lds[end] > arr[i]){
                lds[end+1] = arr[i];
                end++;
            }else{
                int pos = binarySearch(0, end, arr[i], lds);
                lds[pos] = arr[i];
            }
        }

        System.out.println(end+1);
    }

    private static int binarySearch(int start, int end, int key, int[] arr){
        while(start < end){
            int mid = (start + end) / 2;

            if(arr[mid] > key){
                start = mid + 1;
            }else{
                end = mid;
            }
        }
        return end;
    }
}
