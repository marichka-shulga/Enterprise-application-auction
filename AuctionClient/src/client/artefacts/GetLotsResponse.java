
package client.artefacts;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getLotsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getLotsResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://auction.facadeservice/jaxws/auctionservice}baseResponse">
 *       &lt;sequence>
 *         &lt;element name="listLots" type="{http://auction.facadeservice/jaxws/auctionservice}lot" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getLotsResponse", propOrder = {
    "listLots"
})
public class GetLotsResponse
    extends BaseResponse
{

    @XmlElement(nillable = true)
    protected List<Lot> listLots;

    /**
     * Gets the value of the listLots property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listLots property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListLots().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Lot }
     * 
     * 
     */
    public List<Lot> getListLots() {
        if (listLots == null) {
            listLots = new ArrayList<Lot>();
        }
        return this.listLots;
    }

}
