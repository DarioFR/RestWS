package webservice.response;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Response that returns a list of transaction ids that have the same type
 * 
 * @author dario
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class GetTransactionByTypeResponse extends GenericResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1740611852081025602L;
	
	/**
	 * Holds a list of transactions ids
	 */
	@XmlElement
	private List<Long> transactionsIds;

	public List<Long> getTransactionsIds() {
		return transactionsIds;
	}

	public void setTransactionsIds(List<Long> transactionsIds) {
		this.transactionsIds = transactionsIds;
	}
}
