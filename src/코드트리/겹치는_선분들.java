package 코드트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 겹치는_선분들 {
    public static void main(String[] args) throws Exception{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int idx = 0;

        TreeMap<Integer, Integer> point = new TreeMap<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            char d = st.nextToken().charAt(0);

            int l, r;

            if('R' == d){
                l = idx;
                r = idx + M;
                idx = r;
            }else{
                l = idx - M;
                r = idx;
                idx = l;
            }

            point.put(l, point.getOrDefault(l, 0) + 1);
            point.put(r, point.getOrDefault(r, 0) - 1);
        }

        // System.out.println(point);
        int answer = 0;
        int prev = 0;
        int count = 0;

        for(Map.Entry<Integer, Integer> entry : point.entrySet()){
            int p = entry.getKey();
            int value = entry.getValue();

            // 현재 겹쳐진거?
            if(count >= K){
                // 정답에 넣기
                answer += p - prev;
            }

            // 갱신
            prev = p;
            count += value;
        }

        System.out.println(answer);
    }
}
