package auction.ui.addlot;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import client.artefacts.Lot;
import client.artefacts.LotState;
import client.artefacts.User;

public class LotDelegate {
	private Lot lot;
	private String remainingTime;
	
	private String finishDateInFormat; 
	
	public String getFinishDateInFormat() {
		Date date = getFinishDate();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        finishDateInFormat = format.format(date);
		return finishDateInFormat;
	}
	
	public LotDelegate(Lot lot){
		this.lot = lot;
	}
	public void setLot(Lot lot){
		this.lot = lot;
	}
	
	public Lot getLot(){
		return this.lot;
	}
	
	public Integer getIdLot() {
		return lot.getIdLot();
	}

	public void setIdLot(Integer value) {
		lot.setIdLot(value);
	}

	public Integer getCode() {
		return lot.getCode();
	}

	public void setCode(Integer value) {
		lot.setCode(value);
	}

	public String getDescriptions() {
		return lot.getDescriptions();
	}

	public void setDescriptions(String value) {
		lot.setDescriptions(value);
	}

	public Date getFinishDate() {
		XMLGregorianCalendar calendar = lot.getFinishDate();
		Date date = null;
        if( null != calendar ) {
            date = calendar.toGregorianCalendar().getTime();
        }
        return date;
	}

	public void setFinishDate(Date date) {
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTime(date);
		XMLGregorianCalendar xmlCalendar = null;
		try {
			xmlCalendar = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(gCalendar);
		} catch (DatatypeConfigurationException e) {

		}

		lot.setFinishDate(xmlCalendar);
	}
	
	


	public String getName() {
		return lot.getName();
	}

	public void setName(String value) {
		lot.setName(value);
	}

	public BigDecimal getStartPrice() {
		return lot.getStartPrice();
	}

	public void setStartPrice(BigDecimal value) {
		lot.setStartPrice(value);
	}

	public LotState getState() {
		return lot.getState();
	}

	public void setState(LotState value) {
		lot.setState(value);
	}

	public String getUser() {
		String result = "";
		if( null != lot.getUser())
			result = lot.getUser().getUserLogin();
		return result;
	}

	public void setUser(User value) {
		lot.setUser(value);
	}
	
	
	public String getRemainingTime() {
		//this.remainingTime = this.getFinishDate();
		return this.remainingTime;
	}

	public void setRemainingTime(String value) {
		this.remainingTime = value;
	}
	

}
