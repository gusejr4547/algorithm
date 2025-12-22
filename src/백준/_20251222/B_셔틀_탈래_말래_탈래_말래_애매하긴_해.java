package 백준._20251222;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_셔틀_탈래_말래_탈래_말래_애매하긴_해 {
    // 화연이에게는 두 가지 선택지가 있다.
    // 셔틀을 기다렸다가 타고 공학관까지 간다.
    // 셔틀을 기다리지 않고 곧바로 공학관까지 걸어간다.
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nextBusTime = Integer.parseInt(st.nextToken());
        int busTime = Integer.parseInt(st.nextToken());
        int walkTime = Integer.parseInt(st.nextToken());
        int nextClassTime = Integer.parseInt(st.nextToken());

        if (nextBusTime + busTime <= nextClassTime && walkTime <= nextClassTime) {
            System.out.println("~.~");
        } else if (nextBusTime + busTime > nextClassTime && walkTime > nextClassTime) {
            System.out.println("T.T");
        } else if (nextBusTime + busTime <= nextClassTime) {
            System.out.println("Shuttle");
        } else {
            System.out.println("Walk");
        }
    }
}
