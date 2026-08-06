package 프로그래머스;

public class 타겟_넘버 {
    public static void main(String[] args) {

    }

    int answer;

    public int solution(int[] numbers, int target) {
        answer = 0;

        // numbers.length <= 20
        // numbers[] <= 50
        // target <= 1000

        // 전부 해보면 2^20

        dfs(0, 0, numbers, target);

        return answer;
    }

    private void dfs(int idx, int cur, int[] numbers, int target) {
        if (idx == numbers.length) {
            if (cur == target) {
                answer++;
            }
            return;
        }

        // +
        dfs(idx + 1, cur + numbers[idx], numbers, target);

        // -
        dfs(idx + 1, cur - numbers[idx], numbers, target);
    }
}
