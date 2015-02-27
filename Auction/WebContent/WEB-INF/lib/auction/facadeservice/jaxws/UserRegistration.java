
package auction.facadeservice.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "userRegistration", namespace = "http://auction.facadeservice/jaxws/auctionservice")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userRegistration", namespace = "http://auction.facadeservice/jaxws/auctionservice")
public class UserRegistration {

    @XmlElement(name = "arg0", namespace = "")
    private auction.model.User arg0;

    /**
     * 
     * @return
     *     returns User
     */
    public auction.model.User getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(auction.model.User arg0) {
        this.arg0 = arg0;
    }

}
