package 프로그래머스;

import java.util.Arrays;

public class 숫자_게임 {
    public static void main(String[] args) {
        숫자_게임 Main = new 숫자_게임();
        int[] A = {5, 1, 3, 7};
        int[] B = {2, 2, 6, 8};
        System.out.println(Main.solution(A, B));
    }

    public int solution(int[] A, int[] B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        int idxA = 0;
        int idxB = 0;
        while (idxA < A.length) {
            while(idxB < B.length){
                if(A[idxA] < B[idxB]){
                    answer++;
                    idxB++;
                    break;
                }
                idxB++;
            }
            idxA++;
        }

        return answer;
    }
}
