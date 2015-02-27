
package auction.facadeservice.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "cancelLot", namespace = "http://auction.facadeservice/jaxws/auctionservice")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cancelLot", namespace = "http://auction.facadeservice/jaxws/auctionservice")
public class CancelLot {

    @XmlElement(name = "arg0", namespace = "")
    private auction.model.Lot arg0;

    /**
     * 
     * @return
     *     returns Lot
     */
    public auction.model.Lot getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(auction.model.Lot arg0) {
        this.arg0 = arg0;
    }

}
