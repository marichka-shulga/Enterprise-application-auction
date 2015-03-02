
package client.artefacts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for baseResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="baseResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="errorMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stateResult" type="{http://auction.facadeservice/jaxws/auctionservice}stateResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "baseResponse", propOrder = {
    "errorMessage",
    "stateResult"
})
@XmlSeeAlso({
    GetLotByIdResponse.class,
    GetLotsResponse.class,
    UserAuthenticResponse.class
})
public class BaseResponse {

    protected String errorMessage;
    @XmlSchemaType(name = "string")
    protected StateResult stateResult;

    /**
     * Gets the value of the errorMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the value of the errorMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorMessage(String value) {
        this.errorMessage = value;
    }

    /**
     * Gets the value of the stateResult property.
     * 
     * @return
     *     possible object is
     *     {@link StateResult }
     *     
     */
    public StateResult getStateResult() {
        return stateResult;
    }

    /**
     * Sets the value of the stateResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link StateResult }
     *     
     */
    public void setStateResult(StateResult value) {
        this.stateResult = value;
    }

}
