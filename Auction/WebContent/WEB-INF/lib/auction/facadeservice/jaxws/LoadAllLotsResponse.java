
package auction.facadeservice.jaxws;

import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "loadAllLotsResponse", namespace = "http://auction.facadeservice/jaxws/auctionservice")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "loadAllLotsResponse", namespace = "http://auction.facadeservice/jaxws/auctionservice")
public class LoadAllLotsResponse {

    @XmlElement(name = "return", namespace = "")
    private Set<auction.model.Lot> _return;

    /**
     * 
     * @return
     *     returns Set<Lot>
     */
    public Set<auction.model.Lot> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(Set<auction.model.Lot> _return) {
        this._return = _return;
    }

}
