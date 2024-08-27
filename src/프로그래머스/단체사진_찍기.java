package 프로그래머스;

import java.util.ArrayList;
import java.util.List;

public class 단체사진_찍기 {
    public static void main(String[] args) {
        단체사진_찍기 Main = new 단체사진_찍기();
        int n = 2;
        String[] data = {"N~F=0", "R~T>2" };
        System.out.println(Main.solution(n, data));
    }

    // {A, C, F, J, M, N, R, T} 각각 어피치, 콘, 프로도, 제이지, 무지, 네오, 라이언, 튜브

    char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    List<Condition> conditionList;
    boolean[] use;
    int answer;

    public int solution(int n, String[] data) {
        conditionList = new ArrayList<>();

        for (String cond : data) {
            char[] ch = cond.toCharArray();
            char from = ch[0];
            char to = ch[2];
            int interval = ch[4] - '0';

            conditionList.add(new Condition(from, to, ch[3], interval));
        }
        use = new boolean[8];
        answer = 0;

        permutation(new StringBuilder(), 0);

        return answer;
    }

    private void permutation(StringBuilder result, int depth) {
        if (depth == 8) {
            // 조건 만족하는지 확인
            if (isValid(result.toString())) {
                answer++;
            }
        }

        for (int i = 0; i < 8; i++) {
            if (!use[i]) {
                use[i] = true;
                result.append(friends[i]);
                permutation(result, depth + 1);
                result.deleteCharAt(depth);
                use[i] = false;
            }
        }
    }

    private boolean isValid(String result) {
        for (Condition condition : conditionList) {
            int idx1 = result.indexOf(condition.from);
            int idx2 = result.indexOf(condition.to);
            int diff = Math.abs(idx1 - idx2);
            if ('<' == condition.compare) {
                if (diff - 1 >= condition.interval) {
                    return false;
                }
            } else if ('>' == condition.compare) {
                if (diff - 1 <= condition.interval) {
                    return false;
                }
            } else {
                if (diff - 1 != condition.interval) {
                    return false;
                }
            }
        }
        return true;
    }

    private class Condition {
        int from;
        int to;
        int compare;
        int interval;

        public Condition(int from, int to, int compare, int interval) {
            this.from = from;
            this.to = to;
            this.compare = compare;
            this.interval = interval;
        }
    }
}
