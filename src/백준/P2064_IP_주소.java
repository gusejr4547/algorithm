package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2064_IP_주소 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] ipAddress = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), ".");
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                ipAddress[i] += (num << (8 * (3 - j)));
            }
        }

        int mask = 0;
        for (int i = 31; i >= 0; i--) {
            // i번째 bit 1 or 0
            int bit = (ipAddress[0] & (1 << i)) != 0 ? 1 : 0;

            boolean same = true;
            for (int j = 1; j < N; j++) {
                int target = (ipAddress[j] & (1 << i)) != 0 ? 1 : 0;
                if (bit != target) {
                    same = false;
                    break;
                }
            }

            if (same) {
                mask |= (1 << i);
            } else {
                break;
            }
        }

        int networkAddress = ipAddress[0] & mask;

        System.out.println(integer2Ip(networkAddress));
        System.out.println(integer2Ip(mask));
    }

    private static String integer2Ip(int ip) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            // 8자리 잘라서 255 mask
            int num = (ip >> (8 * (3 - i))) & 255;
            sb.append(num).append('.');
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}
