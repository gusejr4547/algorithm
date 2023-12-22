package 프로그래머스.PCCP기출문제;

public class 아날로그_시계 {
    public static void main(String[] args) {
        아날로그_시계 Main = new 아날로그_시계();
        System.out.println(Main.solution(0, 0, 0, 23, 59, 59));
    }

    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {

        // 360도
        // 초침 1초당 360/60 = 6
        // 분침 1분당 360/60 = 6 1초당 6/60 = 0.1
        // 시침 1시간당 360/12 = 30 1분당 30/60 = 0.5 1초당 0.5/60 = 1/120

        int answer = getCount(h2, m2, s2) - getCount(h1, m1, s1);
        // 시작하는 초는 세면 안됨
        // 0,0,0/ 12,0,0 으로 시간이 시작되면 +1 해주기
        if ((h1 == 0 || h1 == 12) && m1 == 0 && s1 == 0) answer += 1;

        return answer;
    }

    // 입력한 시간까지 겹친 횟수
    // 1분에 1번씩 초침이 분침과 시침을 만남
    // 예외 ) 59에서 0으로 갈때는 안만남,  0시0분0초 , 12시0분0초는 전부 만남 1회로 쳐야함
    public int getCount(int h, int m, int s) {
        double hourDegree = (h % 12) * 30 + m * 0.5 + s * (0.5 / 60);
        double minuteDegree = m * 6 + s * 0.1;
        double secondDegree = s * 6;

        int result = -1;

        if (secondDegree >= hourDegree) result += 1;
        if (secondDegree >= minuteDegree) result += 1;

        result += 2 * (h * 60 + m);
        result -= h;
        if (h >= 12) result -= 2;

        return result;
    }
}
