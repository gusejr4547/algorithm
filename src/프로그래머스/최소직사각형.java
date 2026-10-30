package 프로그래머스;

public class 최소직사각형 {
    public static void main(String[] args) {

    }

    public int solution(int[][] sizes) {
        int answer = 0;

        int maxWidth = 0;
        int maxHeight = 0;

        for (int[] size : sizes) {
            // 가로 길게 세로 짧게
            if (size[0] >= size[1]) {
                maxWidth = Math.max(maxWidth, size[0]);
                maxHeight = Math.max(maxHeight, size[1]);
            } else {
                maxWidth = Math.max(maxWidth, size[1]);
                maxHeight = Math.max(maxHeight, size[0]);
            }
        }

        return maxWidth * maxHeight;
    }
}
