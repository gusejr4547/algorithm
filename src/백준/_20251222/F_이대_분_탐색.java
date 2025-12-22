package 백준._20251222;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class F_이대_분_탐색 {
    // 수직선 신촌 x = 0, 이대 x = L
    // 관심범위 [s,e] x=s 부터 x=e까지 정수, 초기값 [0, L]
    // 이분탐색을 응용한 이대 분 탐색
    // [s+e/2] 에 이대 분 있으면 탐색 종료..
    // 없으면 [s, m-1] [m+1, e] 중에 이대 분 더 많은 곳으로 관심 범위 이동, 동일하면 [m+1, e]

    // 탐색이 최대한 오래 진행되도록 이대 분 배치 출력
    static List<Node> nodeList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long L = Long.parseLong(st.nextToken());

        // N명을 배치 해야함.
        // 총 길이는 L

        // 이분 탐색은 logN
        // 탐색하는 자리의 개수도 logN + 1임.
        // log8 => 4번

        // 마지막 탐색에는 찾아야 함. 하나는 확정.
        nodeList = new ArrayList<>();
        divide(0, L, N);

        StringBuilder sb = new StringBuilder();
        for (Node node : nodeList) {
            long mid = (node.s + node.e) / 2;
            List<Long> check = new ArrayList<>();
            if (node.endPoint) {
                check.add(mid);
            }
            for (long i = node.s; i <= node.e && check.size() < node.people; i++) {
                if (node.endPoint && i == mid) {
                    continue;
                }
                check.add(i);
            }

            Collections.sort(check);

            for (long v : check) {
                sb.append(v).append(" ");
            }
        }

        System.out.println(sb);
    }

    private static void divide(long s, long e, int people) {
        long mid = (s + e) / 2;
        int half = people / 2;

        // [s, mid-1] , [mid+1, e] 두 구간에 배치할 사람이 전부 들어가야함.
        if (mid - s >= half && e - mid >= people - half) {
            // [s, mid-1] => 관심 구간 아님
            nodeList.add(new Node(s, mid - 1, half));
            // [mid+1, e] => 관심 구간
            divide(mid + 1, e, people - half);
        }
        // 위에꺼 안되면 여기까지 탐색
        else {
            // mid 는 반드시 들어가야함. endPoint로 표시
            nodeList.add(new Node(s, e, people, true));
        }
    }

    private static class Node {
        // [s,e] 구간에 people 만큼의 사람 배치하면됨.
        long s, e;
        int people;
        boolean endPoint;

        public Node(long s, long e, int people) {
            this.s = s;
            this.e = e;
            this.people = people;
        }

        public Node(long s, long e, int people, boolean endPoint) {
            this.s = s;
            this.e = e;
            this.people = people;
            this.endPoint = endPoint;
        }
    }
}
