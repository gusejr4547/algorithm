package 백준._20260117;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_시니어_개발자_동우의_직행_취업일기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        // 노드에 연결된 노드의 개수 4개 이상인 노드가 4개 이하....
        // 4개에 최대한 많이 달아버리면 되는거 아님?
        // 1 -> 2,3,4 -> 많이 달기
        // a, b, c 개 리프노드 달면?

        // ab + bc + ac = N
        // (a+c)(b+c) = N+c**2
        long resA = -1;
        long resB = -1;
        long resC = -1;

        for (long c = 0; c < 100_000; c++) {
            long target = N + c * c; // 좌변이 이거랑 같아지면 찾음
            long sqrtT = (long) Math.sqrt(target);

            // a, b는 1보다는 커야함
            for (long A = sqrtT; A >= 1; A--) {
                if (target % A == 0) {
                    long B = target / A;

                    if (A <= c || B <= c) {
                        continue;
                    }

                    long a = A - c;
                    long b = B - c;

                    long total = 4 + a + b + c;

                    // 10^6 보다는 작아야함
                    if (total <= 1_000_000) {
                        resA = a;
                        resB = b;
                        resC = c;
                        break;
                    }
                }

                long size = 4 + (A - c) + (target / A - c) + c; // 이게 1_000_000 넘으면 의미 없다
                if (size > 1_000_000) {
                    break;
                }
            }

            // 찾으면 끝
            if (resA != -1) {
                break;
            }
        }

        long size = 4 + resA + resB + resC;
        StringBuilder sb = new StringBuilder();
        sb.append(size).append('\n');

        // 트리
        sb.append("1 2\n").append("1 3\n").append("1 4\n");

        long nodeNum = 5;

        for (int i = 0; i < resA; i++) {
            sb.append("2 ").append(nodeNum).append('\n');
            nodeNum++;
        }
        for (int i = 0; i < resB; i++) {
            sb.append("3 ").append(nodeNum).append('\n');
            nodeNum++;
        }
        for (int i = 0; i < resC; i++) {
            sb.append("4 ").append(nodeNum).append('\n');
            nodeNum++;
        }

        System.out.println(sb);
    }
}
