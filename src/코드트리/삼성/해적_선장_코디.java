package 코드트리.삼성;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 해적_선장_코디 {
    static Map<Integer, Ship> idShipMap;
    static PriorityQueue<Ship> ready, reload;
    static Set<Integer> reloadSet;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        idShipMap = new HashMap<>();
        ready = new PriorityQueue<>((o1, o2) ->
                o1.p == o2.p ? Integer.compare(o1.id, o2.id) : Integer.compare(o2.p, o1.p));
        reload = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.reloadEnd, o2.reloadEnd));
        reloadSet = new HashSet<>();

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            // 공격 준비
            if (100 == op) {
                int N = Integer.parseInt(st.nextToken());
                for (int i = 0; i < N; i++) {
                    int id = Integer.parseInt(st.nextToken());
                    int p = Integer.parseInt(st.nextToken());
                    int r = Integer.parseInt(st.nextToken());
                    addShip(id, p, r);
                }
            }
            // 지원 요청
            else if (200 == op) {
                int id = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                addShip(id, p, r);
            }
            // 함포 교체
            else if (300 == op) {
                int id = Integer.parseInt(st.nextToken());
                int pw = Integer.parseInt(st.nextToken());
                change(id, pw);
            }
            // 공격 명령
            else if (400 == op) {
                sb.append(attack(t)).append('\n');
            }
        }
        System.out.println(sb);
    }

    private static String attack(int time) {
        // reload에서 현재 시간 이전꺼 전부 뽑아서 ready에
        while (!reload.isEmpty() && reload.peek().reloadEnd <= time) {
            Ship s = reload.poll();
            Ship o = idShipMap.get(s.id);

            ready.offer(new Ship(o.id, o.p, o.r));
            reloadSet.remove(o.id);
        }

        // ready에서 최대 5개 뽑기
        // 뽑은게 reload 중인지 확인
        // 뽑은게 p가 현재 상태와 같은지 확인
        // 사격 후 reload에
        int sum = 0;
        int cnt = 0;
        StringBuilder sb = new StringBuilder();

        while (!ready.isEmpty() && cnt < 5) {
            Ship s = ready.poll();
            if (reloadSet.contains(s.id)) {
                continue;
            }
            if (s.p != idShipMap.get(s.id).p) {
                continue;
            }

            reload.offer(new Ship(s.id, s.p, s.r, time + s.r));
            reloadSet.add(s.id);

            sum += s.p;
            cnt++;
            sb.append(s.id).append(' ');
        }

        return sum + " " + cnt + " " + sb.toString();
    }

    private static void change(int id, int pw) {
        idShipMap.get(id).p = pw;
        ready.offer(new Ship(id, pw, idShipMap.get(id).r));
    }

    private static void addShip(int id, int p, int r) {
        idShipMap.put(id, new Ship(id, p, r));
        ready.add(new Ship(id, p, r));
    }

    private static class Ship {
        int id, p, r, reloadEnd;

        public Ship(int id, int p, int r) {
            this.id = id;
            this.p = p;
            this.r = r;
        }

        public Ship(int id, int p, int r, int reloadEnd) {
            this.id = id;
            this.p = p;
            this.r = r;
            this.reloadEnd = reloadEnd;
        }
    }
}
