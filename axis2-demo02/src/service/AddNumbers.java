package service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "AddNubers", targetNamespace = "http://sevice.cxf/")
public interface AddNumbers {

	/**
	 * @param number1
	 * @param number2
	 * @return The sum
	 * @throws AddNumbersException
	 *             if any of the numbers to be added is negative.
	 */
	@WebMethod(operationName = "addNumbers", action = "urn:AddNumbers")
	@RequestWrapper(className = "cxf.sevice.jaxws.AddNumbers", localName = "addNumbers", targetNamespace = "http://sevice.cxf/")
	@ResponseWrapper(className = "cxf.sevice.jaxws.AddNumbersResponse", localName = "addNumbersResponse", targetNamespace = "http://sevice.cxf/")
	public int addNumbers(@WebParam(name = "arg0") int number1, @WebParam(name = "arg1") int number2) throws AddNumbersException;

}