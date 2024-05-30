package 프로그래머스;

import java.util.*;

public class 기둥과_보_설치 {
    public static void main(String[] args) {
        기둥과_보_설치 Main = new 기둥과_보_설치();
        int n = 5;
        int[][] build_frame = {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}};
        System.out.println(Arrays.deepToString(Main.solution(n, build_frame)));
    }

    Map<Structure, Integer> structureMap;

    public int[][] solution(int n, int[][] build_frame) {
        structureMap = new HashMap<>();

        for (int[] frame : build_frame) {
            int x = frame[0];
            int y = frame[1];
            int type = frame[2];
            int option = frame[3];

            if (option == 1) {
                if (canBuild(x, y, type)) {
                    structureMap.put(new Structure(x, y, type), 1);
                }
            } else {
                Structure structure = new Structure(x, y, type);
                structureMap.remove(structure);
                if (!canRemove(x, y, type)) {
                    structureMap.put(structure, 1);
                }
            }
        }

        // Map에 남아있는거 모아서 정렬
        List<Structure> structureList = new ArrayList<>(structureMap.keySet());
        Collections.sort(structureList);

        int[][] answer = new int[structureList.size()][3];
        int idx = 0;
        for (Structure structure : structureList) {
            answer[idx][0] = structure.x;
            answer[idx][1] = structure.y;
            answer[idx][2] = structure.type;
            idx++;
        }

        return answer;
    }

    private boolean canRemove(int x, int y, int type) {
        // 기둥
        if (type == 0) {
            // 위쪽 기둥
            if (structureMap.containsKey(new Structure(x, y + 1, 0))) {
                if (!canBuild(x, y + 1, 0)) {
                    return false;
                }
            }
            // 왼쪽 보
            if (structureMap.containsKey(new Structure(x - 1, y + 1, 1))) {
                if (!canBuild(x - 1, y + 1, 1)) {
                    return false;
                }
            }
            // 오른쪽 보
            if (structureMap.containsKey(new Structure(x, y + 1, 1))) {
                if (!canBuild(x, y + 1, 1)) {
                    return false;
                }
            }
        }
        // 보
        else {
            // 왼쪽 보
            if (structureMap.containsKey(new Structure(x - 1, y, 1))) {
                if (!canBuild(x - 1, y, 1)) {
                    return false;
                }
            }
            // 오른쪽 보
            if (structureMap.containsKey(new Structure(x + 1, y, 1))) {
                if (!canBuild(x + 1, y, 1)) {
                    return false;
                }
            }
            // 왼쪽 기둥
            if (structureMap.containsKey(new Structure(x, y, 0))) {
                if (!canBuild(x, y, 0)) {
                    return false;
                }
            }
            // 오른쪽 기둥
            if (structureMap.containsKey(new Structure(x + 1, y, 0))) {
                if (!canBuild(x + 1, y, 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean canBuild(int x, int y, int type) {
        // 기둥
        if (type == 0) {
            // 바닥 || 기둥 위 || 보 한쪽 끝
            if (y == 0 || structureMap.containsKey(new Structure(x, y - 1, 0)) ||
                    structureMap.containsKey(new Structure(x - 1, y, 1)) ||
                    structureMap.containsKey(new Structure(x, y, 1))) {
                return true;
            }
        }
        // 보
        else {
            // 한쪽 끝 기둥 || 양쪽 끝 다른 보
            if (structureMap.containsKey(new Structure(x, y - 1, 0)) ||
                    structureMap.containsKey(new Structure(x + 1, y - 1, 0)) ||
                    (structureMap.containsKey(new Structure(x - 1, y, 1)) && structureMap.containsKey(new Structure(x + 1, y, 1)))) {
                return true;
            }
        }
        return false;
    }

    static class Structure implements Comparable<Structure> {
        int x, y, type;

        public Structure(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Structure structure = (Structure) o;
            return x == structure.x && y == structure.y && type == structure.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, type);
        }

        @Override
        public int compareTo(Structure o) {
            if (this.x > o.x) {
                return 1;
            } else if (this.x == o.x) {
                if (this.y > o.y) {
                    return 1;
                } else if (this.y == o.y) {
                    return this.type - o.type;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }
    }
}
