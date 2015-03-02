
package client.artefacts;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for lotState.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="lotState">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ACTIVE"/>
 *     &lt;enumeration value="SOLD"/>
 *     &lt;enumeration value="NOT_SOLD"/>
 *     &lt;enumeration value="CANCELLED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "lotState")
@XmlEnum
public enum LotState {

    ACTIVE,
    SOLD,
    NOT_SOLD,
    CANCELLED;

    public String value() {
        return name();
    }

    public static LotState fromValue(String v) {
        return valueOf(v);
    }

}
