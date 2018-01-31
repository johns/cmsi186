public class SumOfDigits {
	public static void main (String [] args) {
		long n = Long.parseLong(args[0]);
		long sum = 0;
		while (n != 0) {
			sum = sum + (n % 10);
			n = n / 10;
		}
		System.out.println(sum);
	}
}