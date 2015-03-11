
package client.artefacts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getWinningBidResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getWinningBidResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://auction.facadeservice/jaxws/auctionservice}getWinningBidByIdResponseResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getWinningBidResponse", propOrder = {
    "_return"
})
public class GetWinningBidResponse {

    @XmlElement(name = "return")
    protected GetWinningBidByIdResponseResponse _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link GetWinningBidByIdResponseResponse }
     *     
     */
    public GetWinningBidByIdResponseResponse getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetWinningBidByIdResponseResponse }
     *     
     */
    public void setReturn(GetWinningBidByIdResponseResponse value) {
        this._return = value;
    }

}
