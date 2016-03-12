package webservice;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import webservice.beans.DetailedTransactionServiceBean;
import webservice.beans.TransactionServiceBean;
import webservice.request.StoreTransactionRequest;
import webservice.response.GetTransactionByTypeResponse;
import webservice.response.GetTransactionResponse;
import webservice.response.GetTransactionsSumResponse;
import webservice.response.StoreTransactionResponse;
import webservice.utils.ResponseCodes;
import webservice.utils.TransactionsServiceUtils;

public class TransactionsService implements ITransactionsService {

	@Context
	ServletContext context;
	private List<DetailedTransactionServiceBean> storedTransactions;

	public StoreTransactionResponse storeTransaction(long transactionId, StoreTransactionRequest request) {

		initStoredTransactions();

		boolean isTransactionPresent = TransactionsServiceUtils.isTransactionIdPresent(storedTransactions,
				transactionId);
		StoreTransactionResponse response = new StoreTransactionResponse();

		if (isTransactionPresent) {
			response.setResult(TransactionsServiceUtils
					.buildKOResponseResultWithMessage("The specified transaction is already present"));
			response.setStatus(ResponseCodes.KO.name());
			return response;
		}
		DetailedTransactionServiceBean transaction = new DetailedTransactionServiceBean();

		if (request.getParent_id() == null) {
			transaction.setAmount(request.getAmount());
			transaction.setType(request.getType());
			transaction.setTransactionId(transactionId);
			storedTransactions.add(transaction);
		} else {
			// it is a child. We need to find its father.
			boolean found = false;
			for (DetailedTransactionServiceBean tx : storedTransactions) {
				if (tx.getTransactionId() == request.getParent_id()) {
					found = true;
					transaction.setAmount(request.getAmount());
					transaction.setParent_id(request.getParent_id());
					transaction.setType(request.getType());
					transaction.setTransactionId(transactionId);
					if (tx.getChildrenTransactions() != null) {
						tx.getChildrenTransactions().add(transaction);
					} else {
						tx.setChildrenTransactions(new ArrayList<DetailedTransactionServiceBean>());
						tx.getChildrenTransactions().add(transaction);
					}
					break;
				}
			}
			if (!found) {
				response.setResult(TransactionsServiceUtils.buildKOResponseResultWithMessage(
						"Cannot find the parent transaction for the specified child transaction"));
				response.setStatus(ResponseCodes.KO.name());
				return response;
			}

		}
		response.setResult(TransactionsServiceUtils.buildSuccessfulResponseResult());
		response.setStatus(ResponseCodes.OK.name());
		return response;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public GetTransactionResponse getTransaction(long transactionId) {
		GetTransactionResponse response = new GetTransactionResponse();

		if (!transactionsPresentInMemory()) {
			response.setResult(
					TransactionsServiceUtils.buildKOResponseResultWithMessage("No transactions found in memory"));
			return response;
		}

		storedTransactions = (List<DetailedTransactionServiceBean>) context.getAttribute("storedTransactions");
		boolean found = false;
		TransactionServiceBean transaction = new TransactionServiceBean();
		external: for (DetailedTransactionServiceBean tx : storedTransactions) {
			if (tx.getTransactionId() == transactionId) {
				found = true;
				transaction = TransactionsServiceUtils.buildTransactionServiceBean(tx);
				break external;
			}
			if (tx.getChildrenTransactions() != null) {
				internal: for (DetailedTransactionServiceBean dtsb : tx.getChildrenTransactions()) {
					if (dtsb.getTransactionId() == transactionId) {
						found = true;
						transaction = TransactionsServiceUtils.buildTransactionServiceBean(dtsb);
						break external;
					}
				}
			}
		}
		if (found) {
			response.setResult(TransactionsServiceUtils.buildSuccessfulResponseResult());
			response.setTransaction(transaction);
			return response;
		} else {
			response.setResult(TransactionsServiceUtils
					.buildKOResponseResultWithMessage("No transaction found with the specified id"));
			return response;
		}
	}

	@SuppressWarnings("unchecked")
	public GetTransactionByTypeResponse getTransactionsByType(String type) {
		GetTransactionByTypeResponse response = new GetTransactionByTypeResponse();

		if (!transactionsPresentInMemory()) {
			response.setResult(
					TransactionsServiceUtils.buildKOResponseResultWithMessage("No transactions found in memory"));
			return response;
		}
		storedTransactions = (List<DetailedTransactionServiceBean>) context.getAttribute("storedTransactions");

		List<Long> transactionIds = new ArrayList<>();

		for (DetailedTransactionServiceBean tr : storedTransactions) {
			if (tr.getType().equals(type)) {
				transactionIds.add(tr.getTransactionId());
			}
			if (tr.getChildrenTransactions() != null) {
				for (DetailedTransactionServiceBean dtr : tr.getChildrenTransactions()) {
					if (dtr.getType().equals(type)) {
						transactionIds.add(dtr.getTransactionId());
					}
				}
			}
		}

		response.setResult(TransactionsServiceUtils.buildSuccessfulResponseResult());
		response.setTransactionsIds(transactionIds);
		return response;
	}

	@SuppressWarnings("unchecked")
	public GetTransactionsSumResponse getTransactionsSum(long transactionId) {
		GetTransactionsSumResponse response = new GetTransactionsSumResponse();

		if (!transactionsPresentInMemory()) {
			response.setResult(
					TransactionsServiceUtils.buildKOResponseResultWithMessage("No transactions found in memory"));
			return response;
		}
		storedTransactions = (List<DetailedTransactionServiceBean>) context.getAttribute("storedTransactions");

		if (!TransactionsServiceUtils.isTransactionIdPresent(storedTransactions, transactionId)) {
			response.setResult(TransactionsServiceUtils
					.buildKOResponseResultWithMessage("Cannot find transaction with the specified Id"));
			return response;
		}

		double sum = 0;
		external:for (DetailedTransactionServiceBean tr : storedTransactions) {
			if (tr.getTransactionId() == transactionId) {
				sum += tr.getAmount();
				if (tr.getChildrenTransactions() != null) {
					for (DetailedTransactionServiceBean dtsb : tr.getChildrenTransactions()) {
						sum += dtsb.getAmount();
					}
				}
				break external;
			}else{
				for(DetailedTransactionServiceBean dtsbChild:tr.getChildrenTransactions()){
					if(dtsbChild.getTransactionId()==transactionId){
						sum=dtsbChild.getAmount();
						break external;
					}
				}
				
			}

		}

		response.setResult(TransactionsServiceUtils.buildSuccessfulResponseResult());
		response.setSum(sum);
		return response;
	}

	@SuppressWarnings("unchecked")
	private boolean transactionsPresentInMemory() {
		if (context.getAttribute("storedTransactions") == null
				|| ((List<DetailedTransactionServiceBean>) context.getAttribute("storedTransactions")).isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	private void initStoredTransactions() {
		if (context.getAttribute("storedTransactions") == null) {
			storedTransactions = new ArrayList<>();
			context.setAttribute("storedTransactions", storedTransactions);
		} else {
			storedTransactions = (List<DetailedTransactionServiceBean>) context.getAttribute("storedTransactions");
		}
	}

	public List<DetailedTransactionServiceBean> getStoredTransactions() {
		return storedTransactions;
	}

	public void setStoredTransactions(List<DetailedTransactionServiceBean> storedTransactions) {
		this.storedTransactions = storedTransactions;
	}

}
