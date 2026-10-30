import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// Задача 5. Путь от S до T. Дверь A..J открывается ключом a..j.
public class DoorPath {
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

        // клетка и маска уже собранных ключей: ключей не больше 10
        int keys = 1 << 10;
        boolean[][][] seen = new boolean[n][m][keys];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sr, sc, 0, 0});
        seen[sr][sc][0] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int ans = -1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int mask = cur[2];
            int dist = cur[3];

            if (map[r][c] == 'T') {
                ans = dist;
                break;
            }

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (nr < 0 || nc < 0 || nr >= n || nc >= m)
                    continue;

                char ch = map[nr][nc];
                if (ch == '#')
                    continue;

                int nmask = mask;
                if (ch >= 'A' && ch <= 'J') {
                    int bit = ch - 'A';
                    if ((mask & (1 << bit)) == 0)
                        continue;
                }
                if (ch >= 'a' && ch <= 'j')
                    nmask |= 1 << (ch - 'a');

                if (seen[nr][nc][nmask])
                    continue;

                seen[nr][nc][nmask] = true;
                q.add(new int[]{nr, nc, nmask, dist + 1});
            }
        }

        out.println(ans);
        out.flush();
    }
}
