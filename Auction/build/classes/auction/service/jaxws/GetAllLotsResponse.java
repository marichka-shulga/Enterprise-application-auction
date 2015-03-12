
package auction.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getAllLotsResponse", namespace = "http://auction.facadeservice/jaxws/auctionservice")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllLotsResponse", namespace = "http://auction.facadeservice/jaxws/auctionservice")
public class GetAllLotsResponse {

    @XmlElement(name = "return", namespace = "")
    private auction.service.response.GetLotsResponse _return;

    /**
     * 
     * @return
     *     returns GetLotsResponse
     */
    public auction.service.response.GetLotsResponse getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(auction.service.response.GetLotsResponse _return) {
        this._return = _return;
    }

}
