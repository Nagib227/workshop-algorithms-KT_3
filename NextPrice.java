import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Задача 1. Через сколько дней впервые будет цена строго больше сегодняшней.
public class NextPrice {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.readLine().trim());
        int[] price = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++)
            price[i] = Integer.parseInt(st.nextToken());

        int[] wait = new int[n];
        // в стеке индексы, цены по ним убывают слева направо
        int[] stack = new int[n];
        int top = 0;

        for (int i = 0; i < n; i++) {
            while (top > 0 && price[stack[top - 1]] < price[i]) {
                int day = stack[--top];
                wait[day] = i - day;
            }
            stack[top++] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0)
                sb.append(' ');
            sb.append(wait[i]);
        }
        out.println(sb);
        out.flush();
    }
}
