package 프로그래머스;

public class 조이스틱 {
    public static void main(String[] args) {

    }

    public int solution(String name) {
        int answer = 0;

        int n = name.length();
        // 오른쪽으로만 이동하는 경우
        int minMove = n - 1;

        for (int i = 0; i < n; i++) {
            // 글자 몇번 움직여야하는지?
            int up = name.charAt(i) - 'A';
            int down = 'Z' - name.charAt(i) + 1;
            answer += Math.min(up, down);

            // 앞 뒤 최적 계산
            // 다응 A아닌 위치
            int next = i + 1;
            while (next < n && name.charAt(next) == 'A') {
                next++;
            }

            // 오른쪽으로 갔다가 되돌아가 왼쪽 탐색
            minMove = Math.min(
                    minMove,
                    i * 2 + n - next
            );

            // 왼쪽으로 갔다가 되돌아가 오른쪽 탐색
            minMove = Math.min(
                    minMove,
                    i + (n - next) * 2
            );
        }

        return answer + minMove;
    }
}
