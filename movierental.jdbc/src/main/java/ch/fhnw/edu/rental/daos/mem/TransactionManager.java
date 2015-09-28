package ch.fhnw.edu.rental.daos.mem;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

public class TransactionManager implements PlatformTransactionManager {

	@Autowired
	private ApplicationContext context;

	public void resetData() {
		String[] daos = new String[]{"priceCategoryDao", "userDao", "movieDao", "rentalDao"};
			// order in which the DAOs are initialized is relevant
		for(String bean : daos){
			Object dao = context.getBean(bean);
			Method method;
			try {
				method = dao.getClass().getDeclaredMethod("initData");
				method.setAccessible(true);
				method.invoke(dao);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public TransactionStatus getTransaction(TransactionDefinition definition)
			throws TransactionException {

		return new TransactionStatus() {

			@Override
			public Object createSavepoint() throws TransactionException {
				throw new UnsupportedOperationException();
			}

			@Override
			public void rollbackToSavepoint(Object savepoint)
					throws TransactionException {
				throw new UnsupportedOperationException();
			}

			@Override
			public void releaseSavepoint(Object savepoint)
					throws TransactionException {
				throw new UnsupportedOperationException();
			}

			@Override
			public boolean isNewTransaction() {
				return false;
			}

			@Override
			public boolean hasSavepoint() {
				return false;
			}

			@Override
			public void setRollbackOnly() {
			}

			@Override
			public boolean isRollbackOnly() {
				return false;
			}

			@Override
			public void flush() {
			}

			@Override
			public boolean isCompleted() {
				return false;
			}
		};
	}

	@Override
	public void commit(TransactionStatus status) throws TransactionException {
	}

	@Override
	public void rollback(TransactionStatus status) throws TransactionException {
		resetData();
	}

}
