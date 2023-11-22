package 프로그래머스.KAKAO_TECH_INTERSHIP_2022;

import java.util.PriorityQueue;

public class 코딩_테스트_공부 {
    public static void main(String[] args) {
        int alp = 0;
        int cop = 0;
        int[][] problems = {{}};
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

        while(!pq.isEmpty()){
            Ability ability = pq.poll();
            int currAlp = ability.alp;
            int currCop = ability.cop;

            if(currAlp >= maxAlpReq && currCop >= maxCopReq){
                answer = ability.cost;
                break;
            }

            for(int[] problem : problems){
                int problemAlp = problem[0];
                int problemCop = problem[1];
                if(problemAlp<=currAlp && problemCop<=currCop){
                    pq.offer(new Ability(currAlp+problem[2], currCop+problem[3], ability.cost+problem[4]));
                }else{
                    int diffAlp = problemAlp - currAlp;
                    int diffCop = problemCop - currCop;
                    pq.offer(new Ability(problemAlp + problem[2], problemCop+problem[3], ability.cost+problem[4]+diffAlp+diffCop));
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
    }
}
