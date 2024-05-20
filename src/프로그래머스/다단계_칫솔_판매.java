package 프로그래머스;

import java.util.*;

public class 다단계_칫솔_판매 {
    public static void main(String[] args) {
        다단계_칫솔_판매 Main = new 다단계_칫솔_판매();
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};
        System.out.println(Arrays.toString(Main.solution(enroll, referral, seller, amount)));
    }

    // 칫솔 판매 이득 개당 100원
    // 조직도 그래프 형식으로 표현 -> 시간초과
    // 조직도 disjoint set 표현

//    List<Integer>[] adj;

    int[] parents;
    int[] profits;

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        profits = new int[enroll.length];
        parents = new int[enroll.length];
        Map<String, Integer> enrollMap = new HashMap<>();
//        adj = new List[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            enrollMap.put(enroll[i], i);
//            adj[i] = new ArrayList<>();
            parents[i] = i;
        }

        for (int i = 0; i < referral.length; i++) {
            if ("-".equals(referral[i])) {
                continue;
            } else {
                int parentIdx = enrollMap.get(referral[i]);
//                adj[i].add(parentIdx);
                parents[i] = parentIdx;
            }
        }

        for (int i = 0; i < seller.length; i++) {
            update(amount[i] * 100, enrollMap.get(seller[i]));
//            dfs(amount[i] * 100, enrollMap.get(seller[i]));
//            System.out.println(Arrays.toString(profits));
        }

        return profits;
    }

    private void update(int profit, int idx) {
        while (parents[idx] != idx) {
            int give = (int) (profit * 0.1);
            profits[idx] += profit - give;
            idx = parents[idx];
            profit = give;
        }

        profits[idx] += profit - (int) (profit * 0.1);
    }

//    private void dfs(int profit, int idx) {
//        if (adj[idx].isEmpty()) {
//            int give = (int) (profit * 0.1);
//            profits[idx] += profit - give;
//            return;
//        }
//
//        for (int parent : adj[idx]) {
//            int give = (int) (profit * 0.1);
//            profits[idx] += profit - give;
//            dfs(give, parent);
//        }
//    }
}
