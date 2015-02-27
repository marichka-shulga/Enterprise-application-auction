
package auction.facadeservice.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "userAuthenticationResponse", namespace = "http://auction.facadeservice/jaxws/auctionservice")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userAuthenticationResponse", namespace = "http://auction.facadeservice/jaxws/auctionservice")
public class UserAuthenticationResponse {

    @XmlElement(name = "return", namespace = "")
    private auction.model.User _return;

    /**
     * 
     * @return
     *     returns User
     */
    public auction.model.User getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(auction.model.User _return) {
        this._return = _return;
    }

}
