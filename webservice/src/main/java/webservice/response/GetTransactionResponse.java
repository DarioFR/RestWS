package webservice.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import webservice.beans.TransactionServiceBean;

/**
 * Response that returns info about a given transaction
 * 
 * @author dario
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class GetTransactionResponse extends GenericResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8238023639914839868L;
	
	/**
	 * Holds the transaction object
	 */
	@XmlElement
	private TransactionServiceBean transaction;

	public TransactionServiceBean getTransaction() {
		return transaction;
	}

	public void setTransaction(TransactionServiceBean transaction) {
		this.transaction = transaction;
	}


}
