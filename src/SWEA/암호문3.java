package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 암호문3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            String[] crypto = br.readLine().split(" ");
            int cmdCnt = Integer.parseInt(br.readLine());
            String cmdAll = br.readLine();

            List<String> cryptoList = new ArrayList<>();
            for (String s : crypto) {
                cryptoList.add(s);
            }

            StringTokenizer st = new StringTokenizer(cmdAll);
            for (int i = 0; i < cmdCnt; i++) {
                char cmd = st.nextToken().charAt(0);
                int x, y;
                switch (cmd) {
                    case 'I':
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        List<String> temp = new ArrayList<>();
                        for (int k = 0; k < y; k++) {
                            cryptoList.add(x + k, st.nextToken());
                        }
                        break;
                    case 'D':
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        for (int k = 0; k < y; k++) {
                            cryptoList.remove(x);
                        }
                        break;
                    case 'A':
                        y = Integer.parseInt(st.nextToken());
                        for (int k = 0; k < y; k++) {
                            cryptoList.add(st.nextToken());
                        }
                        break;
                }
            }

            sb.append("#").append(tc).append(" ");
            for (int i = 0; i < 10; i++) {
                sb.append(cryptoList.get(i)).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
