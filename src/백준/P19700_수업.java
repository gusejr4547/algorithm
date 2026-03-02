package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P19700_수업 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Person[] people = new Person[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            people[i] = new Person(h, k);
        }
        Arrays.sort(people, (o1, o2) -> o2.h - o1.h);

        // TreeMap: K = 팀의 크기, V = 해당 크기의 팀 개수
        TreeMap<Integer, Integer> teamSizes = new TreeMap<>();

        int answer = 0;
        // 큰사람 부터 분류한다.
        for (int i = 0; i < N; i++) {
            Person p = people[i];

            // p.k 인원보다 작은 최대값
            Integer targetSize = teamSizes.lowerKey(p.k);

            // 팀이 없으면 새로 팀 생성
            if (targetSize == null) {
                teamSizes.put(1, teamSizes.getOrDefault(1, 0) + 1);
                answer++;
            }
            // 찾으면 추가
            else {
                // targetSize 의 개수를 1개 줄인다
                int count = teamSizes.get(targetSize);
                if (count == 1) {
                    teamSizes.remove(targetSize);
                } else {
                    teamSizes.put(targetSize, count - 1);
                }

                // targetSize+1의 개수를 늘린다.
                teamSizes.put(targetSize + 1, teamSizes.getOrDefault(targetSize + 1, 0) + 1);
            }
        }

        System.out.println(answer);
    }

    private static class Person {
        int h, k;

        public Person(int h, int k) {
            this.h = h;
            this.k = k;
        }
    }
}
