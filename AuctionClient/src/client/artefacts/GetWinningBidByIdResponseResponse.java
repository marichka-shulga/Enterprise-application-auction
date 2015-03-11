
package client.artefacts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getWinningBidByIdResponseResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getWinningBidByIdResponseResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://auction.facadeservice/jaxws/auctionservice}baseResponse">
 *       &lt;sequence>
 *         &lt;element name="winnindBid" type="{http://auction.facadeservice/jaxws/auctionservice}bid" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getWinningBidByIdResponseResponse", propOrder = {
    "winnindBid"
})
public class GetWinningBidByIdResponseResponse
    extends BaseResponse
{

    protected Bid winnindBid;

    /**
     * Gets the value of the winnindBid property.
     * 
     * @return
     *     possible object is
     *     {@link Bid }
     *     
     */
    public Bid getWinnindBid() {
        return winnindBid;
    }

    /**
     * Sets the value of the winnindBid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bid }
     *     
     */
    public void setWinnindBid(Bid value) {
        this.winnindBid = value;
    }

}
