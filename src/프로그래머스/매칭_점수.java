package 프로그래머스;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 매칭_점수 {
    public static void main(String[] args) {
        매칭_점수 Main = new 매칭_점수();
        String word = "blind";
        String[] pages = {
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"
        };

        System.out.println(Main.solution(word, pages));
    }

    // 기본점수 : 검색어가 등장하는 횟수(대소문자 무시)
    // 외부 링크 수 : 다른 외부 페이지로 연결된 링크의 개수
    // 링크점수 : 해당 웹페이지로 링크가 걸린 다른 웹페이지의 기본점수 / 외부 링크 수

    // 매칭점수 : 기본점수 + 링크점수
    public int solution(String word, String[] pages) {
        Map<String, Info> pageInfoMap = new HashMap<>();

        // page마다 meta tag의 url 저장
        for (int i = 0; i < pages.length; i++) {
            String homeURL = getWebPageURL(pages[i]);
            Info info = new Info(i);
            List<String> externalLinks = getExternalLink(pages[i]);
            int basicPoint = calBasicPoint(word, pages[i]);
            info.externalLinks = externalLinks;
            info.basicPoint = basicPoint;
            pageInfoMap.put(homeURL, info);
        }

        for (String key : pageInfoMap.keySet()) {
            Info pageInfo = pageInfoMap.get(key);
            for (String extLink : pageInfo.externalLinks) {
                if (pageInfoMap.containsKey(extLink)) {
                    pageInfoMap.get(extLink).linkPoint += (double) pageInfo.basicPoint / pageInfo.externalLinks.size();
                }
            }
        }

        double maxPoint = 0;
        int idx = 0;
        for (Info info : pageInfoMap.values()) {
            double point = info.basicPoint + info.linkPoint;
            if (point > maxPoint) {
                maxPoint = point;
                idx = info.index;
            } else if (point == maxPoint && idx > info.index) {
                idx = info.index;
            }
        }

        return idx;
    }

    private String getWebPageURL(String page) {
        Pattern homeURLPattern = Pattern.compile("<meta property=\"og:url\" content=\"(\\S*)\"");
        Matcher homeURLMatcher = homeURLPattern.matcher(page);

        String url = null;
        if (homeURLMatcher.find()) {
            url = homeURLMatcher.group(1);
        }

        return url;
    }

    private List<String> getExternalLink(String page) {
        Pattern externalLinkPattern = Pattern.compile("<a href=\"(\\S*)\"");
        Matcher externalLinkMatcher = externalLinkPattern.matcher(page);

        List<String> links = new ArrayList<>();
        while (externalLinkMatcher.find()) {
            links.add(externalLinkMatcher.group(1));
        }
        return links;
    }


    private int calBasicPoint(String word, String page) {
        // \b는 단어 경계 숫자,문자 이외의 것
        Pattern wordPattern = Pattern.compile("\\b(?i)" + word + "\\b");
        // body 사이의 content에서 찾기
        String body = page.split("<body>")[1].split("</body>")[0].replaceAll("[0-9]", " ");
        Matcher wordMatcher = wordPattern.matcher(body);
        int cnt = 0;
        while (wordMatcher.find()) {
            cnt++;
        }

        return cnt;
    }

    private class Info {
        int index;
        List<String> externalLinks;
        int basicPoint;
        double linkPoint;

        public Info() {
        }

        public Info(int index) {
            this.index = index;
        }
    }
}
