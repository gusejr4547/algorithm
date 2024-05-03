package 프로그래머스.KAKAO_BLIND_RECRUITMENT_2023;

import java.util.*;
import java.util.stream.Collectors;

public class 표_병합 {
    public static void main(String[] args) {
        표_병합 Main = new 표_병합();
        String[] commands = {"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"};
//        String[] commands = {"MERGE 1 1 2 2", "MERGE 1 1 3 3", "UPDATE 3 3 A", "PRINT 1 1", "PRINT 2 2", "PRINT 3 3"};
        System.out.println(Arrays.toString(Main.solution(commands)));
    }

    int[] parents;
    String[] contents;

    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();
        parents = new int[2501];
        contents = new String[2501];

        for (int i = 1; i <= 2500; i++) {
            parents[i] = i;
            contents[i] = "";
        }

        for (String command : commands) {
            StringTokenizer st = new StringTokenizer(command);
            String operate = st.nextToken();

            if ("UPDATE".equals(operate)) {
                if (st.countTokens() == 3) {
                    int r = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    String value = st.nextToken();
                    int num = convertRC(r, c);
                    contents[find(num)] = value;
                } else {
                    String value1 = st.nextToken();
                    String value2 = st.nextToken();
                    for (int i = 1; i <= 2500; i++) {
                        if (contents[i].equals(value1)) {
                            contents[i] = value2;
                        }
                    }
                }
            } else if ("MERGE".equals(operate)) {
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());
                int num1 = convertRC(r1, c1);
                int num2 = convertRC(r2, c2);
                int p1 = find(num1);
                int p2 = find(num2);
                if (p1 == p2) {
                    continue;
                }
                String content = contents[p1].isBlank() ? contents[p2] : contents[p1];
                contents[p1] = "";
                contents[p2] = "";
                union(num1, num2);
                contents[p1] = content;
            } else if ("UNMERGE".equals(operate)) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int num = convertRC(r, c);
                int p = find(num);
                String content = contents[p];
                contents[p] = "";
                contents[num] = content;
                List<Integer> deleteList = new ArrayList<>();
                for (int i = 1; i <= 2500; i++) {
                    if (find(i) == p) {
                        deleteList.add(i);
                    }
                }
                for (Integer i : deleteList) {
                    parents[i] = i;
                }
            } else if ("PRINT".equals(operate)) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int num = convertRC(r, c);
                int p = find(num);
                answer.add(contents[p].isBlank() ? "EMPTY" : contents[p]);
            }
        }

        return answer.toArray(new String[0]);
    }

    public void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parents[b] = a;
        }
    }

    public int find(int a) {
        if (parents[a] == a) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }

    public int convertRC(int r, int c) {
        return 50 * (r - 1) + c;
    }

    // 표 50x50
//    public String[] solution(String[] commands) {
//        List<String> answer = new ArrayList<>();
//        String[][] contents = new String[51][51];
//        int[][] merged = new int[51][51];
//        for (int r = 1; r <= 50; r++) {
//            for (int c = 1; c <= 50; c++) {
//                merged[r][c] = 50 * (r - 1) + c;
//            }
//        }
//
//        for (String command : commands) {
//            String[] splitCommand = command.split(" ");
//            String operate = splitCommand[0];
//            if ("UPDATE".equals(operate)) {
//                if (splitCommand.length == 4) {
//                    int r = Integer.parseInt(splitCommand[1]);
//                    int c = Integer.parseInt(splitCommand[2]);
//                    int x = merged[r][c] / 50 + 1;
//                    int y = merged[r][c] % 50;
//                    contents[x][y] = splitCommand[3];
//                } else {
//                    for (int r = 1; r <= 50; r++) {
//                        for (int c = 1; c <= 50; c++) {
//                            if (contents[r][c] != null && contents[r][c].equals(splitCommand[1])) {
//                                contents[r][c] = splitCommand[2];
//                            }
//                        }
//                    }
//                }
//            } else if ("MERGE".equals(operate)) {
//                int r1 = Integer.parseInt(splitCommand[1]);
//                int c1 = Integer.parseInt(splitCommand[2]);
//                int r2 = Integer.parseInt(splitCommand[3]);
//                int c2 = Integer.parseInt(splitCommand[4]);
//                if (merged[r1][c1] == merged[r2][c2])
//                    continue;
//
//                int value = merged[r2][c2];
//
//                int x1 = merged[r1][c1] / 50 + 1;
//                int y1 = merged[r1][c1] % 50;
//                int x2 = merged[r2][c2] / 50 + 1;
//                int y2 = merged[r2][c2] % 50;
//                for (int r = 1; r <= 50; r++) {
//                    for (int c = 1; c <= 50; c++) {
//                        if (merged[r][c] == value) {
//                            merged[r][c] = merged[r1][c1];
//                        }
//                    }
//                }
//                if (contents[x1][y1] == null && contents[x2][y2] == null) {
//                    continue;
//                } else if (contents[x1][y1] == null && contents[x2][y2] != null) {
//                    contents[x1][y1] = contents[x2][y2];
//                } else if (contents[x1][y1] != null && contents[x2][y2] == null) {
//                    contents[x2][y2] = contents[x1][y1];
//                } else {
//                    contents[x2][y2] = contents[x1][y1];
//                }
//
//            } else if ("UNMERGE".equals(operate)) {
//                int rr = Integer.parseInt(splitCommand[1]);
//                int cc = Integer.parseInt(splitCommand[2]);
//                int value = merged[rr][cc];
//                int x = value / 50 + 1;
//                int y = value % 50;
//                String tmp = contents[x][y];
//
//                for (int r = 1; r <= 50; r++) {
//                    for (int c = 1; c <= 50; c++) {
//                        if (merged[r][c] == value) {
//                            merged[r][c] = 50 * (r - 1) + c;
//                            contents[r][c] = null;
//                        }
//                    }
//                }
//                contents[rr][cc] = tmp;
//
//            } else if ("PRINT".equals(operate)) {
//                int r = Integer.parseInt(splitCommand[1]);
//                int c = Integer.parseInt(splitCommand[2]);
//                int value = merged[r][c];
//                int x = value / 50 + 1;
//                int y = value % 50;
//
//                answer.add(contents[x][y] == null ? "EMPTY" : contents[x][y]);
//            }
//        }
//
//        return answer.toArray(new String[0]);
//    }
}
