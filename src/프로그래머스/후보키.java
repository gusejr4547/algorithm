package 프로그래머스;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 후보키 {
    public static void main(String[] args) {
        후보키 Main = new 후보키();
        String[][] relation = {{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"},
                {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}};
        System.out.println(Main.solution(relation));
    }

    String[][] Relation;
    int row;
    int column;
    Set<List<Integer>> candidateKey;

    public int solution(String[][] relation) {
        Relation = relation;
        candidateKey = new HashSet<>();

        // 컬럼은 최대 8개
        row = relation.length;
        column = relation[0].length;
        for (int keyLen = 1; keyLen <= column; keyLen++) {
            List<Integer> key = new ArrayList<>();
            comb(0, keyLen, key);
        }


        int answer = candidateKey.size();
        return answer;
    }

    private void comb(int idx, int keyLen, List<Integer> key) {
        // 지금까지 만든 key가 후보키에 존재하면 최소성 만족 못함
        boolean isMin = true;
        for (List<Integer> k : candidateKey) {
            int size = k.size();
            for (int c : k) {
                if (key.contains(c)) {
                    size--;
                }
            }
            if (size == 0) {
                isMin = false;
                break;
            }
        }
        if (!isMin) return;

        if (key.size() == keyLen) {
            // key로 식별가능?
            Set<String> set = new HashSet<>();
            for (int r = 0; r < row; r++) {
                StringBuilder info = new StringBuilder();
                for (int c : key) {
                    info.append(Relation[r][c]);
                }
                set.add(info.toString());
            }

            // set이 row만큼 있으면 중복없음.
            if (set.size() == row) {
                candidateKey.add(new ArrayList<>(key));
            }

            return;
        }

        for (int i = idx; i < column; i++) {
            key.add(i);
            comb(i + 1, keyLen, key);
            key.remove(key.size() - 1);
        }
    }
}
