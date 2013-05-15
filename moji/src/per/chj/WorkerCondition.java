package per.chj;
import java.util.Date;

public class WorkerCondition {

	private String city;
	private String snsId;
	private Date startDate;
	private Date endDate;
	private long interval;

	public WorkerCondition(String city, String snsId, Date startDate, Date endDate, long interval) {
		super();
		this.city = city;
		this.snsId = snsId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.interval = interval;
	}

	public WorkerCondition() {
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSnsId() {
		return snsId;
	}

	public void setSnsId(String snsId) {
		this.snsId = snsId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}
}