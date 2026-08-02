package 프로그래머스;

public class 스킬트리 {
    public static void main(String[] args) {

    }

    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        // 선행 스킬 순서는 지켜야한다
        // skill <= 26
        // skill_trees <= 20

        boolean[] needOrder = new boolean[26];
        for (char c : skill.toCharArray()) {
            needOrder[c - 'A'] = true;
        }

        for (String tree : skill_trees) {
            if (isAvailable(tree, skill, needOrder)) {
                answer++;
            }
        }

        return answer;
    }

    private boolean isAvailable(String tree, String skill, boolean[] needOrder) {
        int order = 0;

        for (char c : tree.toCharArray()) {
            if (needOrder[c - 'A']) {
                if (skill.charAt(order) == c) {
                    order++;
                } else {
                    return false;
                }
            }
        }

        return true;
    }
}
