package auction.ui;

import java.io.Serializable;

public class Lot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2227671539529018388L;
	private String code;
	private String name;
	private String finishDate;
	private String state;

	
	
	public Lot(String code, String name, String finishDate, String state) {
		super();
		this.code = code;
		this.name = name;
		this.finishDate = finishDate;
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
