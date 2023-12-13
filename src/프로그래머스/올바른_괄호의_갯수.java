package 프로그래머스;

public class 올바른_괄호의_갯수 {
    public static void main(String[] args) {
        올바른_괄호의_갯수 Main = new 올바른_괄호의_갯수();
        System.out.println(Main.solution(3));
    }

    public int solution(int n) {

        return dfs(0, 0, n);
    }

    public int dfs(int open, int close, int n) {
        if (close == n) {
            return 1;
        }

        int answer = 0;
        if (open == close) {
            answer += dfs(open + 1, close, n);
        } else if (open > close) {
            if (open < n)
                answer += dfs(open + 1, close, n);
            answer += dfs(open, close + 1, n);
        }
        return answer;
    }
}
