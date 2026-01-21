package 코드트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class 처음_등장하는_위치 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (!treeMap.containsKey(num)) {
                treeMap.put(num, i + 1);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> e : treeMap.entrySet()) {
            sb.append(e.getKey()).append(" ").append(e.getValue()).append('\n');
        }

        System.out.println(sb);
    }
}
