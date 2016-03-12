package webservice.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Stores basic information about a given transaction
 * 
 * @author dario
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class TransactionServiceBean implements Serializable {

	private static final long serialVersionUID = -8306766162055869071L;

	/**
	 * Holds the transaction amount
	 */
	@XmlElement
	private double amount;
	/**
	 * Holds the transaction type
	 */
	@XmlElement
	private String type;
	/**
	 * Holds the identifier for the parent transaction (if present)
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
