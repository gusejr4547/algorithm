package 백준._20251220;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class A_신규_학과 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        TreeMap<Integer, String> treeMap = new TreeMap<>(Comparator.reverseOrder());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String major = st.nextToken();
            int y = Integer.parseInt(st.nextToken());
            treeMap.put(y, major);
        }

        System.out.println(treeMap.firstEntry().getValue());
    }
}
