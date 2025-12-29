package 백준;

import java.io.*;
import java.util.*;

public class P35033_용사 {
    // N개의 비석 존재
    // 비석 사이에 N-1개의 길. 비석은 트리구조
    // 1. 직접 연결된 비석의 개수가 하나 이하인 비석을 고르고 제거. 비석에 저주의 낙인이 있으면 같이 사라짐
    // 2. 해당 비석과 직접 연결된 비석에 저주의 낙인이 새겨진다. 이미 있으면 변하지 않음

    // 저주의 낙인이 새겨진 비석이 20개 넘어가면 안됨.
    // 1번 비석에 크크발의 영혼이 있으므로 1번 제거는 제일 마지막...

    // 제거 순서 출력.

    // N <= 200 000

    static List<Integer>[] adj, child;
    static int[] sz;
    static StringBuilder sb;

    // FastScanner (byte-based)
    static final class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do c = readByte(); while (c <= ' ' && c != -1);
            int sign = 1;
            if (c == '-') { sign = -1; c = readByte(); }
            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        int N = fs.nextInt();

        adj = new List[N + 1];
        child = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
            child[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }

        // 1번을 루트로 하는 트리..
        // 후위순회?
        // 그냥 후위순회 하면 안되고 가장 낙인이 많이 생기는 서브 트리 부터 후위순회 해야지 최적

        // 서브트리 사이즈, child 채우기
        sz = new int[N + 1];
        calSize(1, 0);

        // 정렬
        for (int i = 1; i <= N; i++) {
            child[i].sort((o1, o2) -> Integer.compare(sz[o2], sz[o1]));
        }

        sb = new StringBuilder();
        postOrder(1, 0);

        System.out.println(sb);
    }

    private static void calSize(int node, int parent) {
        sz[node] = 1;
        for (int next : adj[node]) {
            if (next == parent) {
                continue;
            }
            child[node].add(next);
            calSize(next, node);
            sz[node] += sz[next];
        }
    }

    private static void postOrder(int node, int parent) {
        // 자식
        for (int next : child[node]) {
            if (next == parent) {
                continue;
            }
            postOrder(next, node);
        }

        // 서브 루트
        sb.append(node).append(" ");
    }
}
