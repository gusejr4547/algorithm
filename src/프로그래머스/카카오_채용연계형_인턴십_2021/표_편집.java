package 프로그래머스.카카오_채용연계형_인턴십_2021;

import java.util.ArrayDeque;
import java.util.Stack;

public class 표_편집 {
    public static void main(String[] args) {
        표_편집 Main = new 표_편집();
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};
        System.out.println(Main.solution(n, k, cmd));
    }

    public String solution(int n, int k, String[] cmd) {
        ArrayDeque<Integer> deletedRow = new ArrayDeque<>();
        int totalSize = n;
        // 현재 위치 k
        for (int i = 0; i < cmd.length; i++) {
            String[] command = cmd[i].split(" ");
            if ("U".equals(command[0])) {
                k -= Integer.parseInt(command[1]);
            } else if ("D".equals(command[0])) {
                k += Integer.parseInt(command[1]);
            } else if ("C".equals(command[0])) {
                deletedRow.push(k);
                totalSize--;
                // 마지막행 지우면 k-1
                if (k == totalSize) {
                    k--;
                }
            } else if ("Z".equals(command[0])) {
                int backupRow = deletedRow.pop();
                // 복구한 행이 현재 행보다 위에 있으면 k+1
                if (backupRow <= k) {
                    k++;
                }
                totalSize++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < totalSize; i++) {
            sb.append("O");
        }
        while(!deletedRow.isEmpty()){
            sb.insert(deletedRow.pop(), "X");
        }

        return sb.toString();
    }
}
