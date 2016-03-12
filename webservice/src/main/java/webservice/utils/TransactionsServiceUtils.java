package webservice.utils;

import java.util.List;
import webservice.beans.ResponseResult;
import webservice.beans.TransactionServiceBean;
import webservice.beans.DetailedTransactionServiceBean;

public class TransactionsServiceUtils {

	public static ResponseResult buildKOResponseResultWithMessage(String message) {

		ResponseResult result = new ResponseResult();
		result.setResultCode(ResponseCodes.KO.name());
		result.setResultMessage(message);

		return result;
	}

	public static ResponseResult buildSuccessfulResponseResult() {

		ResponseResult result = new ResponseResult();
		result.setResultCode(ResponseCodes.OK.name());
		result.setResultMessage("Response successful");

		return result;
	}

	@SuppressWarnings("unused")
	public static boolean isTransactionIdPresent(List<DetailedTransactionServiceBean> storedTransactions,
			long transactionId) {

		// checks whether the transaction is already present
		boolean isTransactionPresent = false;
		if (storedTransactions.isEmpty()) {
			return isTransactionPresent;
		}

		external: for (DetailedTransactionServiceBean tr : storedTransactions) {
			if (tr.getTransactionId() == transactionId) {

				isTransactionPresent = true;
				break external;
			}
			if (tr.getChildrenTransactions() != null) {
				internal: for (DetailedTransactionServiceBean trchild : tr.getChildrenTransactions()) {
					if (trchild.getTransactionId() == transactionId) {
						isTransactionPresent = true;
						break external;
					}
				}
			}
		}
		return isTransactionPresent;

	}

	public static TransactionServiceBean buildTransactionServiceBean(DetailedTransactionServiceBean tx) {
		TransactionServiceBean transaction = new TransactionServiceBean();
		transaction.setParent_id(tx.getParent_id());
		transaction.setAmount(tx.getAmount());
		transaction.setType(tx.getType());

		return transaction;
	}
}
