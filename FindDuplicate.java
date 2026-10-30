import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Задача 6. Найти повтор, массив не менять, память O(1).
public class FindDuplicate {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.readLine().trim());
        int[] nums = new int[n + 1];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n + 1; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        // число — это ссылка на индекс. Повтор замыкает цикл, вход в цикл и есть ответ.
        int slow = nums[0];
        int fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        out.println(slow);
        out.flush();
    }
}
