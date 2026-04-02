package 프로그래머스;

import java.util.ArrayList;
import java.util.List;

public class 모음_사전 {
    public static void main(String[] args) {

    }
    List<String> dict = new ArrayList<>();
    String[] c = {"A", "E", "I", "O", "U"};

    public int solution(String word) {
        dfs("");
        int answer = dict.indexOf(word);
        return answer;
    }

    private void dfs(String word){
        dict.add(word);

        if(word.length() == 5){
            return;
        }

        for(int i=0; i<5; i++){
            dfs(word + c[i]);
        }
    }
}
