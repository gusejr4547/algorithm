package 프로그래머스;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 연속_부분_수열_합의_개수 {
    public static void main(String[] args) {
        연속_부분_수열_합의_개수 Main = new 연속_부분_수열_합의_개수();
        int[] elements = {7, 9, 1, 1, 4};
        System.out.println(Main.solution(elements));
    }

    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();

        int[] arr = new int[elements.length * 2];
        int[] sumArr = new int[arr.length + 1];
        for (int i = 0; i < elements.length * 2; i++) {
            arr[i] = elements[i % elements.length];
        }

        for (int i = 0; i < arr.length; i++) {
            sumArr[i + 1] = sumArr[i] + arr[i];
        }

        for (int subArrLen = 1; subArrLen <= elements.length; subArrLen++) {
            for (int i = 0; i < elements.length; i++) {
                set.add(sumArr[i + subArrLen] - sumArr[i]);
            }
        }

        return set.size();
    }
}
