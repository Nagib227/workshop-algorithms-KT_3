import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Задача 4. Самый большой прямоугольник из единиц.
public class MaxRectangle {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] height = new int[m];
        int best = 0;

        for (int i = 0; i < n; i++) {
            String row = in.readLine();
            for (int j = 0; j < m; j++) {
                if (row.charAt(j) == '1')
                    height[j]++;
                else
                    height[j] = 0;
            }
            int area = largest(height);
            if (area > best)
                best = area;
        }

        out.println(best);
        out.flush();
    }

    // гистограмма: столбцы height[j] подряд идущих единиц, заканчивающихся в этой строке
    private static int largest(int[] height) {
        int m = height.length;
        // -1 в начале и нулевой столбик в конце, плюс все индексы строки
        int[] stack = new int[m + 2];
        int top = 0;
        stack[top++] = -1;
        int best = 0;

        for (int i = 0; i <= m; i++) {
            int cur = i == m ? 0 : height[i];
            while (top > 1 && height[stack[top - 1]] > cur) {
                int h = height[stack[--top]];
                int width = i - stack[top - 1] - 1;
                int area = h * width;
                if (area > best)
                    best = area;
            }
            stack[top++] = i;
        }

        return best;
    }
}
