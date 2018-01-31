class Clock {
	private double slice;
	private double hours;
	private double minutes;
	private double seconds;

	public Clock(double slice) {
		this.slice = slice;
		this.hours = 0;
		this.minutes = 0;
		this.seconds = 0;
	}

	public double hours() {
		return this.hours;
	}

	public double minutes() {
		return this.minutes;
	}

	public double seconds() {
		return this.seconds;
	}

	public void tick() {

		this.seconds += slice;

		if (this.seconds >= 60) {
			this.minutes += (int)(this.seconds / 60);
			this.seconds = this.seconds % 60;
		}

		if (this.minutes >= 60) {
			this.hours += this.minutes / 60;
			this.minutes = this.minutes % 60;
		}

		if (this.hours >= 12) {
			this.hours = 0;
		}
	}

	public double getMinuteAngle() {
		return 6 * this.minutes + 0.1 * this.seconds ;
	}

	public double getHourAngle() {
		return 30 * this.hours + 0.5 * this.minutes + 0.1 * this.seconds / 12;
	}

	public String toString() {
		return (int)this.hours + ":" + (int)this.minutes + ":" + this.seconds;
	}
}

class ClockSolver1 {
	public static void main (String [] args) throws Exception {

		double slice = 60.0;
		double targetAngle = 180.0;
		String bestTime = "";
		int loops = (int)(5.5 * 360 / targetAngle);

		try {
			slice = Double.parseDouble(args[0]);

			if (slice <= 0) {
				System.err.println("Slice cannot be 0 or negative, please try again.");
				System.exit(0);
			}
			else if (slice >= 1800) {
				System.err.println("Slice cannot be 1800 or larger, please try again.");
				System.exit(0);
			}
		} 
		catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Slice was not given, default slice is 60.0 seconds.");
		}

		String[] timeResults = new String[loops];
		double angleRange = 5.5 * slice / 60 / 2;
		Clock c = new Clock(slice);
		double angleDifference = Math.abs((c.getMinuteAngle() - c.getHourAngle()));

		for (int i = 0; i < loops; i++) {
			for (int j = 0; j < (int)(43200 / (slice * loops)); j++) {
				angleDifference = Math.abs((c.getMinuteAngle() - c.getHourAngle()));

					if (angleRange > Math.abs(targetAngle - angleDifference)) {
						angleRange = Math.abs(targetAngle - angleDifference);
						bestTime = c.toString();
					}
					else if (angleRange > Math.abs(360 - targetAngle - angleDifference)) {
						angleRange = Math.abs(360 - targetAngle - angleDifference);
						bestTime = c.toString();
					}

				c.tick();
			}

			timeResults[i] = bestTime;
			angleRange = 5.5 * slice / 60 / 2;

			if (i > 0 && timeResults[i] == timeResults[i-1]) {
				i--;
			}
			else if (i > 1 && timeResults[i] == timeResults[i-2]) {
				i--;
			}
		}

		for (int i = 0; i < timeResults.length; i++) {
			System.out.println(timeResults[i]);
		}
	}
}

class ClockSolver2 {
	public static void main (String [] args) throws Exception {

		double slice = 60.0;
		double targetAngle = 180.0;
		String bestTime = "";
		int loops = (int)(5.5 * 360 / targetAngle);

		try {
			slice = Double.parseDouble(args[0]);

			if (slice <= 0) {
				System.err.println("Slice cannot be 0 or negative, please try again.");
				System.exit(0);
			}
			else if (slice >= 1800) {
				System.err.println("Slice cannot be 1800 or larger, please try again.");
				System.exit(0);
			}
		} 
		catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Slice was not given, default slice is 60.0 seconds.");
		}

		try {
			targetAngle = Double.parseDouble(args[1]);

			if (targetAngle <= 0) {
				System.err.println("Angle cannot be 0 or negative, please try again.");
				System.exit(0);
			}
			else if (targetAngle >= 360) {
				System.err.println("Angle cannot be 360 or larger, please try again.");
				System.exit(0);
			}
		} 
		catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Angle was not given, default angle is 180.0 degrees.");
		}

		if (targetAngle != 180.0) {
			loops = 2 * loops;
		}

		String[] timeResults = new String[loops];
		double angleRange = 5.5 * slice / 60 / 2;
		Clock c = new Clock(slice);
		double angleDifference = Math.abs((c.getMinuteAngle() - c.getHourAngle()));

		for (int i = 0; i < loops; i++) {
			for (int j = 0; j < (int)(43200 / (slice * loops)); j++) {
				angleDifference = Math.abs((c.getMinuteAngle() - c.getHourAngle()));

					if (angleRange > Math.abs(targetAngle - angleDifference)) {
						angleRange = Math.abs(targetAngle - angleDifference);
						bestTime = c.toString();
					}
					else if (angleRange > Math.abs(360 - targetAngle - angleDifference)) {
						angleRange = Math.abs(360 - targetAngle - angleDifference);
						bestTime = c.toString();
					}

				c.tick();
			}

			timeResults[i] = bestTime;
			angleRange = 5.5 * slice / 60 / 2;

			if (targetAngle == 180.0 && i > 0 && timeResults[i] == timeResults[i-1]) {
				i--;
			}
			else if (i > 1 && timeResults[i] == timeResults[i-2]) {
				i--;
			}
		}

		for (int i = 0; i < timeResults.length; i++) {
			System.out.println(timeResults[i]);
		}
	}
}