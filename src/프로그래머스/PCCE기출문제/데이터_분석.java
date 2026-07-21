package 프로그래머스.PCCE기출문제;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 데이터_분석 {
    public static void main(String[] args) {

    }

    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        int extId = getId(ext);
        int sortById = getId(sort_by);

        List<int[]> result = new ArrayList<>();

        for (int[] d : data) {
            if (d[extId] < val_ext) {
                result.add(d);
            }
        }

        result.sort((o1, o2) -> Integer.compare(o1[sortById], o2[sortById]));

        return result.toArray(int[][]::new);
    }

    private int getId(String field) {
        if ("code".equals(field)) {
            return 0;
        } else if ("date".equals(field)) {
            return 1;
        } else if ("maximum".equals(field)) {
            return 2;
        } else if ("remain".equals(field)) {
            return 3;
        }
        return -1;
    }
}
