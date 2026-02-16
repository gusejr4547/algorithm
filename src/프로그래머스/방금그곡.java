package 프로그래머스;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 방금그곡 {
    public static void main(String[] args) {
        방금그곡 Main = new 방금그곡();
        String m = "ABCDEFG";
        String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        System.out.println(Main.solution(m, musicinfos));
    }

    public String solution(String m, String[] musicinfos) {
        String target = replaceSharp(m);

        String answer = "(None)";
        int maxTime = -1;

        for (String info : musicinfos) {
            StringTokenizer st = new StringTokenizer(info, ",");
            int start = time2Int(st.nextToken());
            int end = time2Int(st.nextToken());
            String title = st.nextToken();
            String melody = st.nextToken();

            int t = end - start;
            melody = replaceSharp(melody);

            StringBuilder realMelody = new StringBuilder();
            for (int i = 0; i < t; i++) {
                realMelody.append(melody.charAt(i % melody.length()));
            }

            if (realMelody.toString().contains(target)) {
                if (maxTime < t) {
                    answer = title;
                    maxTime = t;
                }
            }
        }

        return answer;
    }

    private String replaceSharp(String s) {
        return s.replace("A#", "a")
                .replace("B#", "b")
                .replace("C#", "c")
                .replace("D#", "d")
                .replace("E#", "e")
                .replace("F#", "f")
                .replace("G#", "g");
    }

    private int time2Int(String hhmm) {
        String[] t = hhmm.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
}
