package 프로그래머스;

import java.util.*;

public class 순위_검색 {
    public static void main(String[] args) {
        순위_검색 Main = new 순위_검색();
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210",
                "python frontend senior chicken 150", "cpp backend senior pizza 260",
                "java backend junior chicken 80", "python backend senior chicken 50" };
        String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250", "- and backend and senior and - 150",
                "- and - and - and chicken 100", "- and - and - and - 150" };
        System.out.println(Arrays.toString(Main.solution(info, query)));
    }


    // 조건 3 2 2 2
    Map<String, String> map;
    Map<String, List<Integer>> infoMap;
    String[] condition;

    public int[] solution(String[] info, String[] query) {
        init();
        infoMap = new HashMap<>();
        putInfoMap(info);

        // map value 정렬
        for (List<Integer> list : infoMap.values()) {
            list.sort(Comparator.naturalOrder());
        }

        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String q = query[i];
            String[] sub = q.split(" ");
            condition = new String[4];
            condition[0] = sub[0];
            condition[1] = sub[2];
            condition[2] = sub[4];
            condition[3] = sub[6];
            int score = Integer.parseInt(sub[7]);

            answer[i] = find("", 0, score);
        }

        return answer;
    }

    private int find(String key, int depth, int score) {
        if (depth == 4) {
            if (!infoMap.containsKey(key)) {
                return 0;
            }

            List<Integer> valueList = infoMap.get(key);
            // 이진탐색
            int left = 0;
            int right = valueList.size() - 1;
            int result = valueList.size();
            while (left <= right) {
                int mid = (left + right) / 2;

                int value = valueList.get(mid);
                if (value < score) {
                    left = mid + 1;
                } else {
                    result = mid;
                    right = mid - 1;
                }
            }

            return valueList.size() - result;
        }

        int sum = 0;
        if (!"-".equals(condition[depth])) {
            sum += find(key + map.get(condition[depth]), depth + 1, score);
        } else {
            sum += find(key + 0, depth + 1, score);
            sum += find(key + 1, depth + 1, score);
            if (depth == 0) sum += find(key + 2, depth + 1, score);
        }
        return sum;
    }


    private void putInfoMap(String[] info) {
        for (int i = 0; i < info.length; i++) {
            String[] sub = info[i].split(" ");
            StringBuilder keyBuilder = new StringBuilder();
            for (int t = 0; t < 4; t++) {
                keyBuilder.append(map.get(sub[t]));
            }
            String key = keyBuilder.toString();
            if (!infoMap.containsKey(key)) {
                List<Integer> value = new ArrayList<>();
                value.add(Integer.parseInt(sub[4]));
                infoMap.put(key, value);
            } else {
                infoMap.get(key).add(Integer.parseInt(sub[4]));
            }
        }
    }

    private void init() {
        map = new HashMap<>();
        map.put("cpp", "0");
        map.put("java", "1");
        map.put("python", "2");
        map.put("backend", "0");
        map.put("frontend", "1");
        map.put("junior", "0");
        map.put("senior", "1");
        map.put("chicken", "0");
        map.put("pizza", "1");
    }
}
