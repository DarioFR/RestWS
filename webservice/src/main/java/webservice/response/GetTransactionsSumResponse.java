package webservice.response;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Returns the sum of all transactions linked by their parent_id to the transaction_id
 * 
 * @author dario
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class GetTransactionsSumResponse extends GenericResponse implements Serializable {

	private static final long serialVersionUID = -4200464722201612769L;

	/**
	 * Holds the sum of the transactions
	 */
	@XmlElement
	private double sum;

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

}
