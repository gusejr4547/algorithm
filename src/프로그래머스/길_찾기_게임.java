package 프로그래머스;

import java.util.ArrayList;
import java.util.List;

public class 길_찾기_게임 {
    public static void main(String[] args) {

    }

    public int[][] solution(int[][] nodeinfo) {
        int len = nodeinfo.length;
        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            nodeList.add(new Node(nodeinfo[i][1], nodeinfo[i][0], i + 1));
        }

        List<Node> sortedByX = new ArrayList<>(nodeList);
        List<Node> sortedByY = new ArrayList<>(nodeList);

        sortedByX.sort((e1, e2) -> e1.x - e2.x);
        sortedByY.sort((e1, e2) -> e2.y - e1.y);

        List<Integer> preorderList = new ArrayList<>();
        List<Integer> postorderList = new ArrayList<>();

        traversal(preorderList, postorderList, sortedByX, sortedByY);

        int[][] answer = new int[2][len];
        for (int i = 0; i < len; i++) {
            answer[0][i] = preorderList.get(i);
            answer[1][i] = postorderList.get(i);
        }

        return answer;
    }

    public void traversal(List<Integer> preorderList, List<Integer> postorderList, List<Node> arrX, List<Node> arrY) {
        Node root = arrY.get(0);
        int index = arrX.indexOf(root);
        // index를 기준으로 좌, 우로 서브 트리가 생김
        List<Node> leftSubTreeY = new ArrayList<>();
        List<Node> rightSubTreeY = new ArrayList<>();
        for (int i = 1; i < arrY.size(); i++) {
            if (arrY.get(i).x < root.x) {
                leftSubTreeY.add(arrY.get(i));
            } else {
                rightSubTreeY.add(arrY.get(i));
            }
        }

        preorderList.add(root.num);
        if (!leftSubTreeY.isEmpty()) {
            traversal(preorderList, postorderList, arrX.subList(0, index), leftSubTreeY);
        }
        if (!rightSubTreeY.isEmpty()) {
            traversal(preorderList, postorderList, arrX.subList(index + 1, arrX.size()), rightSubTreeY);
        }
        postorderList.add(root.num);
    }

    public class Node {
        int y;
        int x;
        int num;

        public Node(int y, int x, int num) {
            this.y = y;
            this.x = x;
            this.num = num;
        }
    }
}
