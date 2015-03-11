
package client.artefacts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getLotStateByIdLotResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getLotStateByIdLotResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://auction.facadeservice/jaxws/auctionservice}baseResponse">
 *       &lt;sequence>
 *         &lt;element name="lotState" type="{http://auction.facadeservice/jaxws/auctionservice}lotState" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getLotStateByIdLotResponse", propOrder = {
    "lotState"
})
public class GetLotStateByIdLotResponse
    extends BaseResponse
{

    @XmlSchemaType(name = "string")
    protected LotState lotState;

    /**
     * Gets the value of the lotState property.
     * 
     * @return
     *     possible object is
     *     {@link LotState }
     *     
     */
    public LotState getLotState() {
        return lotState;
    }

    /**
     * Sets the value of the lotState property.
     * 
     * @param value
     *     allowed object is
     *     {@link LotState }
     *     
     */
    public void setLotState(LotState value) {
        this.lotState = value;
    }

}
