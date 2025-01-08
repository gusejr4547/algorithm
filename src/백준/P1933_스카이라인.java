package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class P1933_스카이라인 {
    static int N;
    static Building[] buildings;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // x좌표 작은 순서로, 같으면 높은거 먼저
        PriorityQueue<Building> pq = new PriorityQueue<>((o1, o2) -> o1.x - o2.x == 0 ? o2.h - o1.h : o1.x - o2.x);
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            pq.offer(new Building(l, h));
            pq.offer(new Building(r, -h));
        }

        // 좌표 이동하며 높이 기록
        TreeMap<Integer, Integer> buildingHeightMap = new TreeMap<>((o1, o2) -> o2 - o1);
        buildingHeightMap.put(0, 1); // 높이가 0인 건물을 넣음 > 건물이 없을 때 가장 높은 높이를 0으로 만드는 역할을 함.
        int maxH = 0;
        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            Building building = pq.poll();

            if (building.h > 0) {
                // 빌딩 시작 > 높이 cnt 추가
                buildingHeightMap.put(building.h, buildingHeightMap.getOrDefault(building.h, 0) + 1);
            } else {
                // 빌딩 끝 > 높이 cnt 빼기
                int height = -building.h;
                if (buildingHeightMap.get(height) == 1) {
                    buildingHeightMap.remove(height);
                } else {
                    buildingHeightMap.put(height, buildingHeightMap.get(height) - 1);
                }
            }

            // 높이 변화?
            int maxHeight = buildingHeightMap.firstKey(); // 가장 높이 높은거 뽑기
            if (maxH != maxHeight) {
                sb.append(building.x).append(" ").append(maxHeight).append(" ");
                maxH = maxHeight;
            }
        }

        System.out.println(sb.toString());
    }

    private static class Building {
        int x, h;

        public Building(int x, int h) {
            this.x = x;
            this.h = h;
        }
    }
}
