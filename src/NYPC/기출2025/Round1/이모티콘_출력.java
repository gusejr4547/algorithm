package NYPC.기출2025.Round1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 이모티콘_출력 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        Set<String> emoticon = new HashSet<>();
        for (int i = 0; i < N; i++) {
            emoticon.add(br.readLine());
        }
        String input = br.readLine();

        StringBuilder answer = new StringBuilder();

        int idx = 0;
        int len = 0;
        while (idx < input.length()) {
            char c = input.charAt(idx);
            if (c == ':') {
                // 이모티콘 문자 길이는 최대 15
                StringBuilder sb = new StringBuilder();
                int nIdx = idx + 1;
                boolean isEmoticon = false;
                while (nIdx < input.length() && sb.length() <= 15) {
                    if (input.charAt(nIdx) == ':') {
                        isEmoticon = true;
                        break;
                    }
                    sb.append(input.charAt(nIdx));
                    nIdx++;
                }

                String emo = sb.toString();

                if (isEmoticon) {
                    // 이모티콘에 있음 => 1글자 취급
                    if (emoticon.contains(emo)) {
                        answer.append("[").append(emo).append("]");
                        len++;
                        if (len >= L) {
                            answer.append("\n");
                            len = 0;
                        }
                        idx = nIdx + 1;
                    }
                    // 이모티콘 없음 => 일반 문자
                    else {
                        answer.append(input.charAt(idx));
                        len++;
                        if(len >= L){
                            answer.append("\n");
                            len = 0;
                        }

                        idx++;
                    }
                } else {
                    // 이모티콘 아님 => 일반 문자
                    answer.append(input.charAt(idx));
                    len++;
                    if(len >= L){
                        answer.append("\n");
                        len = 0;
                    }

                    idx++;
                }
            }else{
                answer.append(input.charAt(idx));
                len++;
                if (len >= L) {
                    answer.append("\n");
                    len = 0;
                }

                idx++;
            }
        }

        System.out.println(answer);
    }
}
