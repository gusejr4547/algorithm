package 프로그래머스;

public class 유사_칸토어_비트열 {
    public static void main(String[] args) {
        유사_칸토어_비트열 Main = new 유사_칸토어_비트열();
        System.out.println(Main.solution(2, 4, 17));
    }

    int answer;

    public int solution(int n, long l, long r) {
        answer = 0;

        dfs(n, 0, l, r, 1);

        return answer;
    }

    private void dfs(int n, int depth, long l, long r, long block) {
        int weight = n - depth;

        long start = (block - 1) * (long) Math.pow(5, weight) + 1; // 이전 블록 끝 + 1
        long end = (long) Math.pow(5, weight) * block;

        // 범위안에 완전히 들어와있으면 탐색 더 안해도 됨
        if(l <= start && end <= r){
            answer += (int) Math.pow(4, weight);
            return;
        }
        // 범위 완전히 벗어나면 탐색 종료
        if(end < l || start > r){
            return;
        }

        // 걸치는 경우 다음 단계 탐색
        // 다음 단계 1 => 11011 중간 0은 넘어가도 0이므로 생략
        long next = block * 5;
        dfs(n, depth + 1, l, r, next - 4);
        dfs(n, depth + 1, l, r, next - 3);
        dfs(n, depth + 1, l, r, next - 1);
        dfs(n, depth + 1, l, r, next);
    }
}
