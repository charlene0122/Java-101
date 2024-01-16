/**
 * CIT 5910 HW1 Program 2
 * Based on a given age in years, calculates the number of days old, number of minutes old, and the number of times your heart has beat.
 * Leap years are considered when calculating the number of days old.
 * 
 * @author zhifeiwu
 */
public class CalculateHealthData {

	public static void main(String[] args) {

		//age in years
		int userAgeYears = 21;
		
		//calculate the age in days 
		int userAgeDays;
		
		//calculate the age in minutes
		int userAgeMinutes;
		
		//calculate the number of total heart beats based on average number of beats per minute
		int totalHeartbeats;
		int avgBeatsPerMinute = 72;

		
		//TODO my codes are as below
		//calculate the numbers
		int leapYears = userAgeYears / 4;			  	//count the number of leap years
		userAgeDays = userAgeYears * 365 + leapYears; 	//for each leap year, there is an extra day		
		userAgeMinutes = userAgeDays * 24 * 60;			//24 hours a day, 60 minutes an hour
		totalHeartbeats = userAgeMinutes * avgBeatsPerMinute;			// apply the average heart beats
		
		//print the result
		System.out.println("You are " + userAgeDays + " days old. You are " + userAgeMinutes + " minutes old.");
		System.out.println("Your hear has beat " + totalHeartbeats + " times.");
		
	}
}