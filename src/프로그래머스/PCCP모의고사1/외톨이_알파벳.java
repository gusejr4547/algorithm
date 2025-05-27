package 프로그래머스.PCCP모의고사1;

public class 외톨이_알파벳 {
    public static void main(String[] args) {
        외톨이_알파벳 Main = new 외톨이_알파벳();
        String input_string = "edeaaabbccd";
        System.out.println(Main.solution(input_string));
    }

    public String solution(String input_string) {
        int[] count = new int[26];

        for (int i = 0; i < input_string.length(); i++) {
            char now = input_string.charAt(i);
            if (i == 0) {
                count[now - 'a']++;
            } else {
                // 이전꺼랑 비교
                char prev = input_string.charAt(i - 1);
                if (now != prev) {
                    count[now - 'a']++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count.length; i++) {
            if (count[i] >= 2) {
                char lonely = (char) ('a' + i);
                sb.append(lonely);
            }
        }

        if (sb.length() == 0) {
            return "N";
        } else {
            return sb.toString();
        }
    }
}
