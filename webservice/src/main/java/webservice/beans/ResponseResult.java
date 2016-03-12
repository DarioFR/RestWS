package webservice.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Holds info about the response outcome
 * 
 * @author dario
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class ResponseResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5699074101286814376L;
	
	/**
	 * The result message
	 */
	@XmlElement
	private String resultMessage;
	/**
	 * The result code
	 */
	@XmlElement
	private String resultCode;

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
}
