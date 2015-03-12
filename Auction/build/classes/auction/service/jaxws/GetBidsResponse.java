
package auction.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getBidsResponse", namespace = "http://auction.facadeservice/jaxws/auctionservice")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBidsResponse", namespace = "http://auction.facadeservice/jaxws/auctionservice")
public class GetBidsResponse {

    @XmlElement(name = "return", namespace = "")
    private auction.service.response.GetBidsByIdLotResponse _return;

    /**
     * 
     * @return
     *     returns GetBidsByIdLotResponse
     */
    public auction.service.response.GetBidsByIdLotResponse getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(auction.service.response.GetBidsByIdLotResponse _return) {
        this._return = _return;
    }

}
