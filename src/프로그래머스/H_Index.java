package 프로그래머스;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class H_Index {
    public static void main(String[] args) {
        H_Index Main = new H_Index();
        int[] citations = {3, 0, 6, 1, 5};
        System.out.println(Main.solution(citations));
    }

    public int solution(int[] citations) {
        Arrays.sort(citations);
        int len = citations.length;
        int left = 0;
        int right = citations.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int h = len - mid;
            if (citations[mid] >= h) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return len - left;
    }
}
