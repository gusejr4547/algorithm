package 코드트리.삼성;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 민트_초코_우유 {
    // 초기신봉음식 foods
    // T - 민트, C - 초코, M - 우유
    // 영향을 받아 여러개 중복가능
    // 초기 신앙심 B

    static String[] allType = {"TCM", "TC", "TM", "CM", "M", "C", "T"};
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int N;
    static int[][] foods;
    static int[][] B, group;
    static boolean[][] defense;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        foods = new int[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = str.charAt(j);
                if ('T' == c) {
                    foods[i][j] = 1 << 2;
                } else if ('C' == c) {
                    foods[i][j] = 1 << 1;
                } else {
                    foods[i][j] = 1;
                }
            }
        }
        B = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            // 1. 아침
            // 모든 학생 신앙심 +1
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    B[i][j]++;
                }
            }

            // 2. 점심
            // 인접한 학생(상,하,좌,우)과 신봉음식이 완전히 같은 경우 그룹
            // 그룹에서 대표 1명
            // 신앙심 가장 큰사람 -> 동일하면 r이 작은사람 -> 동일하면 c가 작은사람
            group = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(group[i], -1);
            }
            List<Person> groupLeader = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (group[i][j] != -1) {
                        continue;
                    }

                    Person leader = makeGroup(i, j, groupLeader.size());
                    groupLeader.add(leader);
                }
            }

            // 그룹원은 신앙심 1을 대표에게 넘긴다
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int groupNum = group[i][j];
                    // 그룹원인가?
                    if (i != groupLeader.get(groupNum).r || j != groupLeader.get(groupNum).c) {
                        B[i][j] -= 1;
                        B[groupLeader.get(groupNum).r][groupLeader.get(groupNum).c] += 1;
                    }
                }
            }

            // 3. 저녁
            // 그룹의 대표가 신앙을 전파
            // 그룹 순서
            // 1. 단일 - 민트, 초코, 우유
            // 2. 이중
            // 3. 삼중
            // 같은 그룹 내
            // 1. 대표자 신앙심 높은
            // 2. r이 작은
            // 3. c가 작은
            PriorityQueue<Person> first = new PriorityQueue<>();
            PriorityQueue<Person> second = new PriorityQueue<>();
            PriorityQueue<Person> third = new PriorityQueue<>();
            for (Person p : groupLeader) {
                int cnt = Integer.bitCount(foods[p.r][p.c]);
                if (cnt == 1) {
                    first.offer(new Person(p.r, p.c, B[p.r][p.c]));
                } else if (cnt == 2) {
                    second.offer(new Person(p.r, p.c, B[p.r][p.c]));
                } else {
                    third.offer(new Person(p.r, p.c, B[p.r][p.c]));
                }
            }

            defense = new boolean[N][N];
            // 어떤 학생이 다른 그룹 대표에게 전파를 당하면 방어상태가 되어 당일에 전파x
            // 전파 받는것은 가능.
            // 단일
            while (!first.isEmpty()) {
                Person leader = first.poll();
                if (!defense[leader.r][leader.c]) {
                    spread(leader);
                }
            }
            // 이중
            while (!second.isEmpty()) {
                Person leader = second.poll();
                if (!defense[leader.r][leader.c]) {
                    spread(leader);
                }
            }
            // 삼중
            while (!third.isEmpty()) {
                Person leader = third.poll();
                if (!defense[leader.r][leader.c]) {
                    spread(leader);
                }
            }

            // 출력
            int[] sum = new int[1 << 3];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sum[foods[i][j]] += B[i][j];
                }
            }
            sb.append(sum[7]).append(' ').append(sum[6]).append(' ').append(sum[5]).append(' ')
                    .append(sum[3]).append(' ').append(sum[1]).append(' ')
                    .append(sum[2]).append(' ').append(sum[4]).append('\n');
        }
        System.out.println(sb);
    }

    private static void spread(Person leader) {
        // 초기좌표, 방향, 간절함
        // 전파자는 B중 1만 남기고 나머지를 간절함 x = B-1로 바꾸어 전파에 사용
        // 방향은 B % 4로 결정. 0,1,2,3 => 상,하,좌,우
        int r = leader.r;
        int c = leader.c;
        int dir = B[r][c] % 4;
        int want = B[r][c] - 1;
        B[r][c] = 1;
        r = r + dy[dir];
        c = c + dx[dir];

        // 범위 밖 or want가 0이 될때까지 전파
        while (want > 0 && checkBound(r, c)) {
            // 전파자와 음식이 같다 => 전파x 다음으로
            if (foods[leader.r][leader.c] == foods[r][c]) {
                r = r + dy[dir];
                c = c + dx[dir];
            }
            // 전파자와 음식이 다르다 => 전파o
            else {
                // 대상의 신앙심 y
                int target = B[r][c];
                // x > y -> 강한 전파
                if (want > target) {
                    // 전파자의 음식과 동일하게 변경, 전파자의 간절함 y+1 만큼 감소, 전파대상 신앙심+1
                    foods[r][c] = foods[leader.r][leader.c];
                    want = want - (target + 1);
                    B[r][c] += 1;
                }
                // x <= y -> 약한 전파
                else {
                    // 대상은 전파자음식+대상음식 변경, 전파자 간절함 0, 전파대상 신앙심 +x
                    foods[r][c] = foods[r][c] | foods[leader.r][leader.c];
                    B[r][c] += want;
                    want = 0;
                }
                defense[r][c] = true;
                r = r + dy[dir];
                c = c + dx[dir];
            }
        }
    }

    private static boolean checkBound(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    private static Person makeGroup(int r, int c, int groupNum) {
        Person leader = new Person(N, N, -1);
        ArrayDeque<Person> queue = new ArrayDeque<>();
        queue.offer(new Person(r, c, B[r][c]));
        group[r][c] = groupNum;

        while (!queue.isEmpty()) {
            Person cur = queue.poll();

            // 더 리더에 적합하다면 변경
            if (leader.compareTo(cur) > 0) {
                leader = cur;
            }

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dy[d];
                int nc = cur.c + dx[d];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N || group[nr][nc] != -1 || foods[r][c] != foods[nr][nc]) {
                    continue;
                }
                group[nr][nc] = groupNum;
                queue.offer(new Person(nr, nc, B[nr][nc]));
            }
        }
        return leader;
    }

    private static class Person implements Comparable<Person> {
        int r, c, value;

        public Person(int r, int c, int value) {
            this.r = r;
            this.c = c;
            this.value = value;
        }

        @Override
        public int compareTo(Person o) {
            if (this.value == o.value) {
                if (this.r == o.r) {
                    return Integer.compare(this.c, o.c);
                }
                return Integer.compare(this.r, o.r);
            }
            return Integer.compare(o.value, this.value);
        }
    }
}
