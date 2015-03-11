
package client.artefacts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getFinishTradesResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getFinishTradesResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://auction.facadeservice/jaxws/auctionservice}baseResponse">
 *       &lt;sequence>
 *         &lt;element name="state" type="{http://auction.facadeservice/jaxws/auctionservice}lotState" minOccurs="0"/>
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
@XmlType(name = "getFinishTradesResponse", propOrder = {
    "state",
    "winnindBid"
})
public class GetFinishTradesResponse
    extends BaseResponse
{

    @XmlSchemaType(name = "string")
    protected LotState state;
    protected Bid winnindBid;

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link LotState }
     *     
     */
    public LotState getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link LotState }
     *     
     */
    public void setState(LotState value) {
        this.state = value;
    }

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
