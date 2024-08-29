package 프로그래머스;

public class 문자열_압축 {
    public static void main(String[] args) {
        문자열_압축 Main = new 문자열_압축();
        String s = "a";
        System.out.println(Main.solution(s));
    }

    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        if (s.length() == 1) {
            return 1;
        }

        for (int size = 1; size <= s.length() / 2; size++) {
            int len = zip(s, size);
            answer = Math.min(answer, len);
        }


        return answer;
    }

    private int zip(String s, int size) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= s.length() - size; i += size) {
            String target = s.substring(i, i + size);
            int cnt = 1;
            for (int j = i + size; j <= s.length() - size; j += size) {
                String next = s.substring(j, j + size);
                if (target.equals(next)) {
                    cnt++;
                    i = j;
                } else {
                    i = j - size;
                    break;
                }
            }
            if (cnt >= 2) {
                sb.append(cnt).append(target);
            } else {
                sb.append(target);
            }
        }

        if (s.length() % size != 0) {
            //남는 거 있음
            sb.append(s.substring((s.length() / size) * size));
        }

//        System.out.println(sb.toString());
        return sb.length();
    }
}
