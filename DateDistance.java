public class DateDistance {
	public static void main (String [] args) {
		long month0 = Long.parseLong(args[0]);
		long day0 = Long.parseLong(args[1]);
		long year0 = Long.parseLong(args[2]);
		long month1 = Long.parseLong(args[3]);
		long day1 = Long.parseLong(args[4]);
		long year1 = Long.parseLong(args[5]);
		
		System.out.println(distance(month0, day0, year0, month1, day1, year1));
	}
	
	public static boolean isCommonYear (long year) {
		return ((year % 100 == 0 || year % 4 != 0) && year % 400 != 0);
	}
	
	public static long monthLength (long month, long year) {
		long day = 0;
		
		if (month == 2 && isCommonYear(year) == true) {
			day = 28;
		}
		else if (month == 2 && isCommonYear(year) == false) {
			day = 29;
		}
		else if (month == 4 || month == 6 || month == 9 || month == 11) {
			day = 30;
		}
		else {
			day = 31;
		}
		return day;
	}
	
	public static boolean isRealDate (long month, long day, long year) {
		boolean realDate = true;
		
		if (day <= 0 || day > 31 || month <= 0 || month > 12 || year <= 0) {
			realDate = false;
		}
		if (day > monthLength(month, year)) {
			realDate = false;
		}
		return realDate;
	}
	
	public static long distance (long month0, long day0, long year0, long month1, long day1, long year1) {
		long totalDays0 = 0;
		long totalDays1 = 0;
		
		if (isRealDate(month0, day0, year0) && isRealDate(month1, day1, year1)) {
			for (long i = 1; i < year0; i++) {
				if (isCommonYear(i)) {
					totalDays0 += 365;
				}
				else {
					totalDays0 += 366;
				}
			}
			for (long i = 1; i < month0; i++) {
				totalDays0 += monthLength(i, year0);
			}
			totalDays0 += day0;
			
			for (long i = 1; i < year1; i++) {
				if (isCommonYear(i)) {
					totalDays1 += 365;
				}
				else {
					totalDays1 += 366;
				}
			}
			for (long i = 1; i < month1; i++) {
				totalDays1 += monthLength(i, year1);
			}
			totalDays1 += day1;
		}
		return Math.abs(totalDays1 - totalDays0);
	}
}