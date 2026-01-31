package 코드트리.삼성;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 택배_하차 {
    // NxN 크기의 정사각형 격자
    // N <= 50
    // M <= 100

    static int N;
    static int[][] grid;
    static Map<Integer, Package> packages;
    //    static boolean[] isDeleted;
    static List<Integer> deleteList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        grid = new int[N][N];
        packages = new HashMap<>();
        deleteList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int packNum = Integer.parseInt(st.nextToken()); // 택배 번호
            int h = Integer.parseInt(st.nextToken()); // 택배 세로크기
            int w = Integer.parseInt(st.nextToken()); // 택배 가로크기
            int col = Integer.parseInt(st.nextToken()) - 1; // 가장 왼쪽 좌표

            // 1.택배 투입
            // 하단으로 떨어짐
            // 다른짐이 있는 경우 거기서 멈춤
            // 어떤 택배도 격자를 벗어나는 경우는 주어지지 않는다.
            put(packNum, h, w, col);
        }

        while (true) {
            // 2.택배 하차(좌)
            // 택배중에 왼쪽으로 이동했을때 다른 택배와 부딫히지 않는 택배를 먼저 하차
            // 여러개면 => 택배 번호가 작은거 먼저
            // 뺀뒤 떨어질 수 있는거는 떨어짐
            if (!leftPoll()) {
                break;
            }

            // 3. 택배 하차(우)
            // 2번과 같은 과정
            if (!rightPoll()) {
                break;
            }
        }

        // 하차되는 택배의 번호를 순서대로 출력
        StringBuilder sb = new StringBuilder();
        for (int n : deleteList) {
            sb.append(n).append('\n');
        }
        System.out.println(sb);
    }

    private static boolean rightPoll() {
        // 오른쪽에 아무것도 없는 거 찾기
        int selectPackageNum = Integer.MAX_VALUE;
        for (int r = 0; r < N; r++) {
            for (int c = N - 1; c >= 0; c--) {
                if (grid[r][c] != 0) {
                    Package p = packages.get(grid[r][c]);
                    // 우 하단
                    if (p.r == r && p.c + p.w - 1 == c) {
                        selectPackageNum = Math.min(selectPackageNum, grid[r][c]);
                    }
                    break;
                }
            }
        }
        // 뺄 수 있는게 없음
        if (selectPackageNum == Integer.MAX_VALUE) {
            return false;
        }

        // 그중에서 num 가장 작은거 지우기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == selectPackageNum) {
                    grid[i][j] = 0;
                }
            }
        }
        deleteList.add(selectPackageNum);

        // 아래로 내리기
        // selectPackageNum 의 위쪽에서 일어날것...
        Package p = packages.get(selectPackageNum);
        Set<Integer> check = new HashSet<>();

        for (int r = p.r + p.h; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (check.contains(grid[r][c])) {
                    continue;
                }
                if (grid[r][c] != 0) {
                    Package t = packages.get(grid[r][c]);
                    // 좌 하단 좌표
                    if (t.r == r && t.c == c) {
                        check.add(grid[r][c]);

                        // 내리기
                        down(grid[r][c]);
                    }
                }
            }
        }

        return true;
    }

    private static boolean leftPoll() {
        // 왼쪽에 아무것도 없는 거 찾기
        // 좌하 좌표가 왼쪽에 아무것도 없는 경우 뺄 수 있음.
        int selectPackageNum = Integer.MAX_VALUE;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (grid[r][c] != 0) {
                    Package p = packages.get(grid[r][c]);
                    if (p.r == r && p.c == c) {
                        selectPackageNum = Math.min(selectPackageNum, grid[r][c]);
                    }
                    break;
                }
            }
        }
        // 뺄 수 있는게 없음
        if (selectPackageNum == Integer.MAX_VALUE) {
            return false;
        }

        // 그중에서 num 가장 작은거 지우기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == selectPackageNum) {
                    grid[i][j] = 0;
                }
            }
        }
        deleteList.add(selectPackageNum);

        // 아래로 내리기
        // selectPackageNum 의 위쪽에서 일어날것...
        Package p = packages.get(selectPackageNum);
        Set<Integer> check = new HashSet<>();

        for (int r = p.r + p.h; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (check.contains(grid[r][c])) {
                    continue;
                }
                if (grid[r][c] != 0) {
                    Package t = packages.get(grid[r][c]);
                    // 좌 하단 좌표
                    if (t.r == r && t.c == c) {
                        check.add(grid[r][c]);
                        // 내리기
                        down(grid[r][c]);
                    }
                }
            }
        }

        return true;
    }

    private static void down(int packNum) {
        Package p = packages.get(packNum);
        // 지우기
        for (int r = p.r; r < p.r + p.h; r++) {
            for (int c = p.c; c < p.c + p.w; c++) {
                grid[r][c] = 0;
            }
        }

        // 위치 찾기
        int r = 0;
        for (int i = p.r; i >= 0; i--) {
            if (!canPlace(i, p.c, p.h, p.w, packNum)) {
                break;
            }
            r = i;
        }

        // 놓기
        for (int i = 0; i < p.h; i++) {
            for (int j = 0; j < p.w; j++) {
                grid[r + i][p.c + j] = packNum;
            }
        }
        // packages 갱신
        p.r = r;
    }

    private static void put(int packNum, int h, int w, int col) {
        int r = N - 1;

        // 위 -> 아래 순서대로 탐색
        for (int i = N - h; i >= 0; i--) {
            // 놓을 수 있나?
            if (!canPlace(i, col, h, w, packNum)) {
                break;
            }
            // 위치
            r = i;
        }

        // (r,c)에 저장
        for (int i = r; i < r + h; i++) {
            for (int j = col; j < col + w; j++) {
                grid[i][j] = packNum;
            }
        }
        packages.put(packNum, new Package(r, col, h, w));
    }

    private static boolean canPlace(int r, int c, int h, int w, int packNum) {
        // 좌하단 (r,c)
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                // 전부 0
                if (grid[r + i][c + j] != 0 && grid[r + i][c + j] != packNum) {
                    return false;
                }
            }
        }
        return true;
    }

    private static class Package {
        int r, c, h, w;

        public Package(int r, int c, int h, int w) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.w = w;
        }
    }
}
