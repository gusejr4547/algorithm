package 프로그래머스.코드챌린지;

public class 택배_상자_꺼내기 {
    public static void main(String[] args) {
        택배_상자_꺼내기 Main = new 택배_상자_꺼내기();
        int n = 22;
        int w = 6;
        int num = 8;
        System.out.println(Main.solution(n, w, num));
    }

    public int solution(int n, int w, int num) {
        int height = n % w == 0 ? n / w : n / w + 1;
        int[][] map = new int[height][w];
        int v = 1;

        int targetY = 0, targetX = 0;

        for (int i = 0; i < map.length; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < w; j++) {
                    if (v == num) {
                        targetY = i;
                        targetX = j;
                    }

                    if (v <= n) {
                        map[i][j] = v;
                        v++;
                    }
                }
            } else {
                for (int j = w - 1; j >= 0; j--) {
                    if (v == num) {
                        targetY = i;
                        targetX = j;
                    }

                    if (v <= n) {
                        map[i][j] = v;
                        v++;
                    }
                }
            }
        }

        int answer = 0;
        for (int i = targetY; i < map.length; i++) {
            if (map[i][targetX] != 0) {
                answer++;
            }
        }
        return answer;
    }
}
