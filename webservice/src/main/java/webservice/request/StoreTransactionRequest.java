package webservice.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Request used to store a transaction in memory
 * 
 * @author dario
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class StoreTransactionRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1047159186547067580L;
	
	/**
	 * It holds the transaction amount
	 */
	@XmlElement
	private double amount;
	/**
	 * It holds the transaction type
	 */
	@XmlElement
	@NotNull
	private String type;
	/**
	 * It holds the parent transaction id (if present)
	 */
	@XmlElement
	private Long parent_id;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

}
