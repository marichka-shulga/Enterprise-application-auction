package auction.ui.supportingCapabilities;

import java.util.Calendar;
import java.util.Date;

public class CalculateRemainingTime {
	
	public static RemainingTime getRemainingTimeInCalendar(Date finishDate, boolean calculateRemTime) {
		RemainingTime remainingTime = new RemainingTime();
		if( calculateRemTime ){
			Calendar curCalendar = Calendar.getInstance();
			curCalendar.setTime(new Date());
			Calendar finishCalendar = Calendar.getInstance();
			finishCalendar.setTime(finishDate);
			long remainingTimeInMillis = finishCalendar.getTimeInMillis() - curCalendar.getTimeInMillis();
		
			int seconds = (int) remainingTimeInMillis / 1000;
			int minutes = (int) seconds / 60;
			int hours = (int) minutes / 60;
			int days = (int) hours / 24;
			
			remainingTime.setRemainderDays(days);
			remainingTime.setRemainderHours(hours - days*24);
			remainingTime.setRemainderMinutes(minutes - hours*60);
			remainingTime.setRemainderSeconds(seconds - minutes*60);

		}
	    return remainingTime;
	}

}
