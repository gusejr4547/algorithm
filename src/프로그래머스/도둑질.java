package 프로그래머스;

public class 도둑질 {
    public static void main(String[] args) {
        도둑질 Main = new 도둑질();
        int[] money = {1, 2, 3, 1};
        System.out.println(Main.solution(money));
    }

    public int solution(int[] money) {
        // max[i] = max[i-2] + value 또는 max[i-1]

        // 처음집부터 털자, 두번째집부터 털자
        int n = money.length;
        int[] dpFirst = new int[n];
        int[] dpSecond = new int[n];

        dpFirst[0] = money[0];
        dpFirst[1] = Math.max(money[0], money[1]);

        dpSecond[0] = 0;
        dpSecond[1] = money[1];

        for (int i = 2; i < n; i++) {
            if (i != n - 1) {
                dpFirst[i] = Math.max(dpFirst[i - 2] + money[i], dpFirst[i - 1]);
            }
            dpSecond[i] = Math.max(dpSecond[i - 2] + money[i], dpSecond[i - 1]);
        }

        return Math.max(dpFirst[n - 2], dpSecond[n-1]);
    }
}
