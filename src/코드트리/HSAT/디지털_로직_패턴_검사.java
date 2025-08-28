package 코드트리.HSAT;

import java.util.*;

public class 디지털_로직_패턴_검사 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.next();
        int K = scanner.nextInt();
        int M = scanner.nextInt();

        int len = S.length();
        // 길이 K 이상의 동일한 패턴이 M번 이상 반복되면 => 불안정
        // S <= 1 000 000

        // 길이 K의 패턴만 조사하면된다. K 이상인 패턴은 K가 일치하는 것 보다 작을 수 밖에 없음.
        Map<Long, Integer> patternCount = new HashMap<>();

        // 처음 문자 패턴 해시 생성
        long hash = 0;
        for (int i = 0; i < K; i++) {
            hash += (S.charAt(i) - '0') * (1L << i);
        }
        patternCount.put(hash, 1);

        // 롤링 해시 사용 => 패턴 확인
        for (int i = K; i < len; i++) {
            // i자리 추가
            hash += (S.charAt(i) - '0') * (1L << K);
            // 첫 문자 제거 및 비트 이동
            hash = hash >> 1;

            patternCount.put(hash, patternCount.getOrDefault(hash, 0) + 1);
        }

        // M번 반복 확인
        boolean isError = false;
        for (int value : patternCount.values()) {
            if (value >= M) {
                isError = true;
                break;
            }
        }

        System.out.println(isError ? 1 : 0);
    }
}
