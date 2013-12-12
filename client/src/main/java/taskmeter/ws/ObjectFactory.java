
package taskmeter.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the taskmeter.ws package. 
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

    private final static QName _ListProjectsResponse_QNAME = new QName("http://ws.taskmeter/", "listProjectsResponse");
    private final static QName _Login_QNAME = new QName("http://ws.taskmeter/", "login");
    private final static QName _ListProjects_QNAME = new QName("http://ws.taskmeter/", "listProjects");
    private final static QName _Synchronize_QNAME = new QName("http://ws.taskmeter/", "synchronize");
    private final static QName _SynchronizeResponse_QNAME = new QName("http://ws.taskmeter/", "synchronizeResponse");
    private final static QName _LoginResponse_QNAME = new QName("http://ws.taskmeter/", "loginResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: taskmeter.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SynchronizeResponse }
     * 
     */
    public SynchronizeResponse createSynchronizeResponse() {
        return new SynchronizeResponse();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link ListProjectsResponse }
     * 
     */
    public ListProjectsResponse createListProjectsResponse() {
        return new ListProjectsResponse();
    }

    /**
     * Create an instance of {@link ListProjects }
     * 
     */
    public ListProjects createListProjects() {
        return new ListProjects();
    }

    /**
     * Create an instance of {@link Synchronize }
     * 
     */
    public Synchronize createSynchronize() {
        return new Synchronize();
    }

    /**
     * Create an instance of {@link Project }
     * 
     */
    public Project createProject() {
        return new Project();
    }

    /**
     * Create an instance of {@link WorkingSlot }
     * 
     */
    public WorkingSlot createWorkingSlot() {
        return new WorkingSlot();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListProjectsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.taskmeter/", name = "listProjectsResponse")
    public JAXBElement<ListProjectsResponse> createListProjectsResponse(ListProjectsResponse value) {
        return new JAXBElement<ListProjectsResponse>(_ListProjectsResponse_QNAME, ListProjectsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.taskmeter/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListProjects }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.taskmeter/", name = "listProjects")
    public JAXBElement<ListProjects> createListProjects(ListProjects value) {
        return new JAXBElement<ListProjects>(_ListProjects_QNAME, ListProjects.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Synchronize }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.taskmeter/", name = "synchronize")
    public JAXBElement<Synchronize> createSynchronize(Synchronize value) {
        return new JAXBElement<Synchronize>(_Synchronize_QNAME, Synchronize.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SynchronizeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.taskmeter/", name = "synchronizeResponse")
    public JAXBElement<SynchronizeResponse> createSynchronizeResponse(SynchronizeResponse value) {
        return new JAXBElement<SynchronizeResponse>(_SynchronizeResponse_QNAME, SynchronizeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.taskmeter/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

}
