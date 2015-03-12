
package auction.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "addBid", namespace = "http://auction.facadeservice/jaxws/auctionservice")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addBid", namespace = "http://auction.facadeservice/jaxws/auctionservice")
public class AddBid {

    @XmlElement(name = "arg0", namespace = "")
    private auction.model.Bid arg0;

    /**
     * 
     * @return
     *     returns Bid
     */
    public auction.model.Bid getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(auction.model.Bid arg0) {
        this.arg0 = arg0;
    }

}
