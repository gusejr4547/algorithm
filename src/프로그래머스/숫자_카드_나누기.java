package 프로그래머스;

public class 숫자_카드_나누기 {
    public static void main(String[] args) {
        숫자_카드_나누기 Main = new 숫자_카드_나누기();
        int[] arrayA = {14, 35, 119};
        int[] arrayB = {18, 30, 102};
        System.out.println(Main.solution(arrayA, arrayB));
    }

    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;

        // arrayA의 최대공약수
        int gcdA = arrayA[0];
        for (int i = 1; i < arrayA.length; i++) {
            gcdA = GCD(gcdA, arrayA[i]);
        }

        // arrayB의 최대공약수
        int gcdB = arrayB[0];
        for (int i = 1; i < arrayB.length; i++) {
            gcdB = GCD(gcdB, arrayB[i]);
        }

//        System.out.println(gcdA);
//        System.out.println(gcdB);

        // gcdA는 A배열을 전부 나누는 것 중 가장 큰 수
        // B배열을 순회하며 하나라도 나누어떨어지는지 확인
        boolean divideB = false;
        for (int i = 0; i < arrayB.length; i++) {
            if (arrayB[i] % gcdA == 0) {
                divideB = true;
                break;
            }
        }
        if (!divideB) {
            answer = Math.max(answer, gcdA);
        }

        boolean divideA = false;
        for (int i = 0; i < arrayA.length; i++) {
            if (arrayA[i] % gcdB == 0) {
                divideA = true;
                break;
            }
        }
        if (!divideA) {
            answer = Math.max(answer, gcdB);
        }

        return answer;
    }

    private int GCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return GCD(b, a % b);
    }
}
