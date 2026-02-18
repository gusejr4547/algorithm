package 프로그래머스;

public class N진수_게임 {
    public static void main(String[] args) {
        N진수_게임 Main = new N진수_게임();
        int n = 16;
        int t = 16;
        int m = 2;
        int p = 1;
        System.out.println(Main.solution(n, t, m, p));
    }

    // n 진법 <= 16
    // t 미리 구할 숫자 개수 <= 1000
    // m 참여 인원 <= 100
    // p 순서
    public String solution(int n, int t, int m, int p) {
        // 내가 반환할 문자를 전부 구할 수 있는 최종 문자열 길이
        int totalLength = m * (t - 1) + p;
//        System.out.println(totalLength);

        // totalLength 될때까지 진법변환해서 저장
        StringBuilder numStr = new StringBuilder();
        int num = 0; // 0부터
        while (numStr.length() < totalLength) {
            // num을 n진수로 표현
            String s = convertNBase(num, n);
            numStr.append(s);
            num++;
        }

        // t개 p번째 마다 꺼내서 저장
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < t; i++) {
            res.append(numStr.charAt(m * i + p - 1));
        }

        return res.toString();
    }

    private String convertNBase(int num, int base) {
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int r = num % base;
            // 10 이상이면 A, B, C, D, E, F
            switch (r) {
                case 10:
                    sb.append('A');
                    break;
                case 11:
                    sb.append('B');
                    break;
                case 12:
                    sb.append('C');
                    break;
                case 13:
                    sb.append('D');
                    break;
                case 14:
                    sb.append('E');
                    break;
                case 15:
                    sb.append('F');
                    break;
                default:
                    sb.append(r);
                    break;
            }
            num /= base;
        }
        return sb.reverse().toString();
    }
}
