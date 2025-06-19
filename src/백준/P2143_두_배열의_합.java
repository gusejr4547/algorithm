package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P2143_두_배열의_합 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        long[] A = new long[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            A[i] = A[i - 1] + Integer.parseInt(st.nextToken());
        }
        int m = Integer.parseInt(br.readLine());
        long[] B = new long[m + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            B[i] = B[i - 1] + Integer.parseInt(st.nextToken());
        }

        Map<Long, Long> pSumA = new HashMap<>();
        Map<Long, Long> pSumB = new HashMap<>();
        for (int start = 1; start <= n; start++) {
            for (int end = 0; end < start; end++) {
                long sum = A[start] - A[end];
                pSumA.put(sum, pSumA.getOrDefault(sum, 0L) + 1);
            }
        }
        for (int start = 1; start <= m; start++) {
            for (int end = 0; end < start; end++) {
                long sum = B[start] - B[end];
                pSumB.put(sum, pSumB.getOrDefault(sum, 0L) + 1);
            }
        }

        List<Long> pSumAList = new ArrayList<>(pSumA.keySet());
        List<Long> pSumBList = new ArrayList<>(pSumB.keySet());
        pSumAList.sort(Comparator.naturalOrder());
        pSumBList.sort(Comparator.naturalOrder());

        long answer = 0;
        int l = 0;
        int r = pSumBList.size() - 1;

        while (l < pSumAList.size() && r >= 0) {
            long value = pSumAList.get(l) + pSumBList.get(r);

            if (value < T) {
                l++;
            } else if (value > T) {
                r--;
            } else {
                long aCnt = pSumA.get(pSumAList.get(l));
                long bCnt = pSumB.get(pSumBList.get(r));
                answer += aCnt * bCnt;
                l++;
                r--;
            }
        }

        System.out.println(answer);
    }
}
