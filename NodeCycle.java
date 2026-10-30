import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Задача 3. Есть ли цикл на пути из start. Память сверх массива next — O(1).
public class NodeCycle {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        int[] next = new int[n + 1];
        st = new StringTokenizer(in.readLine());
        for (int i = 1; i <= n; i++)
            next[i] = Integer.parseInt(st.nextToken());

        // медленный шаг 1, быстрый шаг 2. В цикле быстрый его догоняет.
        int slow = start;
        int fast = start;
        boolean cycle = false;

        while (valid(fast, n) && valid(next[fast], n)) {
            slow = next[slow];
            fast = next[next[fast]];
            if (slow == fast) {
                cycle = true;
                break;
            }
        }

        out.println(cycle ? "YES" : "NO");
        out.flush();
    }

    private static boolean valid(int v, int n) {
        return v >= 1 && v <= n;
    }
}
