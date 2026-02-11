package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class P20166_문자열_지옥에_빠진_호석 {
    static int N, M, K;
    static char[][] grid;
    static String[] target;
    static Map<String, Integer> strCnt;
    static StringBuilder str;
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        grid = new char[N][M];
        for (int i = 0; i < N; i++) {
            grid[i] = br.readLine().toCharArray();
        }
        target = new String[K];
        for (int i = 0; i < K; i++) {
            target[i] = br.readLine();
        }
        // N,M <= 10
        // 신이 좋아하는 문자열 길이 str[i].length() <= 5
        str = new StringBuilder();
        strCnt = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                str.append(grid[i][j]);
                dfs(i, j, 1);
                str.setLength(str.length() - 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            if (strCnt.containsKey(target[i])) {
                sb.append(strCnt.get(target[i]));
            }else{
                sb.append(0);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static void dfs(int y, int x, int len) {
        // 추가
        String k = String.valueOf(str);
        strCnt.put(k, strCnt.getOrDefault(k, 0) + 1);

        // 기저조건
        if (len == 5) {
            return;
        }

        // 다음
        for (int d = 0; d < 8; d++) {
            int ny = (y + dy[d] + N) % N;
            int nx = (x + dx[d] + M) % M;
            str.append(grid[ny][nx]);
            dfs(ny, nx, len + 1);
            str.setLength(str.length() - 1);
        }
    }
}
