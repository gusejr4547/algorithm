package 프로그래머스;

public class 기지국_설치 {
    public static void main(String[] args) {
        기지국_설치 Main = new 기지국_설치();
        int n = 16;
        int[] stations = {9};
        int w = 2;
        System.out.println(Main.solution(n, stations, w));
    }

    // w 전파거리
    // 증설해야 할 기지국 개수의 최솟값을 리턴하는
    public int solution(int n, int[] stations, int w) {
        int totalAddition = 0;

        // 설치한 위치 + w, -w 만큼 범위

        int totalRight = 1;

        for (int station : stations) {
            int left = station - w;
            int right = station + w;
            // 뒤
            while (left > totalRight) {
                left = left - (w * 2 + 1);
                totalAddition++;
            }
            // 앞
            totalRight = right + 1;
        }

        totalRight--;
        while (totalRight < n) {
            totalRight = totalRight + (w * 2 + 1);
            totalAddition++;
        }

        return totalAddition;
    }
}
