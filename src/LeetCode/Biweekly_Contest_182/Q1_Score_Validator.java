package LeetCode.Biweekly_Contest_182;

public class Q1_Score_Validator {
    public int[] scoreValidator(String[] events) {
        int score = 0;
        int counter = 0;

        for (String str : events) {
            if (Character.isDigit(str.charAt(0))) {
                score += str.charAt(0) - '0';
            } else if ("W".equals(str)) {
                counter += 1;
            } else if ("WD".equals(str)) {
                score += 1;
            } else if ("NB".equals(str)) {
                score += 1;
            }
            if(counter == 10){
                break;
            }
        }

        return new int[]{score, counter};
    }
}
