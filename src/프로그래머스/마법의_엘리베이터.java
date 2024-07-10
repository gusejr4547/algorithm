package 프로그래머스;

public class 마법의_엘리베이터 {
    public static void main(String[] args) {
        마법의_엘리베이터 Main = new 마법의_엘리베이터();
        System.out.println(Main.solution(555));
    }

    public int solution(int storey) {
        // 각 자리수 마다 올림 내림 정함
        return dfs(storey, 1);
    }

    private int dfs(int storey, int digit) {
        // 자리수(digit)의 숫자 계산
        int num = (int) (storey % Math.pow(10, digit) / Math.pow(10, digit - 1));

        if (digit == String.valueOf(storey).length()) {
            return Math.min(num, 10 - num + 1);
        }

        int minCount = Integer.MAX_VALUE;

        // 올림
        minCount = dfs((int) (storey + (10 - num) * Math.pow(10, digit - 1)), digit + 1) + 10 - num;
        // 내림
        minCount = Math.min(minCount, dfs((int) (storey - num * Math.pow(10, digit - 1)), digit + 1) + num);

        return minCount;
    }
}
