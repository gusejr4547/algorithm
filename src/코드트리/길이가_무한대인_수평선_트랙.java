package 코드트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class 길이가_무한대인_수평선_트랙 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long T = Integer.parseInt(st.nextToken());
//        TreeSet<Person> people = new TreeSet<>((o1, o2) -> o1.startPoint - o2.startPoint);
        Person[] people = new Person[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
//            people.add(new Person(s, f));
            people[i] = new Person(s, f);
        }

//        TreeSet<Event> events = new TreeSet<>();
        // 시간 순으로 누가 먼저 만나게 되는지 알아야함
        Arrays.sort(people, (o1, o2) -> o2.startPoint - o1.startPoint);

        int count = 0;
        long lastGroupDest = -1;
        for (Person p : people) {
            // T초 이후 어디?
            long dest = p.startPoint + p.speed * T;

            // 처음 사람
            if (count == 0) {
                count++;
                lastGroupDest = dest;
            }
            // 이외
            else {
                // 앞에 그룹보다 앞이 도착지점이면 따라잡은것
                // 이전이면 새로운 그룹
                if (dest < lastGroupDest) {
                    count++;
                    lastGroupDest = dest;
                }
            }
        }

        System.out.println(count);
    }
    static class Person {
        int startPoint, speed;

        public Person(int startPoint, int speed) {
            this.startPoint = startPoint;
            this.speed = speed;
        }
    }
}
