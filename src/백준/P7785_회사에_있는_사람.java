package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class P7785_회사에_있는_사람 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        TreeSet<String> work = new TreeSet<>(Comparator.reverseOrder());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String op = st.nextToken();

            if ("enter".equals(op)) {
                work.add(name);
            } else {
                work.remove(name);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String name : work) {
            sb.append(name).append('\n');
        }
        System.out.println(sb);
    }
}
