package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P2170_선_긋기 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Pair> pairList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pairList.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        pairList.sort((e1, e2) -> e1.start == e2.start ? e1.end - e2.end : e1.start - e2.start);

        int left = pairList.get(0).start;
        int right = pairList.get(0).end;
        int len = right - left;

        for (int i = 1; i < N; i++) {
            Pair curr = pairList.get(i);
            if (left <= curr.start && curr.end <= right) {
                continue;
            } else if (curr.start < right) {
                len += curr.end - right;
            } else {
                len += curr.end - curr.start;
            }
            left = curr.start;
            right = curr.end;
        }

        System.out.println(len);
    }

    static class Pair {
        int start;
        int end;

        Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
