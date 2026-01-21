package 코드트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class 단어장 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        while (N-- > 0) {
            String str = br.readLine();
            treeMap.put(str, treeMap.getOrDefault(str, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> e : treeMap.entrySet()) {
            sb.append(e.getKey()).append(' ').append(e.getValue()).append('\n');
        }
        System.out.println(sb);
    }
}
