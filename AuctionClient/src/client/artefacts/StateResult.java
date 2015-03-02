
package client.artefacts;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for stateResult.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="stateResult">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ERROR"/>
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="NOT_SUCCESS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "stateResult")
@XmlEnum
public enum StateResult {

    ERROR,
    SUCCESS,
    NOT_SUCCESS;

    public String value() {
        return name();
    }

    public static StateResult fromValue(String v) {
        return valueOf(v);
    }

}
