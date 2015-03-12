
package auction.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "cancelLotResponse", namespace = "http://auction.facadeservice/jaxws/auctionservice")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cancelLotResponse", namespace = "http://auction.facadeservice/jaxws/auctionservice")
public class CancelLotResponse {

    @XmlElement(name = "return", namespace = "")
    private auction.service.response.BaseResponse _return;

    /**
     * 
     * @return
     *     returns BaseResponse
     */
    public auction.service.response.BaseResponse getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(auction.service.response.BaseResponse _return) {
        this._return = _return;
    }

}
