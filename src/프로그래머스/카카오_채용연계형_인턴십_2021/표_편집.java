package 프로그래머스.카카오_채용연계형_인턴십_2021;

import java.util.ArrayDeque;
import java.util.Stack;

public class 표_편집 {
    public static void main(String[] args) {
        표_편집 Main = new 표_편집();
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
        System.out.println(Main.solution(n, k, cmd));
    }

    int[][] parents;
    final static int UP = 0;
    final static int DOWN = 1;

    public String solution(int n, int k, String[] cmd) {
        parents = new int[n + 1][2]; // 0은 up, 1은 down

        for (int i = 0; i <= n; i++) {
            parents[i][0] = i;
            parents[i][1] = i;
        }

        ArrayDeque<Integer> backup = new ArrayDeque<>();

        // 현재 위치 k
        // 행번호 끝번호가 삭제되면 인덱스 n으로 보내기
        for (int i = 0; i < cmd.length; i++) {
            String[] command = cmd[i].split(" ");
            if ("U".equals(command[0])) {
                k = nextRow(k, Integer.parseInt(command[1]), UP);
            } else if ("D".equals(command[0])) {
                k = nextRow(k, Integer.parseInt(command[1]), DOWN);
            } else if ("C".equals(command[0])) {
                // 각각 다음 행 가르키도록하고
                parents[k][0] = k - 1;
                parents[k][1] = k + 1;
                if (k == 0) {
                    parents[k][0] = n;
                } else if (k == n - 1) {
                    parents[k][1] = n;
                }
                backup.push(k);

                // 위치 아래 행으로 변경
                int next = find(k, DOWN);
                if (next == n) {
                    // 아래행 없음
                    next = find(k, UP);
                }
                k = next;
            } else if ("Z".equals(command[0])) {
                int backupRow = backup.pop();
                parents[backupRow][0] = backupRow;
                parents[backupRow][1] = backupRow;
            }
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (parents[i][0] == i) {
                sb.append("O");
            } else {
                sb.append("X");
            }
        }

        return sb.toString();
    }


    public int nextRow(int x, int offset, int CMD) {
        for (int i = 0; i < offset; i++) {
            if (CMD == UP) {
                x = find(x - 1, CMD);
            } else {
                x = find(x + 1, CMD);
            }
        }
        return x;
    }

    public int find(int x, int CMD) {
        int parent = parents[x][CMD];
        if (x == parent) {
            return x;
        }
        return find(parent, CMD);
    }
}
