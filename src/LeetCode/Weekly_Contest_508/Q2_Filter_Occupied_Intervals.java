package LeetCode.Weekly_Contest_508;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q2_Filter_Occupied_Intervals {
    public List<List<Integer>> filterOccupiedIntervals(int[][] occupiedIntervals, int freeStart, int freeEnd) {
        Arrays.sort(occupiedIntervals, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        List<int[]> merged = new ArrayList<>();
        int curS = occupiedIntervals[0][0];
        int curE = occupiedIntervals[0][1];

        for (int i = 1; i < occupiedIntervals.length; i++) {
            int s = occupiedIntervals[i][0];
            int e = occupiedIntervals[i][1];

            // 분리
            if (curE + 1 < s) {
                merged.add(new int[]{curS, curE});
                curS = s;
                curE = e;
            }
            // 병합
            else {
                curE = Math.max(curE, e);
            }
        }
        // 남은 구간
        merged.add(new int[]{curS, curE});

//        for(int[] m : merged){
//            System.out.println(Arrays.toString(m));
//        }

        List<List<Integer>> answer = new ArrayList<>();
        // free로 다시 나누기
        for (int[] mm : merged) {
            // 안겹침
            if (mm[1] < freeStart || mm[0] > freeEnd) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(mm[0]);
                tmp.add(mm[1]);
                answer.add(tmp);
            } else {
                if (mm[0] < freeStart) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(mm[0]);
                    tmp.add(freeStart - 1);
                    answer.add(tmp);
                }

                if (freeEnd < mm[1]) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(freeEnd + 1);
                    tmp.add(mm[1]);
                    answer.add(tmp);
                }
            }
        }

        return answer;
    }
}
