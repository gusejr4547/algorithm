package 프로그래머스;

public class JadenCase_문자열_만들기 {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;

        for(char c : s.toCharArray()){
            if(c == ' '){
                sb.append(c);
                isFirst = true;
            }else{
                if(isFirst){
                    sb.append(Character.toUpperCase(c));
                }else{
                    sb.append(Character.toLowerCase(c));
                }
                isFirst = false;
            }
        }

        return sb.toString();
    }
}
