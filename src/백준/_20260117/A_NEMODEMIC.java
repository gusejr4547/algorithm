package 백준._20260117;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A_NEMODEMIC {

    // 스테이지 보드를 보고 난이도를 알고싶다.
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int wall = 0;
        int oneWayVirus = 0;
        int allWayVirus = 0;
        int vaccine = 0;
        int start = 0;
        int end = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if ('#' == map[i][j]) {
                    wall++;
                } else if ('U' == map[i][j] || 'D' == map[i][j] || 'L' == map[i][j] || 'R' == map[i][j]) {
                    oneWayVirus++;
                } else if ('A' == map[i][j]) {
                    allWayVirus++;
                } else if ('V' == map[i][j]) {
                    vaccine++;
                } else if ('S' == map[i][j]) {
                    start++;
                } else if ('E' == map[i][j]) {
                    end++;
                }
            }
        }

        // 난이도
        // 1
        if (wall <= 1 && oneWayVirus <= 1 && allWayVirus == 0 && vaccine == 0 && start == 1 && end == 1) {
            System.out.println(1);
        }
        // 2
        else if (allWayVirus == 0 && vaccine == 0 && start == 1 && end == 1) {
            System.out.println(2);
        }
        // 3
        else if (allWayVirus == 0 && start == 1 && end == 1) {
            System.out.println(3);
        }
        // 4
        else if (start == 1 && end == 1) {
            System.out.println(4);
        } else {
            System.out.println(-1);
        }
    }
}
