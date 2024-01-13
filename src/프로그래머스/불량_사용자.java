package 프로그래머스;

import java.util.HashSet;

public class 불량_사용자 {
    public static void main(String[] args) {
        불량_사용자 Main = new 불량_사용자();
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "abc1**"};
        System.out.println(Main.solution(user_id, banned_id));
    }

    HashSet<HashSet<String>> result;
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        result = new HashSet<>();
        dfs(new HashSet<>(), 0, user_id, banned_id);
        answer = result.size();
        return answer;
    }

    public void dfs(HashSet<String> set, int idx, String[] user_id, String[] banned_id) {
        if (idx == banned_id.length) {
            result.add(set);
            return;
        }

        for (String userId : user_id) {
            if (set.contains(userId))
                continue;

            if (match(userId, banned_id[idx])) {
                set.add(userId);
                dfs(new HashSet<>(set), idx + 1, user_id, banned_id);
                set.remove(userId);
            }
        }
    }

    public boolean match(String userId, String bannedId) {
        if (userId.length() != bannedId.length()) {
            return false;
        }

        for (int i = 0; i < userId.length(); i++) {
            if (bannedId.charAt(i) != '*' && userId.charAt(i) != bannedId.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
