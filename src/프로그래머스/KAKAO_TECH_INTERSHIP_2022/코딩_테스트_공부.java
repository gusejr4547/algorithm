package 프로그래머스.KAKAO_TECH_INTERSHIP_2022;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 코딩_테스트_공부 {
    public static void main(String[] args) {
        int alp = 0;
        int cop = 0;
//        int[][] problems = {{0, 0, 2, 1, 2}, {4, 5, 3, 1, 2}, {4, 11, 4, 0, 2}, {10, 4, 0, 4, 2}};
        int[][] problems = {{0, 0, 30, 2, 1}, {150, 150, 30, 30, 100}};
        System.out.println(solution(alp, cop, problems));
    }

    public static int solution(int alp, int cop, int[][] problems) {
        int answer = 0;

        // 최대 alp, cop?
        int maxAlpReq = problems[0][0];
        int maxCopReq = problems[0][1];

        for (int i = 0; i < problems.length; i++) {
            if (problems[i][0] > maxAlpReq) {
                maxAlpReq = problems[i][0];
            }
            if (problems[i][1] > maxCopReq) {
                maxCopReq = problems[i][1];
            }
        }

        // 초기 alp, cop 에서 최대 alp, cop 로 가는 가장 빠른 방법
        // PriorityQueue 를 사용해서 cost 가 적은거 먼저 처리해봄?
        PriorityQueue<Ability> pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);
        pq.offer(new Ability(alp, cop, 0));

        /*
        alp +1 => cost +1
        cop +1 => cost +1
        problems 중에 풀수있는 문제
        */

        int[][] visit = new int[151][151];

        for (int i = 0; i < 151; i++) {
            Arrays.fill(visit[i], Integer.MAX_VALUE);
        }

        while (!pq.isEmpty()) {
            Ability ability = pq.poll();
            int currAlp = ability.alp;
            int currCop = ability.cop;

//            System.out.println(ability);

            if (currAlp >= maxAlpReq && currCop >= maxCopReq) {
                answer = ability.cost;
                break;
            }

            if (visit[Math.min(currAlp, 150)][Math.min(currCop, 150)] <= ability.cost) {
                continue;
            }
            visit[Math.min(currAlp, 150)][Math.min(currCop, 150)] = ability.cost;

            for (int[] problem : problems) {
                int problemAlp = problem[0];
                int problemCop = problem[1];
                if (problemAlp <= currAlp && problemCop <= currCop) {
                    pq.offer(new Ability(currAlp + problem[2], currCop + problem[3], ability.cost + problem[4]));
                } else if (problemAlp <= currAlp && problemCop > currCop) {
                    int diffCop = problemCop - currCop;
                    pq.offer(new Ability(currAlp, problemCop, ability.cost + diffCop));
                } else if (problemAlp > currAlp && problemCop <= currCop) {
                    int diffAlp = problemAlp - currAlp;
                    pq.offer(new Ability(problemAlp, currCop, ability.cost + diffAlp));
                } else {
                    int diffAlp = problemAlp - currAlp >= 0 ? problemAlp - currAlp : 0;
                    int diffCop = problemCop - currCop >= 0 ? problemCop - currCop : 0;
                    pq.offer(new Ability(problemAlp, problemCop, ability.cost + diffAlp + diffCop));
                }
            }
        }

        return answer;
    }

    public static class Ability {
        int alp;
        int cop;
        int cost;

        Ability() {
        }

        Ability(int alp, int cop, int cost) {
            this.alp = alp;
            this.cop = cop;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Ability{" +
                    "alp=" + alp +
                    ", cop=" + cop +
                    ", cost=" + cost +
                    '}';
        }
    }
}
