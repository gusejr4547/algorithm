package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 비가 내렸을 때 그 호수가 비어있다면 그 호수는 다시 물로 꽉 차지만,
// 만약 호수에 물이 있는데 다시 비가 내릴경우 호수가 넘쳐 흘러서 엄청난 재앙
// 비가 안오는날 1개의 호수 비울 수 있다.
public class P3430_용이_산다 {
    static StringBuilder sb = new StringBuilder();
    static TreeSet<Integer> drinkTime = new TreeSet<>();
    static int[] rainTime = new int[1_000_000], result = new int[1_000_000];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Z = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < Z; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[] t = new int[m];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                t[i] = Integer.parseInt(st.nextToken());
            }

            solution(n, m, t);
        }
        System.out.println(sb);
    }

    static void solution(int n, int m, int[] t) {
        // n, m <= 1_000_000
        // 이전에 비가 내렸던 시간에 가장 가까운 물 마실 수 있는 시간을 사용.
        boolean isFlood = false;
        drinkTime.clear(); // 비가 안내린 시간
        Arrays.fill(rainTime, -1);
        Arrays.fill(result, 0);

        for (int i = 0; i < m; i++) {
            if (t[i] == 0) {
                drinkTime.add(i);
            } else {
                // 내린 곳 한번 더 내림 => 물 마시기
                if (!drinkTime.isEmpty()) {
                    Integer drink = drinkTime.higher(rainTime[t[i]]);
                    if (drink != null) {
                        result[drink] = t[i];
                        drinkTime.remove(drink);
                        rainTime[t[i]] = i;
                    } else {
                        isFlood = true;
                        break;
                    }
                } else {
                    isFlood = true;
                    break;
                }
            }
        }

        if (isFlood) {
            sb.append("NO");
        } else {
            sb.append("YES").append("\n");
            for (int i = 0; i < m; i++) {
                if (t[i] == 0) {
                    sb.append(result[i]).append(" ");
                }
            }
        }
        sb.append("\n");
    }
}
