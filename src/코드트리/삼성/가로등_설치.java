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
    static List<Light> lightList;
    static TreeMap<Integer, Integer> lightMap;
    static PriorityQueue<Distance> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        lightList = new ArrayList<>();
        lightList.add(new Light(0, 0)); // 더미 데이터
        lightMap = new TreeMap<>();
        // 거리 내림차순
        pq = new PriorityQueue<>((o1, o2) ->
                o1.dist == o2.dist ? Integer.compare(lightList.get(o1.leftId).x, lightList.get(o2.leftId).x) :
                        Integer.compare(o2.dist, o1.dist));
        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            if (100 == op) {
                // 마을 상태 확인
                N = Integer.parseInt(st.nextToken());
                int M = Integer.parseInt(st.nextToken());
                lightList.add(new Light(1, Integer.parseInt(st.nextToken())));
                lightMap.put(lightList.get(1).x, 1);
                for (int i = 2; i <= M; i++) {
                    lightList.add(new Light(i, Integer.parseInt(st.nextToken())));
                    // 가로등 사이 거리 저장
                    pq.offer(new Distance(i - 1, i, lightList.get(i).x - lightList.get(i - 1).x));
                    // lightMap 가로등 위치 순서대로
                    lightMap.put(lightList.get(i).x, i);
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
            if (!lightList.get(d.leftId).isRemoved && !lightList.get(d.rightId).isRemoved) {
                res = d.dist;
                break;
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
        Light l = lightList.get(id);
        // id의 left, right를 찾아서 pq에 Distance 추가
        Integer rightX = lightMap.higherKey(l.x);
        Integer leftX = lightMap.lowerKey(l.x);
        // left, right 둘중 하나라도 없으면 Distance 만들기x
        if (rightX != null && leftX != null) {
            pq.offer(new Distance(lightMap.get(leftX), lightMap.get(rightX), rightX - leftX));
        }

        // id번 가로등을 제거한다.
        lightList.get(id).isRemoved = true;
        lightMap.remove(lightList.get(id).x);
    }

    private static void addLight() {
        // 인접한 가로등 사이의 거리가 가장 먼 곳의 중간에 가로등 설치.
        // x좌표 작은거 먼저
        // i, j 좌표의 경우. 2로 나누어떨어지면 (i+j)/2, 아니면 ceil((i+j)/2)

        // pq에서 가장 앞에 있는거
        // Distance에서 left, right 가로등이 전부 유효해야한다.
        while (!pq.isEmpty()) {
            Distance d = pq.peek();
            if (!lightList.get(d.leftId).isRemoved && !lightList.get(d.rightId).isRemoved) {
                break;
            }
            pq.poll();
        }
        if (pq.isEmpty()) {
            return;
        }

        // pq 뽑기
        Distance d = pq.poll();
        // 중간에 가로등 설치
        int x = (int) Math.ceil((lightList.get(d.rightId).x + lightList.get(d.leftId).x) / 2.0);
        int id = lightList.size();
        lightList.add(new Light(id, x));
        lightMap.put(x, id);
        // 가로등 거리 갱신
        // left - x
        pq.offer(new Distance(d.leftId, id, x - lightList.get(d.leftId).x));
        // x - right
        pq.offer(new Distance(id, d.rightId, lightList.get(d.rightId).x - x));
    }

    private static class Distance {
        int leftId, rightId, dist;

        public Distance(int leftId, int rightId, int dist) {
            this.leftId = leftId;
            this.rightId = rightId;
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
