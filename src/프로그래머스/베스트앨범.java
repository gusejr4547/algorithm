package 프로그래머스;

import java.util.*;

public class 베스트앨범 {
    public static void main(String[] args) {
        베스트앨범 Main = new 베스트앨범();
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        System.out.println(Arrays.toString(Main.solution(genres, plays)));
    }

    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        Map<String, HashMap<Integer, Integer>> mapEach = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);

            if (mapEach.containsKey(genres[i])) {
                HashMap<Integer, Integer> each = mapEach.get(genres[i]);
                each.put(i, plays[i]);
            } else {
                HashMap<Integer, Integer> temp = new HashMap<>();
                temp.put(i, plays[i]);
                mapEach.put(genres[i], temp);
            }
        }
        List<String> genreList = new ArrayList<>(map.keySet());
        genreList.sort((o1, o2) -> map.get(o2) - map.get(o1));

        for (String key : genreList) {
            Map<Integer, Integer> temp = mapEach.get(key);
            List<Integer> musicIdx = new ArrayList<>(temp.keySet());
            musicIdx.sort((o1, o2) -> temp.get(o2) == temp.get(o1) ? o1 - o2 : temp.get(o2) - temp.get(o1));
            answer.add(musicIdx.get(0));
            if (musicIdx.size() > 1) {
                answer.add(musicIdx.get(1));
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
