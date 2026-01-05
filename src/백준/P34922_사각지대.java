package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P34922_사각지대 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(br.readLine());

        int classArea = w * h;
        double teacherDetect = Math.PI * r * r / 4;

        System.out.println(classArea - teacherDetect);
    }
}
