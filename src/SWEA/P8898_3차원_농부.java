package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class P8898_3차원_농부 {
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 1 ≤ cowCnt, horseCnt ≤ 500_000
            int cowCnt = Integer.parseInt(st.nextToken());
            int horseCnt = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int cowX = Integer.parseInt(st.nextToken());
            int horseX = Integer.parseInt(st.nextToken());

            int[] cowZ = new int[cowCnt];
            int[] horseZ = new int[horseCnt];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < cowCnt; i++) {
                cowZ[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < horseCnt; i++) {
                horseZ[i] = Integer.parseInt(st.nextToken());
            }

            // dist = |x1-x2| + |y1-y2| + |z1-z2|
            // x,y는 고정
            int diffX = Math.abs(cowX - horseX);
            Arrays.sort(horseZ);
//            System.out.println(Arrays.toString(horseZ));

            int minDist = Integer.MAX_VALUE;
            int count = 0;
            for (int i = 0; i < cowCnt; i++) {
                int idx = binarySearch(cowZ[i], horseZ);

//                System.out.println("##############");
//                System.out.println(cowZ[i]);
//                System.out.println(idx);

                int dist1 = Integer.MAX_VALUE;
                int dist2 = Integer.MAX_VALUE;
                if (idx == horseCnt - 1) {
                    dist1 = diffX + Math.abs(cowZ[i] - horseZ[idx]);
                } else {
                    dist1 = diffX + Math.abs(cowZ[i] - horseZ[idx]);
                    dist2 = diffX + Math.abs(cowZ[i] - horseZ[idx + 1]);
                }

                boolean same = false;
                if (dist1 == dist2) {
                    same = true;
                }

                int dist = Math.min(dist1, dist2);

                if (dist < minDist) {
                    minDist = dist;
                    if (same) {
                        count = 2;
                    } else {
                        count = 1;
                    }
                } else if (dist == minDist) {
                    if (same) {
                        count += 2;
                    } else {
                        count += 1;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(minDist).append(" ").append(count).append("\n");
        }
        System.out.print(sb.toString());
    }

    private static int binarySearch(int cowZ, int[] horseZ) {
        int left = 0;
        int right = horseZ.length - 1;
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (horseZ[mid] < cowZ) {
                left = mid + 1;
                ans = mid;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
}
