
package auction.service.jaxws;

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
    private auction.service.response.UserAuthenticResponse _return;

    /**
     * 
     * @return
     *     returns UserAuthenticResponse
     */
    public auction.service.response.UserAuthenticResponse getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(auction.service.response.UserAuthenticResponse _return) {
        this._return = _return;
    }

}
