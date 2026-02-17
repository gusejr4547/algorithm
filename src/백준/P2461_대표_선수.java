package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P2461_대표_선수 {
    // N, M <= 1000
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                studentList.add(new Student(Integer.parseInt(st.nextToken()), i));
            }
        }

        // 정렬
        studentList.sort((o1, o2) -> o1.stat - o2.stat);

        int left = 0;
        int right = 0;
        int minDiff = Integer.MAX_VALUE;

        int totalClass = 0;
        int[] classCount = new int[N];
        while (right < N * M) {
            Student cur = studentList.get(right);
            if (classCount[cur.idx] == 0) {
                totalClass++;
            }
            classCount[cur.idx]++;

            // 모든 반이 포함되면 계산 가능 => left 키우기
            while (totalClass == N) {
                int diff = studentList.get(right).stat - studentList.get(left).stat;
                minDiff = Math.min(minDiff, diff);

                // left
                Student lStu = studentList.get(left);
                classCount[lStu.idx]--;
                if (classCount[lStu.idx] == 0) {
                    totalClass--;
                }
                left++;
            }
            right++;
        }

        System.out.println(minDiff);
    }

    private static class Student {
        int stat, idx;

        public Student(int stat, int idx) {
            this.stat = stat;
            this.idx = idx;
        }
    }
}
