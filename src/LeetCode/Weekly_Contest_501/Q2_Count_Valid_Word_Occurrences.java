package LeetCode.Weekly_Contest_501;

import java.util.*;

public class Q2_Count_Valid_Word_Occurrences {
    // chunk에 String을 이어 붙여서 s를 만들기.
    // -로 이어진거는 한 단어로 본다.
    // - 이외의 공백이나 -- 같은거는 구분자로 본다

    public int[] countWordOccurrences(String[] chunks, String[] queries) {
        int[] answer = new int[queries.length];

        StringBuilder sb = new StringBuilder();
        for(String s : chunks){
            sb.append(s);
        }
        String s = sb.toString();

        // System.out.println(s);

        Map<String, Integer> count = new HashMap<>();
        for(String str : queries){
            count.put(str, 0);
        }

        sb = new StringBuilder();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);

            // 소문자
            if('a' <= c && c <= 'z'){
                sb.append(c);
            }else if('-' == c){
                // 앞 뒤가 모두 소문자인지?
                if(i-1 >= 0 && i+1 < s.length() &&
                        'a'<= s.charAt(i-1) && s.charAt(i-1) <='z' &&
                        'a'<= s.charAt(i+1) && s.charAt(i+1) <='z'){
                    sb.append(c);
                }else{
                    // 만든 문자열 완성
                    if(sb.length() > 0){
                        count.put(sb.toString(), count.getOrDefault(sb.toString(), 0)+1);
                        sb.setLength(0);
                    }
                }
            }else{
                if(sb.length() > 0){
                    count.put(sb.toString(), count.getOrDefault(sb.toString(), 0)+1);
                    sb.setLength(0);
                }
            }
            // System.out.println(count);
        }
        // 만든 문자열 완성
        if(sb.length() > 0){
            count.put(sb.toString(), count.getOrDefault(sb.toString(), 0)+1);
            sb.setLength(0);
        }

        // answer 완성
        for(int i=0; i<queries.length; i++){
            answer[i] = count.get(queries[i]);
        }

        return answer;
    }
}
