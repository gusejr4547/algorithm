package 프로그래머스;

public class 두_원_사이의_정수_쌍 {
    public static void main(String[] args) {
        두_원_사이의_정수_쌍 Main = new 두_원_사이의_정수_쌍();
        System.out.println(Main.solution(2, 3));
    }

    // 두 원 사이의 공간에 x좌표와 y좌표가 모두 정수인 점의 개수
    // 각 원 위의 점도 포함하여 셉니다.
    public long solution(int r1, int r2) {
        long answer = 0;

        for (int x = 1; x <= r2; x++) {
            long maxY = (long) Math.floor(Math.sqrt(Math.pow(r2, 2) - Math.pow(x, 2)));
            if (x < r1) {
                long minY = (long) Math.ceil(Math.sqrt(Math.pow(r1, 2) - Math.pow(x, 2)));
                answer += maxY - minY + 1;
            } else {
                answer += maxY + 1;
            }
        }

        return answer * 4;
    }
}
