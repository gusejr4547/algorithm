package 코드트리.삼성;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.InputStreamReader;
import java.util.*;

public class 가로등_설치 {
    // 1 ~ N 직선 좌표
    // 가로등은 r을 사용해서 [x-r, x+r] 구간 거리 밝힘

    // N <= 1_000_000_000

    static int N;
    static List<Integer> posId;
    static TreeMap<Integer, Integer> lightMap;
    static PriorityQueue<Distance> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        posId = new ArrayList<>();
        posId.add(0); // 더미 데이터
        lightMap = new TreeMap<>();
        // 거리 내림차순
        pq = new PriorityQueue<>((o1, o2) ->
                o1.dist != o2.dist ? Integer.compare(o2.dist, o1.dist) : Integer.compare(o1.leftX, o2.leftX));
        StringBuilder sb = new StringBuilder();

        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            if (100 == op) {
                // 마을 상태 확인
                N = Integer.parseInt(st.nextToken());
                int M = Integer.parseInt(st.nextToken());

                int prevX = -1;
                for (int i = 1; i <= M; i++) {
                    int x = Integer.parseInt(st.nextToken());
                    posId.add(x);
                    // lightMap 가로등 위치 순서대로
                    lightMap.put(x, i);
                    // 가로등 사이 거리 저장
                    if (prevX != -1) {
                        pq.offer(new Distance(prevX, x, x - prevX));
                    }
                    prevX = x;
                }

            } else if (200 == op) {
                // 가로등 추가
                addLight();

            } else if (300 == op) {
                // 가로등 제거
                int id = Integer.parseInt(st.nextToken());
                removeLight(id);

            } else if (400 == op) {
                // 최소 전력 계산
                // 가로등 사이 가장 먼 길이/2
                sb.append(getMinPower()).append('\n');
            }
        }
        System.out.println(sb);
    }

    private static int getMinPower() {
        int res = 0;
        // 가장 먼 거리의 절반만큼 power가 필요하다.
        while (!pq.isEmpty()) {
            Distance d = pq.peek();
            // leftX, rightX lightMap에 존재해야 유효
            if (lightMap.containsKey(d.leftX) && lightMap.containsKey(d.rightX)) {
                // leftX 다음 rightX 가 있어야함.
                Integer next = lightMap.higherKey(d.leftX);
                if (next != null && next == d.rightX) {
                    res = d.dist;
                    break;
                }
            }
            pq.poll();
        }
        // 1 ~ 가장 앞의 light.x
        res = Math.max(res, (lightMap.firstKey() - 1) * 2);

        // 가장 뒤의 light.x ~ N
        res = Math.max(res, (N - lightMap.lastKey()) * 2);

        return res;
    }

    private static void removeLight(int id) {
        int x = posId.get(id); // id에 해당되는 x
        // id의 left, right를 찾아서 pq에 Distance 추가
        Integer rightX = lightMap.higherKey(x);
        Integer leftX = lightMap.lowerKey(x);
        // left, right 둘중 하나라도 없으면 Distance 만들기x
        if (rightX != null && leftX != null) {
            pq.offer(new Distance(leftX, rightX, rightX - leftX));
        }

        // id번 가로등을 제거한다.
        posId.set(id, -1); // -1이 제거를 의미
        lightMap.remove(x);
    }

    private static void addLight() {
        // 인접한 가로등 사이의 거리가 가장 먼 곳의 중간에 가로등 설치.
        // x좌표 작은거 먼저
        // i, j 좌표의 경우. 2로 나누어떨어지면 (i+j)/2, 아니면 ceil((i+j)/2)

        // pq에서 가장 앞에 있는거
        while (!pq.isEmpty()) {
            Distance d = pq.peek();
            // leftX, rightX lightMap에 존재해야 유효
            if (lightMap.containsKey(d.leftX) && lightMap.containsKey(d.rightX)) {
                // leftX 다음 rightX 가 있어야함.
                Integer next = lightMap.higherKey(d.leftX);
                if (next != null && next == d.rightX) {
                    break;
                }
            }
            pq.poll();
        }
        if (pq.isEmpty()) {
            return;
        }

        // pq 뽑기
        Distance d = pq.poll();
        // 중간에 가로등 설치
        int x = (int) Math.ceil((d.leftX + d.rightX) / 2.0);
        int id = posId.size();
        posId.add(x);
        lightMap.put(x, id);
        // 가로등 거리 갱신
        // left - x
        pq.offer(new Distance(d.leftX, x, x - d.leftX));
        // x - right
        pq.offer(new Distance(x, d.rightX, d.rightX - x));
    }

    private static class Distance {
        int leftX, rightX, dist;

        public Distance(int leftX, int rightX, int dist) {
            this.leftX = leftX;
            this.rightX = rightX;
            this.dist = dist;
        }
    }

    private static class Light {
        int id, x;
        boolean isRemoved;

        public Light(int id, int x) {
            this.id = id;
            this.x = x;
            this.isRemoved = false;
        }
    }
}
