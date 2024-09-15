package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class P7701_염라대왕의_이름_정렬 {
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append("\n");
            int N = Integer.parseInt(br.readLine());

            TreeMap<String, Integer> nameList = new TreeMap<>((o1, o2) -> o1.length() == o2.length() ? o1.compareTo(o2) : Integer.compare(o1.length(), o2.length()));
            for (int i = 0; i < N; i++) {
                String name = br.readLine();
                nameList.put(name, 0);
            }

            for(String name : nameList.keySet()){
                sb.append(name).append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}
