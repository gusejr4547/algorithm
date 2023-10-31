package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P17500_국경 {

    static int N;
    static char[][] map;

    static boolean[][] validVisit;
    static List<int[]> animalPos;
    static char[][] ans;
    static boolean[][] visit;
    static int[] dy = {0, 0, 2, -2};
    static int[] dx = {4, -4, 0, 0};
    static int[] my = {0, 0, 1, -1};
    static int[] mx = {1, -1, 0, 0};
    static boolean flag = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        animalPos = new ArrayList<>();

        // 초기화
        ans = new char[2 * N + 3][4 * N + 3];
        for (int i = 0; i < 2 * N + 3; i++) {
            for (int j = 0; j < 4 * N + 3; j++) {
                if (i == 0 || i == 2 * N + 2) {
                    ans[i][j] = '#';
                } else if (j == 0 || j == 4 * N + 2) {
                    ans[i][j] = '#';
                } else if (i % 2 == 1 && j % 4 == 1) {
                    ans[i][j] = '+';
                } else if (i % 2 == 0 && j % 4 == 3) {
                    char c = map[i / 2 - 1][j / 4];
                    if (c != '.')
                        animalPos.add(new int[]{i / 2 - 1, j / 4});

                    ans[i][j] = c;
                } else {
                    ans[i][j] = ' ';
                }
            }
        }

        visit = new boolean[2 * N + 3][4 * N + 3];

        // DFS?
        // 국경은 왼쪽 상단 꼭짓점에서 시작하여 오른쪽 하단 꼭짓점으로 가는 하나의 경로여야 합니다.
        // (1,1) -> (n*2+1, n*4+1)

        visit[1][1] = true;
        dfs(1, 1);
        if(flag){
            System.out.println("yes");
            for (char[] arr : ans) {
                System.out.println(arr);
            }
        }else{
            System.out.println("no");
        }


    }


    static void dfs(int y, int x) {
        if (flag) {
            return;
        }

        if (y == N * 2 + 1 && x == N * 4 + 1) {
            validVisit = new boolean[N][N];


//            for (char[] arr : ans) {
//                System.out.println(arr);
//            }
//            System.out.println("--------------------------------------------");

            for (int[] animal : animalPos) {
                // valid 하지 않으면 다음 탐색
                int animalY = animal[0];
                int animalX = animal[1];
                if (validVisit[animalY][animalX]) {
                    continue;
                }
                validVisit[animalY][animalX] = true;
//                for(int i=0; i<N; i++){
//                    System.out.println(Arrays.toString(validVisit[i]));
//                }
//                System.out.println("탐색 : " + animalY + " " + animalX + " " + map[animalY][animalX]);
                if (!valid(animalY, animalX, map[animalY][animalX])) {
                    return;
                }
            }
            flag = true;
//            for (char[] arr : ans) {
//                System.out.println(arr);
//            }
//            System.out.println("--------------------------------------------");


            return;
        }

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (ny < 0 || nx < 0 || ny >= 2 * N + 3 || nx >= 4 * N + 3 || visit[ny][nx]) {
                continue;
            }
            // 이동 경로 표시
            int nmy = y + my[d];
            int nmx = x + mx[d];
            while (nmy != ny || nmx != nx) {
                if (d == 0 || d == 1) {
                    ans[nmy][nmx] = '-';
                } else {
                    ans[nmy][nmx] = '|';
                }
                nmy += my[d];
                nmx += mx[d];
            }
            visit[ny][nx] = true;

            // 다음 이동
            dfs(ny, nx);
            // true이면 종료
            if (flag) {
                return;
            }
            // 이동 경로 삭제
            nmy = ny - my[d];
            nmx = nx - mx[d];
            while (nmy != y || nmx != x) {
                ans[nmy][nmx] = ' ';
                nmy -= my[d];
                nmx -= mx[d];
            }
            visit[ny][nx] = false;
        }
    }


    private static boolean valid(int y, int x, char animal) {

        int ansY = y * 2 + 2;
        int ansX = x * 4 + 3;

        for (int d = 0; d < 4; d++) {
            int nmy = y + my[d];
            int nmx = x + mx[d];
            // 벽이 있어서 이동 못하는지 확인
            int ny = ansY + dy[d] / 2;
            int nx = ansX + dx[d] / 2;
            if (ny < 0 || nx < 0 || ny >= 2 * N + 3 || nx >= 4 * N + 3 || ans[ny][nx] != ' ') {
                continue;
            }
            if (nmy < 0 || nmx < 0 || nmy >= N || nmx >= N || validVisit[nmy][nmx]) {
                continue;
            }
            validVisit[nmy][nmx] = true;
            // 위치 이동한 장소에 동물 확인
            if (map[nmy][nmx] != '.' && animal != map[nmy][nmx]) {
//                System.out.println(map[nmy][nmx]);
//                System.out.println(animal);
                return false;
            }
            // 다음 탐색
            if (!valid(nmy, nmx, animal)) {
                return false;
            }
        }

        return true;
    }
}
