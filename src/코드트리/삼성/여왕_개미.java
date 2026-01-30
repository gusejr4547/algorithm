package 코드트리.삼성;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 여왕_개미 {
    static Map<Integer, Integer> houseMap;
    static List<House> houseList;
    static int houseNum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        houseMap = new HashMap<>();
        houseList = new ArrayList<>();
        houseNum = 1;
        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            if (100 == op) {
                int N = Integer.parseInt(st.nextToken());
                // x=0에 여왕개미집, x좌표에 N개의 개미집 건설
                houseList.add(new House(0, 0));
                // 개미집은 1~N으로 표현된다.
                for (int i = 0; i < N; i++) {
                    houseList.add(new House(houseNum, Integer.parseInt(st.nextToken())));
                    houseNum++;
                }
            } else if (200 == op) {
                int p = Integer.parseInt(st.nextToken());
                // 새로운 개미집 하나 건설
                // p위치에 N+k번으로 표현된다.
                houseList.add(new House(houseNum, p));
                houseNum++;

            } else if (300 == op) {
                int q = Integer.parseInt(st.nextToken());
                // 개미집 제거
                // q번 개미집을 제거
                // list에는 오름차순으로 되어있음.
                deleteHouse(q);

            } else if (400 == op) {
                int r = Integer.parseInt(st.nextToken());
                // 정찰
                // r마리의 개미가 서로 다른 개미집을 선택.
                // x값이 증가하는 방향으로 1초에 1만큼 계속 이동.
                // 개미가 지나가는 집은 안전한 개미집이 된다.
                // x=0은 언제나 안전함

                // x값이 증가하는 방향으로 더 갈수있는 개미집이 없거나,
                // 안전한 개미집이면 모든 이동이 종료
                // 일 개미들은 처음 개미집을 선택할 때, 정찰에 걸리는 시간이 최소가 되도록 개미집을 적절히 선택합니다.

                // 시간을 기준으로 이진탐색
                int left = 0;
                int right = 1_000_000_000; // 최대 좌표
                int result = 1_000_000_000;
                while (left <= right) {
                    int mid = (left + right) / 2;

                    if (isValid(r, mid)) {
                        right = mid - 1;
                        result = mid;
                    } else {
                        left = mid + 1;
                    }
                }

                sb.append(result).append('\n');
            }
        }
        System.out.println(sb);
    }

    private static boolean isValid(int num, int maxTime) {
        // 여왕개미의 집만 있는 경우
        if (houseList.size() == 1) {
            return true;
        }
        int count = 1;
        int startIdx = 1;
        for (int i = 1; i < houseList.size(); i++) {
            if (houseList.get(i).x - houseList.get(startIdx).x > maxTime) {
                count++;
                startIdx = i;
            }
        }

        return count <= num;
    }

    private static void deleteHouse(int q) {
        int left = 0;
        int right = houseList.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (houseList.get(mid).houseNum == q) {
                houseList.remove(mid);
                return;
            } else if (houseList.get(mid).houseNum < q) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }

    private static class House {
        int houseNum, x;

        public House(int houseNum, int x) {
            this.houseNum = houseNum;
            this.x = x;
        }
    }

}
