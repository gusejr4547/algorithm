package 프로그래머스;

public class N_Queen {
    public static void main(String[] args) {

    }

    int answer;
    boolean[] col;
    boolean[] a, b;

    public int solution(int n) {
        answer = 0;
        col = new boolean[n];
        a = new boolean[2 * n - 1];
        b = new boolean[2 * n - 1];

        dfs(0, n);

        return answer;
    }

    private void dfs(int row, int n) {
        if (row == n) {
            answer++;
            return;
        }

        for (int i = 0; i < n; i++) {
            int x = row + i;
            int y = row - i + n - 1;

            if (col[i] || a[x] || b[y]) {
                continue;
            }

            col[i] = true;
            a[x] = true;
            b[y] = true;

            dfs(row + 1, n);

            col[i] = false;
            a[x] = false;
            b[y] = false;
        }
    }
}
