package 프로그래머스.PCCP기출문제2;

import java.util.*;

public class 충돌위험_찾기 {
    public static void main(String[] args) {
        충돌위험_찾기 Main = new 충돌위험_찾기();
//        int[][] points = {{2, 2}, {2, 3}, {2, 7}, {6, 6}, {5, 2}};
//        int[][] routes = {{2, 3, 4, 5}, {1,3,4,5}};
        int[][] points = {{3, 2}, {6, 4}, {4, 7}, {1, 4}};
        int[][] routes = {{4, 2}, {1, 3}, {2, 4}};
//        int[][] points = {{3, 2}, {6, 4}, {4, 7}, {1, 4}};
//        int[][] routes = {{4, 2}, {1, 3}, {4, 2}, {4, 3}};
        System.out.println(Main.solution(points, routes));
    }

    // 이동중 같은 좌표에 로봇 2대 이상 모이면 위험 상황
    // 포인트 주어짐.
    // 로봇마다 운송경로
    // 로봇 동시에 출발
    // 다음 포인트로 이동할 때 최단경로로 이동 => r좌표가 먼저 이동
    public int solution(int[][] points, int[][] routes) {
        int robotCnt = routes.length;
        int routeLen = routes[0].length;
        int[][][] visit = new int[101][101][robotCnt];
        ArrayDeque<Robot> queue = new ArrayDeque<>();
        // 각 로봇 시작지점 queue에 넣기
        for(int i=0; i<routes.length; i++){
            int[] route = routes[i];
            int[] startPoint = points[route[0]-1];
            queue.offer(new Robot(i, startPoint[0], startPoint[1], 1, 1));
        }

        int answer = 0;
        ArrayDeque<Robot> nextQueue = new ArrayDeque<>();

        while(true){
            Map<Integer, Integer> map = new HashMap<>(); // r*100 + c 로 넣기
            // 모든 로봇 1칸씩 이동, 경로 겹치는지 확인
            while(!queue.isEmpty()){
                Robot cur = queue.poll();
                int[] nextPoint = points[routes[cur.robotIdx][cur.targetPointIdx]-1];

                int convert = cur.r*100+cur.c;
                map.put(convert, map.getOrDefault(convert, 0) + 1);

                // 종료
                if(cur.targetPointIdx == routeLen-1 && nextPoint[0] == cur.r && nextPoint[1] == cur.c){
                    continue;
                }

                // nextPoint 갱신필요?
                if(nextPoint[0] == cur.r && nextPoint[1] == cur.c){
                    cur.targetPointIdx += 1;
                    nextPoint = points[routes[cur.robotIdx][cur.targetPointIdx]-1];
                }
                // nextPoint를 향해 이동
                if(nextPoint[0] != cur.r){
                    int nr = cur.r > nextPoint[0] ? cur.r - 1 : cur.r + 1;
                    nextQueue.offer(new Robot(cur.robotIdx, nr, cur.c, cur.time+1, cur.targetPointIdx));
                }else{
                    int nc = cur.c > nextPoint[1] ? cur.c - 1 : cur.c + 1;
                    nextQueue.offer(new Robot(cur.robotIdx, cur.r, nc, cur.time+1, cur.targetPointIdx));
                }
            }

            // 경로 중복 확인
            // map의 value가 2이상이면 겹침
            for(int value : map.values()){
                if(value >= 2){
                    answer++;
                }
            }

            if(nextQueue.isEmpty()){
                break;
            }
            queue = new ArrayDeque<>(nextQueue);
            nextQueue.clear();
        }

        return answer;
    }

    private class Robot{
        int robotIdx;
        int r, c;
        int time;
        int targetPointIdx;

        Robot(int robotIdx, int r, int c, int time, int targetPointIdx){
            this.robotIdx = robotIdx;
            this.r = r;
            this.c = c;
            this.time = time;
            this.targetPointIdx = targetPointIdx;
        }
    }
}
