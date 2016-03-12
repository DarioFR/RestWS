package webservice;


import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import webservice.request.StoreTransactionRequest;
import webservice.response.GetTransactionByTypeResponse;
import webservice.response.GetTransactionResponse;
import webservice.response.GetTransactionsSumResponse;
import webservice.response.StoreTransactionResponse;

/**
 * Interface used to store transactions, retrieve transactions with a specified id,
 * get the transactions of a given type and return the total amount involved for all 
 * transactions linked to a parent one.
 * 
 * @author Dario 
 *
 */
@Path("transactionservice")
public interface ITransactionsService {
	
	/**
	 * It stores information regarding a specific transaction (can be a parent or child transaction)
	 * 
	 * @param  transactionId, StoreTransactionRequest
	 * @return StoreTransactionResponse
	 */
	@PUT
	@Path("transaction/{transactionId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public StoreTransactionResponse storeTransaction(@PathParam("transactionId") long transactionId,@Valid StoreTransactionRequest request);
	
	/**
	 * It retrieves information about a specific transaction given its id
	 * 
	 * @param transactionId
	 * @return GetTransactionResponse
	 */
	@GET
	@Path("transaction/{transactionId}")
	@Produces(MediaType.APPLICATION_JSON)
	public GetTransactionResponse getTransaction(@PathParam("transactionId") long transactionId);
	
	/**
	 * It retrieves a list of transaction ids sharing the same type
	 * 
	 * @param type
	 * @return GetTransactionByTypeResponse 
	 */
	@GET
	@Path("types/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public GetTransactionByTypeResponse getTransactionsByType(@PathParam("type") String type);
	
	/**
	 * It returns the sum of all transactions that are transitively linked by their parent_id to 
	 * the transactionId
	 * 
	 * @param transactionId
	 * @return GetTransactionsSumResponse
	 */
	@GET
	@Path("sum/{transactionId}")
	@Produces(MediaType.APPLICATION_JSON)
	public GetTransactionsSumResponse getTransactionsSum(@PathParam("transactionId") long transactionId);
}
