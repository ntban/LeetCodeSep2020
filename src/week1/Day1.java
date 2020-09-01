package week1;

//problem: https://leetcode.com/explore/challenge/card/september-leetcoding-challenge/554/week-1-september-1st-september-7th/3445/
public class Day1 {
	/**
	 * generate all time from the numbers, return the max time
	 * space: 8, time: 24 
	 * @return
	 */
	public String largestTimeFromDigits(int[] A) {
		x = new int[4];
		check = new boolean[4];
		a = A;
		max = -1;
		result = "";
		Try(0);
		return result;
	}
	
	/**
	 * check from 23:59 to 00:00, if there is a correct time, return
	 * space: 20, time 36000
	 */
	public String largestTimeFromDigits1(int[] A) {
		return bruteForce(A);
	}

	int x[];
	boolean check[];
	int a[];
	int max;
	String result;

	void Try(int i) {
		if (i == 4) {
			checkMax();
			return;
		}
		for (int j = 0; j < 4; j++) {
			if (check[j]) continue;
			x[i] = a[j];
			check[j] = true;
			Try(i + 1);
			check[j] = false;
		}
	}

	void checkMax() {
		if(x[0] > 2 || (x[0] == 2 && x[1] > 3) || x[2] > 5) return;
		int r = x[0]*1000+x[1]*100+x[2]*10+x[3];
		if(r > max) {
			max = r;
			result = String.format("%d%d:%d%d", x[0],x[1],x[2],x[3]);
		}
	}

	private String bruteForce(int A[]) {
		int count[] = new int[10];
		for (int i = 0; i < A.length; i++) count[A[i]]++;
		int max = 2359;
		while (max >= 0) {
			if (check(max, count)) {
				int digits[] = new int[4];
				int j = 3;
				while (j >= 0) {
					digits[j] = max % 10;
					max /= 10;
					j--;
				}
				return String.format("%d%d:%d%d", digits[0], digits[1], digits[2], digits[3]);
			}
			max = dec1Sec(max);
		}
		return "";
	}

	private int dec1Sec(int max) {
		if (max > 0 && max % 100 == 0)  return (max / 100 - 1) * 100 + 59;
		return --max;
	}

	private boolean check(int max, int count[]) {
		int check[] = new int[10];
		int j = 0;
		while (j++ < 4) {
			check[max % 10]++;
			max /= 10;
		}
		for (int i = 0; i < check.length; i++)
			if (count[i] != check[i]) return false;
		return true;

	}

	public static void main(String[] args) {
		int a[] = { 5, 4, 5, 6 };
		System.out.println(new Day1().largestTimeFromDigits(a));
	}
}
