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
        int N = musicinfos.length;
        // musicinfos를 음의 문자열로 변경
        Music[] music = new Music[N];
        for (int i = 0; i < N; i++) {
            String musicInfo = musicinfos[i];
            StringTokenizer st = new StringTokenizer(musicInfo, ",");
            int start = time2Int(st.nextToken());
            int end = time2Int(st.nextToken());
            String title = st.nextToken();
            String info = st.nextToken();
            List<String> melody = convert(info, end - start);
            music[i] = new Music(title, melody);
        }

//        for (String[] s : music) {
//            System.out.println(s[0] + ", " + s[1]);
//        }

        // m 찾기
        String title = "";
        int len = 0;
        for (int i = 0; i < N; i++) {
            if (isContain(music[i].melody, m)) {
                if (len < music[i].melody.size()) {
                    title = music[i].title;
                    len = music[i].melody.size();
                }
            }
        }
        return title.isBlank() ? "(None)" : title;
    }

    private boolean isContain(List<String> melody, String m) {
        List<String> target = split(m);
        // melody에 target이 있는지 탐색
        for (int i = 0; i <= melody.size() - target.size(); i++) {
            int cnt = 0;
            for (int j = 0; j < target.size(); j++) {
                if (!melody.get(i + j).equals(target.get(j))) {
                    break;
                }
                cnt++;
            }

            if (cnt == target.size()) {
                return true;
            }
        }

        return false;
    }

    private List<String> convert(String info, int time) {
        // info를 음 단위로 나누기
        List<String> m = split(info);

        List<String> res = new ArrayList<>();
        for (int i = 0; i < time; i++) {
            res.add(m.get(i % m.size()));
        }
        return res;
    }

    private List<String> split(String info) {
        List<String> m = new ArrayList<>();
        for (int i = 0; i < info.length(); i++) {
            // 다음이 #이면 붙여서
            if (i + 1 < info.length() && info.charAt(i + 1) == '#') {
                m.add(info.substring(i, i + 2));
                i++;
            } else {
                m.add(String.valueOf(info.charAt(i)));
            }
        }
        return m;
    }

    private int time2Int(String hhmm) {
        String[] t = hhmm.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }

    private class Music {
        String title;
        List<String> melody;

        public Music(String title, List<String> melody) {
            this.title = title;
            this.melody = melody;
        }
    }
}
