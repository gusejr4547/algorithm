package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P2261_가장_가까운_두_점 {
    static int N;
    static Point[] points;
    static Comparator<Point> Ycomp = new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            return Integer.compare(o1.y, o2.y);
        }
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        points = new Point[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }

        // x축 기준으로 정렬
        Arrays.sort(points, (o1, o2) -> Integer.compare(o1.x, o2.x));

        // 분할
//        int answer = closestPair(0, N - 1);

        // 라인 스위핑
        int answer = lineSweeping();

        System.out.println(answer);
    }

    private static int lineSweeping() {
        // TreeSet → Comparator.compare() == 0 이면 동일 취급
        TreeSet<Point> set = new TreeSet<>((o1, o2) ->
                o1.y == o2.y ? Integer.compare(o1.x, o2.x) : Integer.compare(o1.y, o2.y));
        int minDist = getSquareDistance(points[0], points[1]);

        // 탐색은 points[2]부터, 0,1은 최소거리 후보
        set.add(points[0]);
        set.add(points[1]);

        int leftIdx = 0;
        for (int rightIdx = 2; rightIdx < N; rightIdx++) {
            // 왼쪽 점 부터 rightIdx와 x거리 계산해서 minDist보다 크면 set에서 제외
            while (leftIdx < rightIdx) {

                int xDist = (points[rightIdx].x - points[leftIdx].x) * (points[rightIdx].x - points[leftIdx].x);
                if (xDist > minDist) {
                    set.remove(points[leftIdx]);
                    leftIdx++;
                } else {
                    break;
                }
            }

            // rightIdx의 y거리가 minDist 이하의 점 비교해서 minDist 갱신
            int range = (int) Math.sqrt(minDist) + 1; // 소수점 버려지는거 보정
            TreeSet<Point> subSet = (TreeSet<Point>) set.subSet(
                    new Point(-100000, points[rightIdx].y - range),
                    new Point(100000, points[rightIdx].y + range));

            for (Point p : subSet) {
                minDist = Math.min(minDist, getSquareDistance(points[rightIdx], p));
            }

            set.add(points[rightIdx]);
        }

        return minDist;
    }

    private static int closestPair(int left, int right) {
        // 3개 이하는 나누지 않음.
        if (right - left <= 2) {
            int minDist = Integer.MAX_VALUE;

            for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    minDist = Math.min(minDist, getSquareDistance(points[i], points[j]));
                }
            }

            return minDist;
        }


        int minDist = Integer.MAX_VALUE;
        int mid = (left + right) / 2;
        // 왼쪽 최소길이
        minDist = Math.min(minDist, closestPair(left, mid));
        // 오른쪽 최소길이
        minDist = Math.min(minDist, closestPair(mid + 1, right));

        // 걸쳐있는 길이 계산할 점
        List<Point> strip = new ArrayList<>();

        for (int i = left; i <= right; i++) {
            int sqd = (points[mid].x - points[i].x) * (points[mid].x - points[i].x);
            if (sqd < minDist) {
                strip.add(points[i]);
            }
        }

        // y 기준 정렬
        Collections.sort(strip, Comparator.comparingInt(p -> p.y));

        // y 기준으로 minDist 범위 안
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size(); j++) {
                int sqd = (strip.get(i).y - strip.get(j).y) * (strip.get(i).y - strip.get(j).y);
                if (sqd < minDist) {
                    minDist = Math.min(minDist, getSquareDistance(strip.get(i), strip.get(j)));
                } else {
                    break;
                }
            }
        }

        return minDist;
    }

    private static int getSquareDistance(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }


    private static class Point {
        int y, x;

        public Point(int x, int y) {
            this.y = y;
            this.x = x;
        }
    }
}
