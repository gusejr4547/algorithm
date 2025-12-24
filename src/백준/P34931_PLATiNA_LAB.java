package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P34931_PLATiNA_LAB {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        char[] seq = br.readLine().toCharArray();
        char[] answer = seq.clone();
        boolean[] isStable = new boolean[N];
        // 변한 것들만 변한다...
        isStable[0] = true;
        isStable[N - 1] = true;
        // AA나 BB인 경우는 변하지 않는쪽
        for (int i = 1; i < N - 1; i++) {
            if (seq[i - 1] == seq[i] || seq[i] == seq[i + 1]) {
                isStable[i] = true;
            }
        }

        for (int i = 0; i < N; i++) {
            if (!isStable[i]) {
                // 변하는 구간
                int s = i;
                while (i < N && !isStable[i]) {
                    i++;
                }
                int e = i - 1;
                i--; // 보정

                // 양쪽 끝에 고정된 색
                char left = seq[s - 1];
                char right = seq[e + 1];
                int length = e - s + 1;

                // 밖에서부터 변화 고정됨...
                // 1초에 한개씩 안쪽으로 들어옴.
                // 중앙에 모일때 까지 얼마나 걸리나?
                int maxTime = 0;
                if (length % 2 == 0) {
                    maxTime = length / 2;
                } else {
                    maxTime = (length + 1) / 2;
                }

                // t와 limit 중 작은거 만큼 확산
                int time = Math.min(T, maxTime);

                // 왼쪽에서 오른쪽
                for (int j = s; j < s + time; j++) {
                    answer[j] = left;
                }

                // 오른쪽에서 왼쪽
                for (int j = e; j > e - time; j--) {
                    answer[j] = right;
                }

                // 완전히 고정되지 않은 부분?
                // T가 홀수일때 원래꺼에서 변함.
                if (T < maxTime && T % 2 == 1) {
                    for (int j = s + time; j <= e - time; j++) {
                        answer[j] = (seq[j] == 'A') ? 'B' : 'A';
                    }
                }
            }
        }

//        List<Integer> target = new ArrayList<>();
//        for (int i = 1; i < N - 1; i++) {
//            target.add(i);
//        }
//
//        while (T-- > 0 && !target.isEmpty()) {
//            List<Integer> next = new ArrayList<>();
//
//            for (int idx : target) {
//                // i번째 생물 앞뒤에 있는 생물이 같고, i번째랑은 다르면 i번째는 변함.
//                if (seq[idx - 1] == seq[idx + 1] && seq[idx] != seq[idx - 1]) {
//                    next.add(idx);
//                }
//            }
//
//            // target에 있는거 변경...
//            for (int idx : next) {
//                seq[idx] = seq[idx] == 'A' ? 'B' : 'A';
//            }
//
//            // 이번에 변경된거 다음 target
//            target = new ArrayList<>(next);
//        }

        System.out.println(String.valueOf(answer));
    }
}
