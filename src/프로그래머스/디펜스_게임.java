package 프로그래머스;

import java.util.PriorityQueue;

public class 디펜스_게임 {
    public static void main(String[] args) {
        디펜스_게임 Main = new 디펜스_게임();
        int n = 7;
        int k = 3;
        int[] enemy = {4, 2, 4, 5, 3, 3, 1};
        System.out.println(Main.solution(n, k, enemy));
    }

    public int solution(int n, int k, int[] enemy) {
        int answer = 0;

        // 내 병사를 활용해 없앤 적의 수
        int enemySum = 0;
        // 무적권 사용해서 없앤 적의 수
        PriorityQueue<Integer> eliminatedEnemies = new PriorityQueue<>();
        int i = 0;
        for (i = 0; i < enemy.length; i++) {
            int curEnemy = enemy[i];

            if (eliminatedEnemies.size() < k) {
                eliminatedEnemies.offer(curEnemy);
            } else {
                // 무적권을 사용할때 적의 수가 많으면 좋다
                // 적의 수를 비교해 사용할 시기를 변경한다
                // enemySum < n 일 동안 계속 반복

                int min = eliminatedEnemies.peek();

                if (curEnemy > min) {
                    eliminatedEnemies.poll();
                    eliminatedEnemies.offer(curEnemy);
                    curEnemy = min;
                }

                if (enemySum + curEnemy > n) {
                    answer = i;
                    break;
                } else {
                    enemySum += curEnemy;
                }
            }
        }

        if(i == enemy.length){
            answer = i;
        }

        return answer;
    }
}
