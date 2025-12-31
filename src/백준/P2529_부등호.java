package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2529_부등호 {
    // 부등호 기호 앞뒤에 넣을 수 있는 숫자는 0부터 9까지의 정수이며 선택된 숫자는 모두 달라야 한다.
    // 최대, 최소 정수를 첫째 줄과 둘째 줄에 각각 출력
    static int k;
    static char[] result;
    static char[] ops;
    static boolean[] use;
    static long minNum = Long.MAX_VALUE, maxNum = Long.MIN_VALUE;
    static String minNumStr, maxNumStr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ops = new char[k];
        for (int i = 0; i < k; i++) {
            ops[i] = st.nextToken().charAt(0);
        }

        result = new char[k + 1];
        use = new boolean[10];

        for (char num = '9'; num >= '0'; num--) {
            use[num - '0'] = true;
            result[0] = num;
            makeBiggestNum(0);
            use[num - '0'] = false;

            if (maxNum != Long.MIN_VALUE) {
                break;
            }
        }

        Arrays.fill(use, false);
        for (char num = '0'; num <= '9'; num++) {
            use[num - '0'] = true;
            result[0] = num;
            makeSmallestNum(0);
            use[num - '0'] = false;

            if (minNum != Long.MAX_VALUE) {
                break;
            }
        }

        System.out.println(maxNumStr);
        System.out.println(minNumStr);
    }

    private static void makeBiggestNum(int idx) {
        if (idx == k) {
            long num = Long.parseLong(String.valueOf(result));
            if (maxNum < num) {
                maxNum = num;
                maxNumStr = String.valueOf(result);
            }
            return;
        }

        char op = ops[idx];
        for (char num = '9'; num >= '0'; num--) {
            if (use[num - '0']) {
                continue;
            }

            if ('<' == op) {
                if (result[idx] < num) {
                    result[idx + 1] = num;
                    use[num - '0'] = true;
                    makeBiggestNum(idx + 1);
                    use[num - '0'] = false;
                }
            } else if ('>' == op) {
                if (result[idx] > num) {
                    result[idx + 1] = num;
                    use[num - '0'] = true;
                    makeBiggestNum(idx + 1);
                    use[num - '0'] = false;
                }
            }
        }
    }

    private static void makeSmallestNum(int idx) {
        if (idx == k) {
            long num = Long.parseLong(String.valueOf(result));
            if (minNum > num) {
                minNum = num;
                minNumStr = String.valueOf(result);
            }
            return;
        }

        char op = ops[idx];
        for (char num = '0'; num <= '9'; num++) {
            if (use[num - '0']) {
                continue;
            }

            if ('<' == op) {
                if (result[idx] < num) {
                    result[idx + 1] = num;
                    use[num - '0'] = true;
                    makeSmallestNum(idx + 1);
                    use[num - '0'] = false;
                }
            } else if ('>' == op) {
                if (result[idx] > num) {
                    result[idx + 1] = num;
                    use[num - '0'] = true;
                    makeSmallestNum(idx + 1);
                    use[num - '0'] = false;
                }
            }
        }
    }
}
