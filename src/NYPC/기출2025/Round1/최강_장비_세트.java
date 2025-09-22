package NYPC.기출2025.Round1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 최강_장비_세트 {
    static int INF = 1_000_000_000;
    static int ALL_MASK = (1 << 9) - 1;
    static int[] setItem = {3, 5, 7, 9};
    static Map<String, Integer> slotIdx, setIdx;
    static int ws, wd, wh;
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        slotIdx = new HashMap<>();
        slotIdx.put("무기", 0);
        slotIdx.put("장갑", 1);
        slotIdx.put("상의", 2);
        slotIdx.put("하의", 3);
        slotIdx.put("신발", 4);
        slotIdx.put("벨트", 5);
        slotIdx.put("목걸이", 6);
        slotIdx.put("모자", 7);
        slotIdx.put("반지", 8);

        StringTokenizer st = new StringTokenizer(br.readLine());
        ws = Integer.parseInt(st.nextToken());
        wd = Integer.parseInt(st.nextToken());
        wh = Integer.parseInt(st.nextToken());

        N = Integer.parseInt(br.readLine());
        Item[] items = new Item[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            String slotName = st.nextToken();
            String name = st.nextToken();
            int str = Integer.parseInt(st.nextToken());
            int dex = Integer.parseInt(st.nextToken());
            int hp = Integer.parseInt(st.nextToken());
            String setName = st.nextToken();
            items[i] = new Item(slotIdx.get(slotName), name, str, dex, hp, setName);
        }

        M = Integer.parseInt(br.readLine());
        setIdx = new HashMap<>();
        int[][][] setBonus = new int[M][4][3];
        for (int i = 0; i < M; i++) {
            String setName = br.readLine();
            setIdx.put(setName, i);
            // 3, 5, 7, 9 개 세트 효과
            for (int t = 0; t < 4; t++) {
                st = new StringTokenizer(br.readLine());
                setBonus[i][t][0] = Integer.parseInt(st.nextToken());
                setBonus[i][t][1] = Integer.parseInt(st.nextToken());
                setBonus[i][t][2] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 세트의 부위 별로 최고의 장비만 남긴다.
        int[][] bestScore = new int[M][9];
        int[][] bestItemIdx = new int[M][9];
        for (int i = 0; i < M; i++) {
            Arrays.fill(bestScore[i], -INF);
            Arrays.fill(bestItemIdx[i], -1);
        }

        for (int i = 1; i <= N; i++) {
            Item item = items[i];
            int set = setIdx.get(item.setName);
            int score = item.str * ws + item.dex * wd + item.hp * wh;
            if (bestScore[set][item.slot] < score) {
                bestScore[set][item.slot] = score;
                bestItemIdx[set][item.slot] = i;
            }
        }

        // 각 세트의 장비 초합 별로 점수 계산
        int[][] setScore = new int[M][1 << 9]; // i세트의 mask 조합의 점수
        for (int i = 0; i < M; i++) {
            // 모든 부위별 장착 조합
            for (int mask = 0; mask <= ALL_MASK; mask++) {
                int score = 0;
                boolean isEquip = true;
                int itemCount = 0;
                // mask 부위에 장비 존재해야함.
                for (int s = 0; s < 9; s++) {
                    if ((mask & (1 << s)) != 0) {
                        itemCount++;
                        if (bestScore[i][s] == -INF) {
                            isEquip = false;
                        }
                    }
                }
                // 못하는 조합.
                if (!isEquip) {
                    setScore[i][mask] = -INF;
                    continue;
                }

                // 기존에 계산했던 장비 점수
                for (int s = 0; s < 9; s++) {
                    if ((mask & (1 << s)) != 0) {
                        score += bestScore[i][s];
                    }
                }

                // 가능한 조합 => 세트보너스 포함한 점수 계산
                for (int j = 0; j < 4; j++) {
                    if (itemCount >= setItem[j]) {
                        score += ws * setBonus[i][j][0] + wd * setBonus[i][j][1] + wh * setBonus[i][j][2];
                    }
                }

                setScore[i][mask] = score;
            }
        }

        int[][] useItem = new int[M + 1][1 << 9]; // 세트 별로 실제 사용한 것.

        // 점수 DP
        int[][] dp = new int[M + 1][1 << 9];
        for (int i = 0; i <= M; i++) {
            Arrays.fill(dp[i], -INF);
        }
        dp[0][0] = 0;

        for (int i = 1; i <= M; i++) {
            int set = i - 1;
            for (int mask = 0; mask <= ALL_MASK; mask++) {
                // 아무것도 선택하지 않은 상태
                dp[i][mask] = dp[i - 1][mask];
                useItem[i][mask] = 0;

                // mask의 subset을 구해서 점수 조합 최대로 만들기
                for (int subset = mask; subset > 0; subset = (subset - 1) & mask) {
                    // 현재 선택한 장비를 제외한 장비를 이전 상태에서 가져옴 + 나머지 장비 setScore에서 가져옴
                    int score = dp[i - 1][mask ^ subset] + setScore[set][subset];
                    if (score > dp[i][mask]) {
                        dp[i][mask] = score;
                        useItem[i][mask] = subset;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        // 정답 찾기
        // 마지막까지 탐색한 것에서 최대 값을 찾는다.
        int max = -INF;
        int resultSubset = 0;
        for (int mask = 0; mask <= ALL_MASK; mask++) {
            if (dp[M][mask] > max) {
                max = dp[M][mask];
                resultSubset = mask;
            }
        }
        sb.append(max).append("\n");

        // useItem을 바탕으로 resultSubset 부터 역으로 거슬러 올라간다.
        int[] result = new int[9];
        for (int i = M; i > 0; i--) {
            int now = useItem[i][resultSubset];
            // 사용 중인 아이템 result에 기록
            for (int slot = 0; slot < 9; slot++) {
                if ((now & (1 << slot)) != 0) {
                    result[slot] = bestItemIdx[i - 1][slot];
                }
            }

            resultSubset = resultSubset ^ now; // 장착한거 bit 제거
        }

        for (int slot = 0; slot < 9; slot++) {
            sb.append(result[slot]).append("\n");
        }

        System.out.println(sb);
    }

    private static class Item {
        int slot;
        String name;
        int str, dex, hp;
        String setName;

        public Item(int slot, String name, int str, int dex, int hp, String setName) {
            this.slot = slot;
            this.name = name;
            this.str = str;
            this.dex = dex;
            this.hp = hp;
            this.setName = setName;
        }
    }
}
