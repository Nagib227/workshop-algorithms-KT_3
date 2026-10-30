import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// Задача 2. Кратчайший путь по клеткам от S до T. Стена — #.
public class GridPath {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][];
        int sr = 0;
        int sc = 0;
        for (int i = 0; i < n; i++) {
            map[i] = in.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'S') {
                    sr = i;
                    sc = j;
                }
            }
        }

        int[] dist = new int[n * m];
        for (int i = 0; i < dist.length; i++)
            dist[i] = -1;

        ArrayDeque<Integer> q = new ArrayDeque<>();
        int start = sr * m + sc;
        dist[start] = 0;
        q.add(start);

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int ans = -1;

        while (!q.isEmpty()) {
            int v = q.poll();
            int r = v / m;
            int c = v % m;
            if (map[r][c] == 'T') {
                ans = dist[v];
                break;
            }

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (nr < 0 || nc < 0 || nr >= n || nc >= m)
                    continue;
                if (map[nr][nc] == '#')
                    continue;

                int u = nr * m + nc;
                if (dist[u] != -1)
                    continue;

                dist[u] = dist[v] + 1;
                q.add(u);
            }
        }

        out.println(ans);
        out.flush();
    }
}
