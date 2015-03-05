
package client.artefacts;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the client.artefacts package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CancelLot_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "cancelLot");
    private final static QName _Lot_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "lot");
    private final static QName _GetLot_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "getLot");
    private final static QName _GetAllLotsResponse_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "getAllLotsResponse");
    private final static QName _User_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "user");
    private final static QName _AddLotResponse_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "addLotResponse");
    private final static QName _Bid_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "bid");
    private final static QName _GetLotResponse_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "getLotResponse");
    private final static QName _UserRegistrationResponse_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "userRegistrationResponse");
    private final static QName _AddBidResponse_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "addBidResponse");
    private final static QName _AddLot_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "addLot");
    private final static QName _UserRegistration_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "userRegistration");
    private final static QName _GetAllLots_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "getAllLots");
    private final static QName _AddBid_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "addBid");
    private final static QName _UserAuthenticationResponse_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "userAuthenticationResponse");
    private final static QName _UserAuthentication_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "userAuthentication");
    private final static QName _CancelLotResponse_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "cancelLotResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: client.artefacts
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetLotResponse }
     * 
     */
    public GetLotResponse createGetLotResponse() {
        return new GetLotResponse();
    }

    /**
     * Create an instance of {@link UserRegistrationResponse }
     * 
     */
    public UserRegistrationResponse createUserRegistrationResponse() {
        return new UserRegistrationResponse();
    }

    /**
     * Create an instance of {@link AddBidResponse }
     * 
     */
    public AddBidResponse createAddBidResponse() {
        return new AddBidResponse();
    }

    /**
     * Create an instance of {@link AddLot }
     * 
     */
    public AddLot createAddLot() {
        return new AddLot();
    }

    /**
     * Create an instance of {@link GetAllLots }
     * 
     */
    public GetAllLots createGetAllLots() {
        return new GetAllLots();
    }

    /**
     * Create an instance of {@link UserRegistration }
     * 
     */
    public UserRegistration createUserRegistration() {
        return new UserRegistration();
    }

    /**
     * Create an instance of {@link UserAuthentication }
     * 
     */
    public UserAuthentication createUserAuthentication() {
        return new UserAuthentication();
    }

    /**
     * Create an instance of {@link AddBid }
     * 
     */
    public AddBid createAddBid() {
        return new AddBid();
    }

    /**
     * Create an instance of {@link UserAuthenticationResponse }
     * 
     */
    public UserAuthenticationResponse createUserAuthenticationResponse() {
        return new UserAuthenticationResponse();
    }

    /**
     * Create an instance of {@link CancelLotResponse }
     * 
     */
    public CancelLotResponse createCancelLotResponse() {
        return new CancelLotResponse();
    }

    /**
     * Create an instance of {@link CancelLot }
     * 
     */
    public CancelLot createCancelLot() {
        return new CancelLot();
    }

    /**
     * Create an instance of {@link Lot }
     * 
     */
    public Lot createLot() {
        return new Lot();
    }

    /**
     * Create an instance of {@link GetLot }
     * 
     */
    public GetLot createGetLot() {
        return new GetLot();
    }

    /**
     * Create an instance of {@link GetAllLotsResponse }
     * 
     */
    public GetAllLotsResponse createGetAllLotsResponse() {
        return new GetAllLotsResponse();
    }

    /**
     * Create an instance of {@link AddLotResponse }
     * 
     */
    public AddLotResponse createAddLotResponse() {
        return new AddLotResponse();
    }

    /**
     * Create an instance of {@link Bid }
     * 
     */
    public Bid createBid() {
        return new Bid();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link BaseResponse }
     * 
     */
    public BaseResponse createBaseResponse() {
        return new BaseResponse();
    }

    /**
     * Create an instance of {@link GetLotByIdResponse }
     * 
     */
    public GetLotByIdResponse createGetLotByIdResponse() {
        return new GetLotByIdResponse();
    }

    /**
     * Create an instance of {@link GetLotsResponse }
     * 
     */
    public GetLotsResponse createGetLotsResponse() {
        return new GetLotsResponse();
    }

    /**
     * Create an instance of {@link UserAuthenticResponse }
     * 
     */
    public UserAuthenticResponse createUserAuthenticResponse() {
        return new UserAuthenticResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelLot }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://auction.facadeservice/jaxws/auctionservice", name = "cancelLot")
    public JAXBElement<CancelLot> createCancelLot(CancelLot value) {
        return new JAXBElement<CancelLot>(_CancelLot_QNAME, CancelLot.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Lot }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://auction.facadeservice/jaxws/auctionservice", name = "lot")
    public JAXBElement<Lot> createLot(Lot value) {
        return new JAXBElement<Lot>(_Lot_QNAME, Lot.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLot }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://auction.facadeservice/jaxws/auctionservice", name = "getLot")
    public JAXBElement<GetLot> createGetLot(GetLot value) {
        return new JAXBElement<GetLot>(_GetLot_QNAME, GetLot.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllLotsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://auction.facadeservice/jaxws/auctionservice", name = "getAllLotsResponse")
    public JAXBElement<GetAllLotsResponse> createGetAllLotsResponse(GetAllLotsResponse value) {
        return new JAXBElement<GetAllLotsResponse>(_GetAllLotsResponse_QNAME, GetAllLotsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://auction.facadeservice/jaxws/auctionservice", name = "user")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddLotResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://auction.facadeservice/jaxws/auctionservice", name = "addLotResponse")
    public JAXBElement<AddLotResponse> createAddLotResponse(AddLotResponse value) {
        return new JAXBElement<AddLotResponse>(_AddLotResponse_QNAME, AddLotResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Bid }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://auction.facadeservice/jaxws/auctionservice", name = "bid")
    public JAXBElement<Bid> createBid(Bid value) {
        return new JAXBElement<Bid>(_Bid_QNAME, Bid.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLotResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://auction.facadeservice/jaxws/auctionservice", name = "getLotResponse")
    public JAXBElement<GetLotResponse> createGetLotResponse(GetLotResponse value) {
        return new JAXBElement<GetLotResponse>(_GetLotResponse_QNAME, GetLotResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserRegistrationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://auction.facadeservice/jaxws/auctionservice", name = "userRegistrationResponse")
    public JAXBElement<UserRegistrationResponse> createUserRegistrationResponse(UserRegistrationResponse value) {
        return new JAXBElement<UserRegistrationResponse>(_UserRegistrationResponse_QNAME, UserRegistrationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddBidResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://auction.facadeservice/jaxws/auctionservice", name = "addBidResponse")
    public JAXBElement<AddBidResponse> createAddBidResponse(AddBidResponse value) {
        return new JAXBElement<AddBidResponse>(_AddBidResponse_QNAME, AddBidResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddLot }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://auction.facadeservice/jaxws/auctionservice", name = "addLot")
    public JAXBElement<AddLot> createAddLot(AddLot value) {
        return new JAXBElement<AddLot>(_AddLot_QNAME, AddLot.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserRegistration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://auction.facadeservice/jaxws/auctionservice", name = "userRegistration")
    public JAXBElement<UserRegistration> createUserRegistration(UserRegistration value) {
        return new JAXBElement<UserRegistration>(_UserRegistration_QNAME, UserRegistration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllLots }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://auction.facadeservice/jaxws/auctionservice", name = "getAllLots")
    public JAXBElement<GetAllLots> createGetAllLots(GetAllLots value) {
        return new JAXBElement<GetAllLots>(_GetAllLots_QNAME, GetAllLots.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddBid }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://auction.facadeservice/jaxws/auctionservice", name = "addBid")
    public JAXBElement<AddBid> createAddBid(AddBid value) {
        return new JAXBElement<AddBid>(_AddBid_QNAME, AddBid.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserAuthenticationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://auction.facadeservice/jaxws/auctionservice", name = "userAuthenticationResponse")
    public JAXBElement<UserAuthenticationResponse> createUserAuthenticationResponse(UserAuthenticationResponse value) {
        return new JAXBElement<UserAuthenticationResponse>(_UserAuthenticationResponse_QNAME, UserAuthenticationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserAuthentication }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://auction.facadeservice/jaxws/auctionservice", name = "userAuthentication")
    public JAXBElement<UserAuthentication> createUserAuthentication(UserAuthentication value) {
        return new JAXBElement<UserAuthentication>(_UserAuthentication_QNAME, UserAuthentication.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelLotResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://auction.facadeservice/jaxws/auctionservice", name = "cancelLotResponse")
    public JAXBElement<CancelLotResponse> createCancelLotResponse(CancelLotResponse value) {
        return new JAXBElement<CancelLotResponse>(_CancelLotResponse_QNAME, CancelLotResponse.class, null, value);
    }

}
