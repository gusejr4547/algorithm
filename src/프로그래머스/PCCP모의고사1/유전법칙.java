package 프로그래머스.PCCP모의고사1;

import java.util.Arrays;

public class 유전법칙 {
    public static void main(String[] args) {
        System.out.println(Math.ceil(1/4));
        유전법칙 Main = new 유전법칙();
        int[][] queries = {{3, 1}, {2, 2}};
        System.out.println(Arrays.toString(Main.solution(queries)));
    }

    public String[] solution(int[][] queries) {
        String[] answer = new String[queries.length];

        // RR -> 전부 RR
        // Rr -> RR, Rr, Rr, rr
        // rr -> 전부 rr

        for (int i = 0; i < queries.length; i++) {
            int generation = queries[i][0];
            int order = queries[i][1];

            String result = getGenetic(generation, order);
            answer[i] = result;
        }


        return answer;
    }

    private String getGenetic(int generation, int order) {
        if (generation == 1) {
            return "Rr";
        }

        String parent = getGenetic(generation - 1, (int) Math.ceil(order / 4.0));
        if ("RR".equals(parent)) {
            return "RR";
        } else if ("rr".equals(parent)) {
            return "rr";
        } else {
            int num = order % 4;
            switch (num) {
                case 1 -> {
                    return "RR";
                }
                case 2, 3 -> {
                    return "Rr";
                }
                default -> {
                    return "rr";
                }
            }
        }
    }
}
