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

            while (idx < file.length()) {
                // 숫자가 아니면 break
                if (!Character.isDigit(file.charAt(idx))) {
                    break;
                }
                idx++;
            }
            String number = file.substring(head.length(), idx);

            // TAIL = 남은 부분
            String tail = file.substring(idx);

            // 저장
            fileList.add(new File(head, number, tail));
        }

        // 정렬
        fileList.sort((o1, o2) ->
                o1.upperHead.equals(o2.upperHead) ? Integer.compare(o1.number, o2.number) : o1.upperHead.compareTo(o2.upperHead));

        return fileList.stream().map(o -> o.head + o.numberStr + o.tail).toArray(String[]::new);
    }

    private class File {
        String head;
        String numberStr;
        String tail;
        String upperHead;
        int number;

        public File(String head, String numberStr, String tail) {
            this.head = head;
            this.numberStr = numberStr;
            this.tail = tail;
            this.upperHead = head.toUpperCase();
            this.number = Integer.parseInt(numberStr);
        }
    }
}
