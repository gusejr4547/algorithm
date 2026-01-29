package 프로그래머스;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 영어_끝말잇기 {
    public static void main(String[] args) {
        영어_끝말잇기 Main = new 영어_끝말잇기();
        int n = 3;
        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        System.out.println(Arrays.toString(Main.solution(n, words)));
    }

    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];

        Set<String> used = new HashSet<>();
        used.add(words[0]);
        // 몇번째에 끝말잇기가 끝나는가?
        int idx = 1;
        char last = words[0].charAt(words[0].length() - 1);
        while (idx < words.length) {
            // 이전 끝 문자 = 현재 첫번째 문자
            if (last != words[idx].charAt(0)) {
                break;
            }
            // 이전에 사용한적 있는 단어
            if (used.contains(words[idx])) {
                break;
            }

            // 성공
            used.add(words[idx]);
            last = words[idx].charAt(words[idx].length() - 1);
            idx++;
        }

        // 끝까지 성공
        if (idx == words.length) {
            return new int[]{0, 0};
        }
        // 중간에 실패
        else {
            // idx차례에 끝
            int pNum = idx % n + 1;
            int pTurn = idx / n + 1;
            answer[0] = pNum;
            answer[1] = pTurn;
            return answer;
        }
    }
}
