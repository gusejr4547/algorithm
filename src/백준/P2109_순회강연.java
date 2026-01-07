package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P2109_순회강연 {
    // n <= 10 000
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Lecture> lectureList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            lectureList.add(new Lecture(p, d));
        }

        // 돈 내림차순
        lectureList.sort((o1, o2) -> Integer.compare(o2.p, o1.p));

        int[] next = new int[10001]; // 비어있는 이전날 바로 탐색
        for (int i = 0; i <= 10000; i++) {
            next[i] = i;
        }

        int answer = 0;
        for (Lecture lecture : lectureList) {
            // 강연 p일에 맞추어서 함.
            if (next[lecture.d] == lecture.d) {
                answer += lecture.p;
                union(lecture.d, lecture.d - 1, next);
            }
            // p일에 일정이 있다면 가장 가까운 이전날 수행
            else {
                int prev = find(lecture.d, next);
                if (prev != 0) {
                    answer += lecture.p;
                    union(prev, prev - 1, next);
                }
            }
        }

        System.out.println(answer);
    }

    private static void union(int a, int b, int[] parent) {
        a = find(a, parent);
        b = find(b, parent);

        if (a != b) {
            parent[a] = b;
        }
    }

    private static int find(int a, int[] parent) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a], parent);
    }

    private static class Lecture {
        int p, d;

        public Lecture(int p, int d) {
            this.p = p;
            this.d = d;
        }
    }
}
