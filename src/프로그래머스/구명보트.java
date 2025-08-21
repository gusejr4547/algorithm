package 프로그래머스;

import java.util.Arrays;

public class 구명보트 {
    public static void main(String[] args) {
        구명보트 Main = new 구명보트();
        int[] people = {70, 50, 80, 50};
        int limit = 100;
        System.out.println(Main.solution(people, limit));
    }

    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);

        int left = 0;
        int right = people.length - 1;

        while (left <= right) {
            if (people[right] + people[left] <= limit) {
                left++;
            }

            right--;
            answer++;
        }

        return answer;
    }

    private static int binarySearch(int[] arr, int key) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] <= key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
