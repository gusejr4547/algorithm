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
        int[][] school = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                school[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(school[i]);
        }

        PriorityQueue<Student> pq = new PriorityQueue<>((o1, o2) -> o1.stat - o2.stat);
        int maxStat = 0;
        // 각 반에서 제일 작은 사람 1개씩 넣기
        for (int i = 0; i < N; i++) {
            pq.offer(new Student(school[i][0], i, 0));
            maxStat = Math.max(maxStat, school[i][0]);
        }

        int minDiff = Integer.MAX_VALUE;
        // 한 반에 사람 없을때까지
        while (!pq.isEmpty()) {
            Student cur = pq.poll();

            // 최대 - 최소
            minDiff = Math.min(minDiff, maxStat - cur.stat);

            // cur반에서 다음 사람
            int nextIdx = cur.idxInClass + 1;

            // 없으면 종료
            if (nextIdx >= M) {
                break;
            }

            // pq에 넣기
            pq.offer(new Student(school[cur.classIdx][nextIdx], cur.classIdx, nextIdx));

            // 최대값 갱신
            maxStat = Math.max(maxStat, school[cur.classIdx][nextIdx]);
        }

        System.out.println(minDiff);
    }

    public static void main1(String[] args) throws Exception {
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
            if (classCount[cur.classIdx] == 0) {
                totalClass++;
            }
            classCount[cur.classIdx]++;

            // 모든 반이 포함되면 계산 가능 => left 키우기
            while (totalClass == N) {
                int diff = studentList.get(right).stat - studentList.get(left).stat;
                minDiff = Math.min(minDiff, diff);

                // left
                Student lStu = studentList.get(left);
                classCount[lStu.classIdx]--;
                if (classCount[lStu.classIdx] == 0) {
                    totalClass--;
                }
                left++;
            }
            right++;
        }

        System.out.println(minDiff);
    }

    private static class Student {
        int stat, classIdx, idxInClass;

        public Student(int stat, int classIdx, int idxInClass) {
            this.stat = stat;
            this.classIdx = classIdx;
            this.idxInClass = idxInClass;
        }

        public Student(int stat, int classIdx) {
            this.stat = stat;
            this.classIdx = classIdx;
        }
    }
}
