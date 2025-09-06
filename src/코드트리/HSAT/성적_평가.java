package 코드트리.HSAT;

import java.util.*;

public class 성적_평가 {
    static int N;
    static int[][] scores;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        scores = new int[3][N];
        for (int contest = 0; contest < 3; contest++) {
            for (int i = 0; i < N; i++) {
                scores[contest][i] = sc.nextInt();
            }
        }

        // 누적합
//        solution1();

        // 정렬
        solution2();
    }

    private static void solution2() {
        PlayerScore[][] playerScores = new PlayerScore[4][N];

        for (int test = 0; test < 3; test++) {
            for (int i = 0; i < N; i++) {
                playerScores[test][i] = new PlayerScore(scores[test][i], i);
                if (playerScores[3][i] == null) {
                    playerScores[3][i] = new PlayerScore(scores[test][i], i);
                } else {
                    playerScores[3][i].score += scores[test][i];
                }
            }
        }

        int[][] rnk = new int[4][N];
        StringBuilder sb = new StringBuilder();
        // 등수 계산
        for (int test = 0; test < 4; test++) {
            Arrays.sort(playerScores[test], (o1, o2) -> Integer.compare(o1.score, o2.score));

            int i = N - 1;
            int rank = 1;
            while (i >= 0) {
                int s = playerScores[test][i].score;
                rnk[test][playerScores[test][i].id] = rank;

                int j = i - 1;
                while (j >=0 && s == playerScores[test][j].score) {
                    rnk[test][playerScores[test][j].id] = rank;
                    j--;
                }
                rank += i - j;
                i = j;
            }
        }

        // 출력
        for (int test = 0; test < 4; test++) {
            for (int i = 0; i < N; i++) {
                sb.append(rnk[test][i]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static class PlayerScore {
        int score, id;

        public PlayerScore(int score, int id) {
            this.score = score;
            this.id = id;
        }
    }

    private static void solution1() {
        // 나보다 점수가 큰사람 수 + 1 이 등수
        // 점수 <= 1000
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int[] prefixSum = new int[1002];
            for (int j = 0; j < N; j++) {
                prefixSum[scores[i][j]]++;
            }

            // 큰 숫자부터 누적합
            for (int s = 1000; s >= 0; s--) {
                prefixSum[s] += prefixSum[s + 1];
            }

            // 점수 + 1 까지의 인원 수로 등수 판별
            for (int j = 0; j < N; j++) {
                sb.append(prefixSum[scores[i][j] + 1] + 1).append(" ");
            }
            sb.append("\n");
        }

        // 최종 순위
        // 최종 등수는 세 대회의 점수의 합을 기준
        int[] finalScore = new int[N];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < N; j++) {
                finalScore[j] += scores[i][j];
            }
        }
        int[] prefixSum = new int[3002];
        for (int i = 0; i < N; i++) {
            prefixSum[finalScore[i]]++;
        }

        // 큰 숫자부터 누적합
        for (int s = 3000; s >= 0; s--) {
            prefixSum[s] += prefixSum[s + 1];
        }
        // 점수 + 1 까지의 인원 수로 등수 판별
        for (int i = 0; i < N; i++) {
            sb.append(prefixSum[finalScore[i] + 1] + 1).append(" ");
        }

        System.out.println(sb.toString());
    }
}
