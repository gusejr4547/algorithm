package 프로그래머스;

public class 이진_변환_반복하기 {
    public static void main(String[] args) {

    }

    public int[] solution(String s) {
        int[] answer = new int[2];

        // s <= 150 000
        // 0의 개수를 샌다고 했을때

        int changeCount = 0;
        int zeroCount = 0;

        while(s.length() != 1){
            // 1개수 세기
            int oneCount = 0;
            for(int i=0; i<s.length(); i++){
                if(s.charAt(i) == '1'){
                    oneCount++;
                }else{
                    zeroCount++;
                }
            }

            // s 갱신
            s = Integer.toBinaryString(oneCount);

            // 변환 1번추가
            changeCount += 1;
            // System.out.println(s);
        }

        answer[0] = changeCount;
        answer[1] = zeroCount;

        return answer;
    }
}
