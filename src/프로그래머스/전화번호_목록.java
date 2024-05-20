package 프로그래머스;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 전화번호_목록 {
    public static void main(String[] args) {

    }

    public boolean solution(String[] phone_book) {
        Map<String, Integer> hash = new HashMap<>();

        for (String phoneNum : phone_book) {
            hash.put(phoneNum, 0);
        }

        for (String phoneNum : phone_book) {
            for (int i = 0; i < phoneNum.length(); i++) {
                if (hash.containsKey(phoneNum.substring(0, i))) {
                    return false;
                }
            }
        }

        return true;
    }
}
