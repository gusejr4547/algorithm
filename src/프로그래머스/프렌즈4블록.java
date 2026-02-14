package 프로그래머스;

public class 프렌즈4블록 {
    public static void main(String[] args) {
        프렌즈4블록 Main = new 프렌즈4블록();
        int m = 4;
        int n = 5;
        String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
        System.out.println(Main.solution(m, n, board));
    }

    // m,n <= 30

    public int solution(int m, int n, String[] board) {
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            grid[i] = board[i].toCharArray();
        }

        int answer = 0;
        while (true) {
            // grid에서 2x2 지워지는 블록 찾기
            boolean[][] delete = new boolean[m][n];
            int res = find(grid, delete);

            // 없으면 break
            if (res == 0) {
                break;
            }

            // answer에 더하기
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (delete[i][j]) {
                        answer++;
                    }
                }
            }

            // 삭제
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (delete[i][j]) {
                        grid[i][j] = '.';
                    }
                }
            }

            // 빈공간 내리기
            drop(grid);
        }

        return answer;
    }

    private void drop(char[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        for (int j = 0; j < M; j++) {
            int i = N - 1;// 저장할 위치
            int k = N - 1;// 탐색 포인터
            while (i >= 0 && k >= 0) {
                // 빈공간 찾으면 위에 블록 탐색
                if (grid[i][j] == '.') {
                    k = i - 1;
                    while (k >= 0) {
                        if (grid[k][j] != '.') {
                            break;
                        }
                        k--;
                    }
                    if (k >= 0) {
                        grid[i][j] = grid[k][j];
                        grid[k][j] = '.';
                    }
                }
                i--;
            }
        }
    }

    private int find(char[][] grid, boolean[][] delete) {
        int result = 0;
        // 2x2 한칸씩 전부 확인
        int N = grid.length;
        int M = grid[0].length;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 1; j++) {
                // 알파벳이 전부 같으면
                if (Character.isAlphabetic(grid[i][j])
                        && grid[i][j] == grid[i][j + 1]
                        && grid[i][j] == grid[i + 1][j]
                        && grid[i][j] == grid[i + 1][j + 1]) {
                    delete[i][j] = true;
                    delete[i][j + 1] = true;
                    delete[i + 1][j] = true;
                    delete[i + 1][j + 1] = true;
                    result++;
                }
            }
        }
        return result;
    }
}
