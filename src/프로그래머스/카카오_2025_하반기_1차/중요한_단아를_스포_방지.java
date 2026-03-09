package 프로그래머스.카카오_2025_하반기_1차;

import java.util.*;

public class 중요한_단아를_스포_방지 {
    public static void main(String[] args) {
        중요한_단아를_스포_방지 Main = new 중요한_단아를_스포_방지();
        String message = "my phone number is 01012345678 and may i have your phone number";
        int[][] spoiler_ranges = {{5, 5}, {25, 28}, {34, 40}, {53, 59}};
        System.out.println(Main.solution(message, spoiler_ranges));
    }

    // 단어는 공백으로 구분
    // 단어 내 일부 문자에만 스포일러 방지 기능이 적용되더라도, 해당 단어 전체를 스포일러 방지 단어로 간주
    // 한 단어가 여러 개의 스포 방지 구간에 걸쳐 있을 수 있으며, 하나의 스포 방지 구간에 여러 단어가 포함될 수 있다

    public int solution(String message, int[][] spoiler_ranges) {
        Set<String> notSpoiler = new HashSet<>();
        Set<String> spoiler = new HashSet<>();

        String[] words = message.split(" ");

        int rangeIdx = 0;
        int sIdx = 0;
        for (String word : words) {
            int start = sIdx;
            int end = start + word.length() - 1;

            // 구간이 왼쪽에 있으면 다음 구간 이동
            while (rangeIdx < spoiler_ranges.length && spoiler_ranges[rangeIdx][1] < start) {
                rangeIdx++;
            }

            // range에 포함되는가?
            boolean isSpoiler = false;

            // range의 start는 word의 start와 같거나 크다.
            // end 이하에 존재하면 겹친다.
            if (rangeIdx < spoiler_ranges.length && spoiler_ranges[rangeIdx][0] <= end) {
                isSpoiler = true;
            }

            if (isSpoiler) {
                spoiler.add(word);
            } else {
                notSpoiler.add(word);
            }

            sIdx = end + 2;
        }

        // spoiler에만 있는 단어
        spoiler.removeAll(notSpoiler);

        int answer = spoiler.size();
        return answer;
    }
}
