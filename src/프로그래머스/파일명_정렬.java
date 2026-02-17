package 프로그래머스;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 파일명_정렬 {
    public static void main(String[] args) {
        파일명_정렬 Main = new 파일명_정렬();
        String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        System.out.println(Arrays.toString(Main.solution(files)));
    }

    public String[] solution(String[] files) {
        List<File> fileList = new ArrayList<>();
        for (String file : files) {
            // HEAD = 숫자 나오기전까지
            int idx = 0;
            while (idx < file.length()) {
                // 숫자면 break
                if (Character.isDigit(file.charAt(idx))) {
                    break;
                }
                idx++;
            }
            String head = file.substring(0, idx);

            // NUMBER = 숫자외 다른문자 나오기전까지
            int numberStart = idx;
            while (idx < file.length()) {
                if (!Character.isDigit(file.charAt(idx))) {
                    break;
                }
                // 5글자가 넘어가면 그 뒤는 TAIL로 취급해야 함
                if (idx - numberStart >= 5) {
                    break;
                }
                idx++;
            }
            String number = file.substring(numberStart, idx);

            // TAIL = 남은 부분
//            String tail = file.substring(idx);

            // 저장
            fileList.add(new File(file, head, number));
        }

        // 정렬
        fileList.sort((o1, o2) ->
                o1.upperHead.equals(o2.upperHead) ? Integer.compare(o1.number, o2.number) : o1.upperHead.compareTo(o2.upperHead));

        return fileList.stream().map(o -> o.original).toArray(String[]::new);
    }

    private class File {
        String original;
        String upperHead;
        int number;

        public File(String original, String head, String numberStr) {
            this.original = original;
            this.upperHead = head.toUpperCase();
            this.number = Integer.parseInt(numberStr);
        }
    }
}
