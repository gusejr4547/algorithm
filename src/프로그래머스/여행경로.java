package 프로그래머스;

import java.util.*;

public class 여행경로 {
    public static void main(String[] args) {
        여행경로 Main = new 여행경로();
        String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        System.out.println(Arrays.toString(Main.solution(tickets)));
    }

    // 모든 티켓 사용해야함
    // 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 합니다.
    boolean[] useTicket;
    List<String> answer;

    public String[] solution(String[][] tickets) {
        int size = 0;
        useTicket = new boolean[tickets.length];
        answer = new ArrayList<>();

        dfs(0, "ICN", "ICN", tickets);

        Collections.sort(answer);

        return answer.get(0).split(",");
    }

    public void dfs(int count, String current, String result, String[][] tickets) {
        if (count == tickets.length) {
            answer.add(result);
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!useTicket[i] && tickets[i][0].equals(current)) {
                useTicket[i] = true;
                dfs(count + 1, tickets[i][1], result + "," + tickets[i][1], tickets);
                useTicket[i] = false;
            }
        }
    }
}
