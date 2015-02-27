
package auction.webservice.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the auction.webservice.client package. 
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
    private final static QName _LoadAllLots_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "loadAllLots");
    private final static QName _LoadAllLotsResponse_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "loadAllLotsResponse");
    private final static QName _AddLotResponse_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "addLotResponse");
    private final static QName _UserRegistrationResponse_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "userRegistrationResponse");
    private final static QName _AddBidResponse_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "addBidResponse");
    private final static QName _FirstLoadSystemResponse_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "firstLoadSystemResponse");
    private final static QName _FirstLoadSystem_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "firstLoadSystem");
    private final static QName _AddLot_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "addLot");
    private final static QName _ReleaseResourceResponse_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "releaseResourceResponse");
    private final static QName _UserRegistration_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "userRegistration");
    private final static QName _AddBid_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "addBid");
    private final static QName _UserAuthenticationResponse_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "userAuthenticationResponse");
    private final static QName _UserAuthentication_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "userAuthentication");
    private final static QName _ReleaseResource_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "releaseResource");
    private final static QName _CancelLotResponse_QNAME = new QName("http://auction.facadeservice/jaxws/auctionservice", "cancelLotResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: auction.webservice.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UserRegistrationResponse }
     * 
     */
    public UserRegistrationResponse createUserRegistrationResponse() {
        return new UserRegistrationResponse();
    }

    /**
     * Create an instance of {@link FirstLoadSystem }
     * 
     */
    public FirstLoadSystem createFirstLoadSystem() {
        return new FirstLoadSystem();
    }

    /**
     * Create an instance of {@link AddBidResponse }
     * 
     */
    public AddBidResponse createAddBidResponse() {
        return new AddBidResponse();
    }

    /**
     * Create an instance of {@link FirstLoadSystemResponse }
     * 
     */
    public FirstLoadSystemResponse createFirstLoadSystemResponse() {
        return new FirstLoadSystemResponse();
    }

    /**
     * Create an instance of {@link AddLot }
     * 
     */
    public AddLot createAddLot() {
        return new AddLot();
    }

    /**
     * Create an instance of {@link ReleaseResourceResponse }
     * 
     */
    public ReleaseResourceResponse createReleaseResourceResponse() {
        return new ReleaseResourceResponse();
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
     * Create an instance of {@link ReleaseResource }
     * 
     */
    public ReleaseResource createReleaseResource() {
        return new ReleaseResource();
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
     * Create an instance of {@link LoadAllLots }
     * 
     */
    public LoadAllLots createLoadAllLots() {
        return new LoadAllLots();
    }

    /**
     * Create an instance of {@link LoadAllLotsResponse }
     * 
     */
    public LoadAllLotsResponse createLoadAllLotsResponse() {
        return new LoadAllLotsResponse();
    }

    /**
     * Create an instance of {@link AddLotResponse }
     * 
     */
    public AddLotResponse createAddLotResponse() {
        return new AddLotResponse();
    }

    /**
     * Create an instance of {@link Lot }
     * 
     */
    public Lot createLot() {
        return new Lot();
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
     * Create an instance of {@link Timestamp }
     * 
     */
    public Timestamp createTimestamp() {
        return new Timestamp();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadAllLots }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://auction.facadeservice/jaxws/auctionservice", name = "loadAllLots")
    public JAXBElement<LoadAllLots> createLoadAllLots(LoadAllLots value) {
        return new JAXBElement<LoadAllLots>(_LoadAllLots_QNAME, LoadAllLots.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadAllLotsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://auction.facadeservice/jaxws/auctionservice", name = "loadAllLotsResponse")
    public JAXBElement<LoadAllLotsResponse> createLoadAllLotsResponse(LoadAllLotsResponse value) {
        return new JAXBElement<LoadAllLotsResponse>(_LoadAllLotsResponse_QNAME, LoadAllLotsResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link FirstLoadSystemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://auction.facadeservice/jaxws/auctionservice", name = "firstLoadSystemResponse")
    public JAXBElement<FirstLoadSystemResponse> createFirstLoadSystemResponse(FirstLoadSystemResponse value) {
        return new JAXBElement<FirstLoadSystemResponse>(_FirstLoadSystemResponse_QNAME, FirstLoadSystemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FirstLoadSystem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://auction.facadeservice/jaxws/auctionservice", name = "firstLoadSystem")
    public JAXBElement<FirstLoadSystem> createFirstLoadSystem(FirstLoadSystem value) {
        return new JAXBElement<FirstLoadSystem>(_FirstLoadSystem_QNAME, FirstLoadSystem.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ReleaseResourceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://auction.facadeservice/jaxws/auctionservice", name = "releaseResourceResponse")
    public JAXBElement<ReleaseResourceResponse> createReleaseResourceResponse(ReleaseResourceResponse value) {
        return new JAXBElement<ReleaseResourceResponse>(_ReleaseResourceResponse_QNAME, ReleaseResourceResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ReleaseResource }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://auction.facadeservice/jaxws/auctionservice", name = "releaseResource")
    public JAXBElement<ReleaseResource> createReleaseResource(ReleaseResource value) {
        return new JAXBElement<ReleaseResource>(_ReleaseResource_QNAME, ReleaseResource.class, null, value);
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
