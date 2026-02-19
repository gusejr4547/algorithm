package 프로그래머스;

import java.util.*;

public class 오픈채팅방 {
    public static void main(String[] args) throws Exception {
        오픈채팅방 Main = new 오픈채팅방();
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        System.out.println(Arrays.toString(Main.solution(record)));
    }

    public String[] solution(String[] record) {
        Map<String, String> uidNicknameMap = new HashMap<>(); // 최종 uid - 닉네임 저장됨.

        for (String s : record) {
            StringTokenizer st = new StringTokenizer(s);
            String op = st.nextToken();

            if ("Enter".equals(op)) {
                uidNicknameMap.put(st.nextToken(), st.nextToken());
            } else if ("Change".equals(op)) {
                uidNicknameMap.put(st.nextToken(), st.nextToken());
            }
        }

        List<String> answer = new ArrayList<>();
        for (String s : record) {
            StringTokenizer st = new StringTokenizer(s);
            String op = st.nextToken();

            if ("Enter".equals(op)) {
                String uid = st.nextToken();
                answer.add(uidNicknameMap.get(uid) + "님이 들어왔습니다.");
            } else if ("Leave".equals(op)) {
                String uid = st.nextToken();
                answer.add(uidNicknameMap.get(uid) + "님이 나갔습니다.");
            }
        }

        return answer.toArray(new String[0]);
    }
}
