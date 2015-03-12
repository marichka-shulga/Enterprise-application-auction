
package client.artefacts;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "FacadeService", targetNamespace = "http://auction.facadeservice/jaxws/auctionservice")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface FacadeService {


    /**
     * 
     * @param arg0
     * @return
     *     returns client.artefacts.BaseResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "userRegistration", targetNamespace = "http://auction.facadeservice/jaxws/auctionservice", className = "client.artefacts.UserRegistration")
    @ResponseWrapper(localName = "userRegistrationResponse", targetNamespace = "http://auction.facadeservice/jaxws/auctionservice", className = "client.artefacts.UserRegistrationResponse")
    @Action(input = "http://auction.facadeservice/jaxws/auctionservice/FacadeService/userRegistrationRequest", output = "http://auction.facadeservice/jaxws/auctionservice/FacadeService/userRegistrationResponse")
    public BaseResponse userRegistration(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0);

    /**
     * 
     * @return
     *     returns client.artefacts.GetLotsResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllLots", targetNamespace = "http://auction.facadeservice/jaxws/auctionservice", className = "client.artefacts.GetAllLots")
    @ResponseWrapper(localName = "getAllLotsResponse", targetNamespace = "http://auction.facadeservice/jaxws/auctionservice", className = "client.artefacts.GetAllLotsResponse")
    @Action(input = "http://auction.facadeservice/jaxws/auctionservice/FacadeService/getAllLotsRequest", output = "http://auction.facadeservice/jaxws/auctionservice/FacadeService/getAllLotsResponse")
    public GetLotsResponse getAllLots();

    /**
     * 
     * @param arg0
     * @return
     *     returns client.artefacts.BaseResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addLot", targetNamespace = "http://auction.facadeservice/jaxws/auctionservice", className = "client.artefacts.AddLot")
    @ResponseWrapper(localName = "addLotResponse", targetNamespace = "http://auction.facadeservice/jaxws/auctionservice", className = "client.artefacts.AddLotResponse")
    @Action(input = "http://auction.facadeservice/jaxws/auctionservice/FacadeService/addLotRequest", output = "http://auction.facadeservice/jaxws/auctionservice/FacadeService/addLotResponse")
    public BaseResponse addLot(
        @WebParam(name = "arg0", targetNamespace = "")
        Lot arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns client.artefacts.BaseResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "cancelLot", targetNamespace = "http://auction.facadeservice/jaxws/auctionservice", className = "client.artefacts.CancelLot")
    @ResponseWrapper(localName = "cancelLotResponse", targetNamespace = "http://auction.facadeservice/jaxws/auctionservice", className = "client.artefacts.CancelLotResponse")
    @Action(input = "http://auction.facadeservice/jaxws/auctionservice/FacadeService/cancelLotRequest", output = "http://auction.facadeservice/jaxws/auctionservice/FacadeService/cancelLotResponse")
    public BaseResponse cancelLot(
        @WebParam(name = "arg0", targetNamespace = "")
        Lot arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns client.artefacts.BaseResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addBid", targetNamespace = "http://auction.facadeservice/jaxws/auctionservice", className = "client.artefacts.AddBid")
    @ResponseWrapper(localName = "addBidResponse", targetNamespace = "http://auction.facadeservice/jaxws/auctionservice", className = "client.artefacts.AddBidResponse")
    @Action(input = "http://auction.facadeservice/jaxws/auctionservice/FacadeService/addBidRequest", output = "http://auction.facadeservice/jaxws/auctionservice/FacadeService/addBidResponse")
    public BaseResponse addBid(
        @WebParam(name = "arg0", targetNamespace = "")
        Bid arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns client.artefacts.GetBidsByIdLotResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getBids", targetNamespace = "http://auction.facadeservice/jaxws/auctionservice", className = "client.artefacts.GetBids")
    @ResponseWrapper(localName = "getBidsResponse", targetNamespace = "http://auction.facadeservice/jaxws/auctionservice", className = "client.artefacts.GetBidsResponse")
    @Action(input = "http://auction.facadeservice/jaxws/auctionservice/FacadeService/getBidsRequest", output = "http://auction.facadeservice/jaxws/auctionservice/FacadeService/getBidsResponse")
    public GetBidsByIdLotResponse getBids(
        @WebParam(name = "arg0", targetNamespace = "")
        Integer arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns client.artefacts.UserAuthenticResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "userAuthentication", targetNamespace = "http://auction.facadeservice/jaxws/auctionservice", className = "client.artefacts.UserAuthentication")
    @ResponseWrapper(localName = "userAuthenticationResponse", targetNamespace = "http://auction.facadeservice/jaxws/auctionservice", className = "client.artefacts.UserAuthenticationResponse")
    @Action(input = "http://auction.facadeservice/jaxws/auctionservice/FacadeService/userAuthenticationRequest", output = "http://auction.facadeservice/jaxws/auctionservice/FacadeService/userAuthenticationResponse")
    public UserAuthenticResponse userAuthentication(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

}
