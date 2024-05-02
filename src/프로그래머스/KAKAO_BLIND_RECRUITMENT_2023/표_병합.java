package 프로그래머스.KAKAO_BLIND_RECRUITMENT_2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class 표_병합 {
    public static void main(String[] args) {
        표_병합 Main = new 표_병합();
//        String[] commands = {"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap",
//                "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon",
//                "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant",
//                "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle",
//                "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group",
//                "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"};
        String[] commands = {"MERGE 1 1 2 2",
                "UPDATE 1 1 A",
                "UNMERGE 1 1",
                "PRINT 1 1",
                "PRINT 2 2"};
        System.out.println(Arrays.toString(Main.solution(commands)));
    }

    // 표 50x50
    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();
        String[][] contents = new String[51][51];
        int[][] merged = new int[51][51];
        for (int r = 1; r <= 50; r++) {
            for (int c = 1; c <= 50; c++) {
                merged[r][c] = 50 * (r - 1) + c;
            }
        }

        for (String command : commands) {
            String[] splitCommand = command.split(" ");
            String operate = splitCommand[0];
            if ("UPDATE".equals(operate)) {
                if (splitCommand.length == 4) {
                    int r = Integer.parseInt(splitCommand[1]);
                    int c = Integer.parseInt(splitCommand[2]);
                    int x = merged[r][c] / 50 + 1;
                    int y = merged[r][c] % 50;
                    contents[x][y] = splitCommand[3];
                } else {
                    for (int r = 1; r <= 50; r++) {
                        for (int c = 1; c <= 50; c++) {
                            if (contents[r][c] != null && contents[r][c].equals(splitCommand[1])) {
                                contents[r][c] = splitCommand[2];
                            }
                        }
                    }
                }
            } else if ("MERGE".equals(operate)) {
                int r1 = Integer.parseInt(splitCommand[1]);
                int c1 = Integer.parseInt(splitCommand[2]);
                int r2 = Integer.parseInt(splitCommand[3]);
                int c2 = Integer.parseInt(splitCommand[4]);
                if (merged[r1][c1] == merged[r2][c2])
                    continue;

                int value = merged[r2][c2];

                int x1 = merged[r1][c1] / 50 + 1;
                int y1 = merged[r1][c1] % 50;
                int x2 = merged[r2][c2] / 50 + 1;
                int y2 = merged[r2][c2] % 50;
                for (int r = 1; r <= 50; r++) {
                    for (int c = 1; c <= 50; c++) {
                        if (merged[r][c] == value) {
                            merged[r][c] = merged[r1][c1];
                        }
                    }
                }

                if (contents[x1][y1] == null && contents[x2][y2] != null) {
                    contents[x1][y1] = contents[x2][y2];
                } else {
                    contents[x2][y2] = contents[x1][y1];
                }

            } else if ("UNMERGE".equals(operate)) {
                int rr = Integer.parseInt(splitCommand[1]);
                int cc = Integer.parseInt(splitCommand[2]);
                int value = merged[rr][cc];
                int x = value / 50 + 1;
                int y = value % 50;
                String tmp = contents[x][y];

                for (int r = 1; r <= 50; r++) {
                    for (int c = 1; c <= 50; c++) {
                        if (merged[r][c] == value) {
                            merged[r][c] = 50 * (r - 1) + c;
                            contents[r][c] = null;
                        }
                    }
                }
                contents[rr][cc] = tmp;

            } else if ("PRINT".equals(operate)) {
                int r = Integer.parseInt(splitCommand[1]);
                int c = Integer.parseInt(splitCommand[2]);
                int value = merged[r][c];
                int x = value / 50 + 1;
                int y = value % 50;

                answer.add(contents[x][y] == null ? "EMPTY" : contents[x][y]);
            }
        }

        return answer.toArray(new String[0]);
    }
}
