package 백준._20251227;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class C_마왕 {
    // 좌표평면
    // N개의 좌표 마법 주어짐. (x-r, y-r) (x+r, y+r) 을 대각선으로 하는 정사각형 내부를 공격 하는 마법..
    // 일부를 선택. 범위가 겹치지 않도록
    // 고른 마법은 준비된 모든 마법을 사용하는 공격 위력의 1/9 배 이상...
    // 공격의 위력은 마법진들의 범위의 합집합의 면적

    // N <= 2000
    // -100 000 000 <= 좌표 <= 100 000 000
    // r <= 100 000 000

    static Magic[] magics;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        magics = new Magic[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            magics[i] = new Magic(x, y, r, i + 1);
        }
        // 영역 내림차순
        Arrays.sort(magics, (o1, o2) -> Long.compare(o2.r, o1.r));

        // 큰거부터 선택, 겹치면 넘어감
        List<Magic> select = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Magic cur = magics[i];
            boolean conflict = false;
            for (int j = 0; j < select.size(); j++) {
                if (isConflict(cur, select.get(j))) {
                    conflict = true;
                    break;
                }

            }
            if (!conflict) {
                select.add(cur);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(select.size()).append("\n");
        for (int i = 0; i < select.size(); i++) {
            sb.append(select.get(i).idx).append("\n");
        }

        System.out.println(sb);
    }

    private static boolean isConflict(Magic a, Magic b) {
        long xDist = Math.abs(a.x - b.x);
        long yDist = Math.abs(a.y - b.y);
        long minDist = a.r + b.r;
        return xDist < minDist && yDist < minDist;
    }

    private static class Magic {
        long x, y, r;
        int idx;

        public Magic(long x, long y, long r, int idx) {
            this.x = x;
            this.y = y;
            this.r = r;
            this.idx = idx;
        }
    }


}
