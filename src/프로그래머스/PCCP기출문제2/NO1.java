package 프로그래머스.PCCP기출문제2;

public class NO1 {
    public static void main(String[] args) {
        NO1 Main = new NO1();
        String video_len = "34:33";
        String pos = "13:00";
        String op_start = "00:55";
        String op_end = "02:55";
        String[] commends = {"next", "prev"};
        System.out.println(Main.solution(video_len, pos, op_start, op_end, commends));
    }

    // command 종류 : prev, next 10초 뒤, 앞 이동
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int videoLen = timeConvertStringToInt(video_len);
        int posTime = timeConvertStringToInt(pos);
        int opStart = timeConvertStringToInt(op_start);
        int opEnd = timeConvertStringToInt(op_end);

        // opStart <= posTime < opEnd 이면 opEnd로 이동
        if(opStart <= posTime && posTime < opEnd){
            posTime = opEnd;
        }
        for(String command : commands){
            if("prev".equals(command)){
                posTime = posTime - 10 < 0 ? 0 : posTime - 10;
            }else{
                posTime = posTime + 10 > videoLen ? videoLen : posTime + 10;
            }
            if(opStart <= posTime && posTime < opEnd){
                posTime = opEnd;
            }
        }

        return timeConvertIntToString(posTime);
    }

    private int timeConvertStringToInt(String time){
        String[] t = time.split(":");
        return Integer.parseInt(t[0])*60 + Integer.parseInt(t[1]);
    }

    private String timeConvertIntToString(int time){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%02d", time/60)).append(":").append(String.format("%02d", time%60));
        return sb.toString();
    }
}
