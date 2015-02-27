
package auction.facadeservice.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "addBid", namespace = "http://auction.facadeservice/jaxws/auctionservice")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addBid", namespace = "http://auction.facadeservice/jaxws/auctionservice", propOrder = {
    "arg0",
    "arg1"
})
public class AddBid {

    @XmlElement(name = "arg0", namespace = "")
    private auction.model.Lot arg0;
    @XmlElement(name = "arg1", namespace = "")
    private auction.model.Bid arg1;

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

    /**
     * 
     * @return
     *     returns Bid
     */
    public auction.model.Bid getArg1() {
        return this.arg1;
    }

    /**
     * 
     * @param arg1
     *     the value for the arg1 property
     */
    public void setArg1(auction.model.Bid arg1) {
        this.arg1 = arg1;
    }

}
