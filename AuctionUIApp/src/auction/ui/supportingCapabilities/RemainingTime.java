package auction.ui.supportingCapabilities;

public class RemainingTime {

	private int remainderDays;
	private int remainderHours;
	private int remainderMinutes;
	private int remainderSeconds;
	
	public RemainingTime(){
		remainderDays = 0;
		remainderHours = 0;
		remainderMinutes = 0;
		remainderSeconds = 0;
	}
	
	public int getRemainderDays() {
		return remainderDays;
	}

	public void setRemainderDays(int remainderDays) {
		this.remainderDays = remainderDays;
	}

	public int getRemainderHours() {
		return remainderHours;
	}

	public void setRemainderHours(int remainderHours) {
		this.remainderHours = remainderHours;
	}

	public int getRemainderMinutes() {
		return remainderMinutes;
	}

	public void setRemainderMinutes(int remainderMinutes) {
		this.remainderMinutes = remainderMinutes;
	}

	public int getRemainderSeconds() {
		return remainderSeconds;
	}

	public void setRemainderSeconds(int remainderSeconds) {
		this.remainderSeconds = remainderSeconds;
	}

}
