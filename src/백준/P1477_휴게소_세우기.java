package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class P1477_휴게소_세우기 {
    static int N, M, L;
    static List<Integer> restPlace;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        restPlace = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            restPlace.add(Integer.parseInt(st.nextToken()));
        }
        // 시작, 끝
        restPlace.add(0);
        restPlace.add(L);

        // 정렬
        Collections.sort(restPlace);

        // 휴게소 사이 최소 거리를 찾기
        int answer = 0;
        int left = 1;
        int right = L;
        while (left <= right) {
            int mid = (left + right) / 2;

            // 휴게소 사이 최대 거리가 mid 라고 가정
            if (isPossible(mid, M)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean isPossible(int maxDist, int additionalRest) {
        int count = 0;
        for (int i = 1; i < restPlace.size(); i++) {
            int dist = restPlace.get(i) - restPlace.get(i - 1);

            int need = dist / maxDist;
            // 딱 나누어 떨어지면 -1
            if (dist % maxDist == 0) {
                need -= 1;
            }
            count += need;
        }

        return count <= additionalRest;
    }
}
