package 프로그래머스;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class 의상 {
    public static void main(String[] args) {
        의상 Main = new 의상();
        String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
        System.out.println(Main.solution(clothes));
    }

    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();

        for (String[] cloth : clothes) {
            map.put(cloth[1], map.getOrDefault(cloth[1], 0) + 1);
        }

        int answer = 1;
        for (String key : map.keySet()) {
            answer *= map.get(key) + 1;
        }

        return answer - 1;
    }
}
