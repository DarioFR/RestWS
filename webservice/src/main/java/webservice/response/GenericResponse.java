package webservice.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import webservice.beans.ResponseResult;

/**
 * Generic class for service responses
 * 
 * @author dario
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class GenericResponse implements Serializable {

	private static final long serialVersionUID = -7803891191184255045L;
	
	/**
	 * Holds the response result
	 */
	@XmlElement
	private ResponseResult result;

	public ResponseResult getResult() {
		return result;
	}

	public void setResult(ResponseResult result) {
		this.result = result;
	}

}
