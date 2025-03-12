package 코드트리;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 사다리_타기 {
    static List<Bridge> bridgeList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        boolean[][] board = new boolean[15][n];
        bridgeList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;

            board[b][a] = true;
            bridgeList.add(new Bridge(b, a));
        }
        // Please write your code here.
        // 주어진거랑 같은 결과가 나오게 가로줄 최소로 그어봐라

        // 주어진거 결과 일단 필요함
        int[] originalResult = new int[n];
        for (int num = 0; num < n; num++) {
            int endLine = goToEnd(num, board);
            originalResult[endLine] = num;
        }
//        System.out.println(Arrays.toString(originalResult));

        int answer = 0;
        for (int maxBridgeCnt = 0; maxBridgeCnt <= m; maxBridgeCnt++) {
            if (selectBridge(0, 0, maxBridgeCnt, new boolean[15][n], originalResult)) {
                answer = maxBridgeCnt;
                break;
            }
        }

        System.out.println(answer);
    }

    private static boolean selectBridge(int bridgeIdx, int bridgeCnt, int maxBridgeCnt, boolean[][] board, int[] originalResult) {
        if (bridgeCnt == maxBridgeCnt) {
            for (int num = 0; num < originalResult.length; num++) {
                int endLine = goToEnd(num, board);
                if (originalResult[endLine] != num) {
                    return false;
                }
            }
            return true;
        }

        boolean result = false;

        for (int i = bridgeIdx; i < bridgeList.size(); i++) {
            board[bridgeList.get(i).y][bridgeList.get(i).x] = true;
            result |= selectBridge(i + 1, bridgeCnt + 1, maxBridgeCnt, board, originalResult);
            board[bridgeList.get(i).y][bridgeList.get(i).x] = false;
        }

        return result;
    }

    private static int goToEnd(int startNum, boolean[][] board) {
        int line = startNum;
        for (int i = 0; i < board.length; i++) {
            // 오른쪽
            if (board[i][line]) {
                line += 1;
            }
            // 왼쪽
            else if (line > 0 && board[i][line - 1]) {
                line -= 1;
            }
            // 아래로
            else {
                continue;
            }
        }

        return line;
    }

    private static class Bridge {
        int y, x;

        public Bridge(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
