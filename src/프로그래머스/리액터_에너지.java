package 프로그래머스;

public class 리액터_에너지 {
    public static void main(String[] args) {
        리액터_에너지 Main = new 리액터_에너지();
        int[] energyLevels ={3,1,2};
        System.out.println(Main.solution(energyLevels));
    }

    // 각 리액터는 초당 2 소모
    // 소모할 수 있으면 좌우에 각각 1씩 전달
    // 0,1은 동작안함
    // 양 끝에 있는거는 한쪽으로만  에너지 전달하는데 2소모함

    // 모든 리액터가 동작하지 않는 최초 시간

    // N <= 1000

    public int solution(int[] energyLevels) {
        int answer = 0;

        int N = energyLevels.length;

        while (true) {
            boolean end = true;
            int[] next = new int[N];
            for (int i = 0; i < N; i++) {
                if (energyLevels[i] >= 2) {
                    end = false;
                    next[i] -= 2;
                    // 양옆에 에너지 1전달
                    if (i == 0) {
                        next[i + 1] += 1;
                    } else if (i == N - 1) {
                        next[i - 1] += 1;
                    } else {
                        next[i + 1] += 1;
                        next[i - 1] += 1;
                    }
                }
            }
            if (end) {
                break;
            }
            // next에 담아둔거 energy에 더하기
            for (int i = 0; i < N; i++) {
                energyLevels[i] += next[i];
            }
            answer++;
        }

        return answer;
    }
}
