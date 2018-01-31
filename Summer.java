public class Summer {
	public static void main (String [] args) {
		long runningTotal = 0;
		for (int i = 0; i < args.length; i = i + 1) {
			runningTotal = runningTotal + Long.parseLong(args[i]);
		}
		System.out.println(runningTotal);
	}
}