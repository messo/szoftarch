
package taskmeter.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for workingSlot complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="workingSlot">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="projectID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="startTimestamp" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="stopTimestamp" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="userID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "workingSlot", propOrder = {
    "id",
    "projectID",
    "startTimestamp",
    "stopTimestamp",
    "userID"
})
public class WorkingSlot {

    protected int id;
    protected int projectID;
    protected long startTimestamp;
    protected long stopTimestamp;
    protected int userID;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the projectID property.
     * 
     */
    public int getProjectID() {
        return projectID;
    }

    /**
     * Sets the value of the projectID property.
     * 
     */
    public void setProjectID(int value) {
        this.projectID = value;
    }

    /**
     * Gets the value of the startTimestamp property.
     * 
     */
    public long getStartTimestamp() {
        return startTimestamp;
    }

    /**
     * Sets the value of the startTimestamp property.
     * 
     */
    public void setStartTimestamp(long value) {
        this.startTimestamp = value;
    }

    /**
     * Gets the value of the stopTimestamp property.
     * 
     */
    public long getStopTimestamp() {
        return stopTimestamp;
    }

    /**
     * Sets the value of the stopTimestamp property.
     * 
     */
    public void setStopTimestamp(long value) {
        this.stopTimestamp = value;
    }

    /**
     * Gets the value of the userID property.
     * 
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets the value of the userID property.
     * 
     */
    public void setUserID(int value) {
        this.userID = value;
    }

}
