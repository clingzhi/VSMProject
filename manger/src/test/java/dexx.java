import java.util.Arrays;

public class dexx {

	public static void main(String[] args) {
		int[] nums = {1, 2, 1, 2, 1, 2, 3, 3, 4, 2, 6};
		int key = 6;
		int[] results = new int[0];
		for (int i = 0; i < nums.length; i++) {
			int sum = 0;
			int count = 0;
			for (int j = i; j < nums.length; j++) {
				sum = sum + nums[j];
				count++;
				if (sum == key) {
					results = Arrays.copyOf(results, results.length + 1);
					results[results.length - 1] = count;
				}
			}
			Arrays.sort(results);
			System.out.println(results[results.length - 1]);
		}

	}
}