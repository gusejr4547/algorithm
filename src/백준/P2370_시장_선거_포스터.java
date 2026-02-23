package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P2370_시장_선거_포스터 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] left = new int[N];
        int[] right = new int[N];

        int[] arr = new int[N * 4];
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            left[i] = Integer.parseInt(st.nextToken());
            right[i] = Integer.parseInt(st.nextToken());

            arr[cnt++] = left[i];
            arr[cnt++] = right[i];
            arr[cnt++] = left[i] - 1; // 간격 보존
            arr[cnt++] = right[i] + 1; // 간격 보존
        }

        // 정렬
        Arrays.sort(arr);
        // 중복 제거
        List<Integer> comp = new ArrayList<>();
        comp.add(arr[0]);
        for (int i = 1; i < cnt; i++) {
            if (arr[i] != arr[i - 1]) {
                comp.add(arr[i]);
            }
        }

        int[] wall = new int[comp.size()];
        for (int i = 0; i < N; i++) {
            // 원래 좌표의 comp 좌표
            int leftIdx = Collections.binarySearch(comp, left[i]);
            int rightIdx = Collections.binarySearch(comp, right[i]);

            // i+1 번째 포스터 덮어 씌우기
            for (int j = leftIdx; j <= rightIdx; j++) {
                wall[j] = i + 1;
            }
        }

        // 벽에서 개수 찾기
        boolean[] visible = new boolean[N + 1];
        int answer = 0;
        for (int i = 0; i < wall.length; i++) {
            int posterId = wall[i];

            if (posterId != 0 && !visible[posterId]) {
                visible[posterId] = true;
                answer++;
            }
        }

        System.out.println(answer);
    }
}
