package webservice.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Stores transactions in memory
 * 
 * @author dario
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class StoreTransactionResponse extends GenericResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -227797037476755861L;
	
	/**
	 * The response status
	 */
	@XmlElement
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
