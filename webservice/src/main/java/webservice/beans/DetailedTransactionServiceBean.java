package webservice.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * It stores detailed information about a given transaction
 * 
 * @author Dario 
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class DetailedTransactionServiceBean extends TransactionServiceBean implements Serializable {

	private static final long serialVersionUID = -7505130555334922403L;
	
	/**
	 * Holds the transaction Id
	 */
	@XmlElement
	private long transactionId;
	
	/**
	 * Holds a list of children transactions
	 */
	@XmlElement
	private List<DetailedTransactionServiceBean> childrenTransactions;



	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public List<DetailedTransactionServiceBean> getChildrenTransactions() {
		return childrenTransactions;
	}

	public void setChildrenTransactions(List<DetailedTransactionServiceBean> childrenTransactions) {
		this.childrenTransactions = childrenTransactions;
	}

}
