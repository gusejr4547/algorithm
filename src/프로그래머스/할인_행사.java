package 프로그래머스;

import java.util.*;

public class 할인_행사 {
    public static void main(String[] args) {

    }

    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        // 자신이 원하는 제품과 수량이 할인하는 날짜와 10일 연속으로 일치할 경우'
        // 슬라이딩 윈도우
        // 10개

        HashMap<String, Integer> wantMap = new HashMap<>();
        HashMap<String, Integer> haveMap = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            String prod = want[i];
            int num = number[i];
            wantMap.put(prod, num);
        }

        // 초기 10개
        for (int i = 0; i < 10; i++) {
            String prod = discount[i];
            haveMap.put(prod, wantMap.getOrDefault(prod, 0) + 1);
        }


        for (int i = 0; i <= discount.length - 10; i++) {
            // 검증
            if (isValid(wantMap, haveMap)) {
                answer++;
            }

            // 마지막?
            if (i + 10 == discount.length) {
                break;
            }

            // 넣고
            haveMap.put(discount[i + 10], haveMap.getOrDefault(discount[i + 10], 0) + 1);

            // 빼기
            haveMap.put(discount[i], haveMap.getOrDefault(discount[i], 0) - 1);
        }

        return answer;
    }

    private boolean isValid(Map<String, Integer> wantMap, Map<String, Integer> haveMap) {
        for (Map.Entry<String, Integer> w : wantMap.entrySet()) {
            String prod = w.getKey();
            int cnt = w.getValue();

            if (haveMap.getOrDefault(prod, 0) != cnt) {
                return false;
            }
        }
        return true;
    }
}
