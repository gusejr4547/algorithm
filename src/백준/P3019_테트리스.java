package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P3019_테트리스 {
    static int C, P; // C열, P블록번호
    static int[][][] shapes = {
            {{0}, {0, 0, 0, 0}},
            {{0, 0}},
            {{0, 0, 1}, {1, 0}},
            {{1, 0, 0}, {0, 1}},
            {{0, 0, 0}, {0, 1}, {1, 0, 1}, {1, 0}},
            {{0, 0, 0}, {0, 0}, {0, 1, 1}, {2, 0}},
            {{0, 0, 0}, {0, 2}, {1, 1, 0}, {0, 0}}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        int[] map = new int[C];
        for (int i = 0; i < C; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        int answer = fitCnt(map, P);
        System.out.println(answer);
    }

    private static int fitCnt(int[] map, int shapeNum) {
        int cnt = 0;

        for (int[] shape : shapes[shapeNum]) {
            for (int i = 0; i <= C - shape.length; i++) {
                int diff = map[i] - shape[0];
                boolean valid = true;

                for (int j = 1; j < shape.length; j++) {
                    if (diff != map[i + j] - shape[j]) {
                        valid = false;
                        break;
                    }
                }

                if (valid) {
//                    System.out.println("startidx = " + i);
//                    System.out.println(Arrays.toString(shape));
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
