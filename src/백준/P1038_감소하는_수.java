package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P1038_감소하는_수 {
    static List<Long> numList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        numList = new ArrayList<>();

        // 숫자 선택하면 순서는 정해져있음.
        // 숫자 선택하는 경우의 수 2^10, 공집합 -1

        if (N <= 10) {
            System.out.println(N);
        } else if (N >= 1023) {
            System.out.println(-1);
        } else {
            numList = new ArrayList<>();
            for (int i = 0; i <= 9; i++) {
                make(1, i);
            }
        }

        Collections.sort(numList);
        System.out.println(numList.get(N));
    }

    // num을 시작으로하는 감소하는 수
    private static void make(int idx, long num) {
        if (idx > 10) {
            return;
        }
        numList.add(num);
        for (int val = 0; val < num % 10; val++) {
            make(idx + 1, num * 10 + val);
        }
    }
}
