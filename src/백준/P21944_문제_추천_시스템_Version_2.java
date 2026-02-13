package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P21944_문제_추천_시스템_Version_2 {
    static Map<Integer, Problem> problemMap; // 단순히 문제번호와 매핑
    static Map<Integer, TreeSet<Problem>> GLMap; // 알고리즘 분류에 따른 난이도 TreeSet
    static TreeSet<Problem> LTreeSet; // 난이도 TreeSet

    public static void main(String[] args) throws Exception {
        problemMap = new HashMap<>();
        GLMap = new HashMap<>();
        LTreeSet = new TreeSet<>((o1, o2) -> o1.l == o2.l ? Integer.compare(o1.p, o2.p) : Integer.compare(o1.l, o2.l));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken()); // 문제번호
            int l = Integer.parseInt(st.nextToken()); // 난이도
            int g = Integer.parseInt(st.nextToken()); // 알고리즘 분류
            Problem problem = new Problem(p, l, g);

            problemMap.put(p, problem);
            if (GLMap.containsKey(g)) {
                GLMap.get(g).add(problem);
            } else {
                TreeSet<Problem> treeSet = new TreeSet<>((o1, o2) -> o1.l == o2.l ? Integer.compare(o1.p, o2.p) : Integer.compare(o1.l, o2.l));
                treeSet.add(problem);
                GLMap.put(g, treeSet);
            }
            LTreeSet.add(problem);
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            if ("recommend".equals(op)) {
                int g = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    sb.append(GLMap.get(g).last().p).append('\n');
                } else {
                    sb.append(GLMap.get(g).first().p).append('\n');
                }
            } else if ("recommend2".equals(op)) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    sb.append(LTreeSet.last().p).append('\n');
                } else {
                    sb.append(LTreeSet.first().p).append('\n');
                }
            } else if ("recommend3".equals(op)) {
                int x = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    Problem problem = LTreeSet.ceiling(new Problem(-1, l, -1));
                    sb.append(problem == null ? -1 : problem.p).append('\n');
                } else {
                    Problem problem = LTreeSet.floor(new Problem(-1, l, -1));
                    sb.append(problem == null ? -1 : problem.p).append('\n');
                }
            } else if ("add".equals(op)) {
                int p = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());
                int g = Integer.parseInt(st.nextToken());
                Problem problem = new Problem(p, l, g);

                problemMap.put(p, problem);
                if (GLMap.containsKey(g)) {
                    GLMap.get(g).add(problem);
                } else {
                    TreeSet<Problem> treeSet = new TreeSet<>((o1, o2) -> o1.l == o2.l ? Integer.compare(o1.p, o2.p) : Integer.compare(o1.l, o2.l));
                    treeSet.add(problem);
                    GLMap.put(g, treeSet);
                }
                LTreeSet.add(problem);
            } else if ("solved".equals(op)) {
                int p = Integer.parseInt(st.nextToken());
                Problem problem = problemMap.get(p);
                problemMap.remove(p);
                GLMap.get(problem.g).remove(problem);
                LTreeSet.remove(problem);
            }
        }
        System.out.println(sb);
    }

    private static class Problem {
        int p, l, g;
        boolean solved;

        public Problem(int p, int l, int g) {
            this.p = p;
            this.l = l;
            this.g = g;
            this.solved = false;
        }
    }
}
