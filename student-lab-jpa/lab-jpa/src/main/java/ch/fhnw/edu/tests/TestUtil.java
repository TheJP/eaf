package ch.fhnw.edu.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.HibernateException;
import org.hibernate.internal.SessionImpl;
import org.xml.sax.InputSource;

public class TestUtil {

	private static final String TEST_DATA_FILE = "src/main/resources/dataset.xml";

	public static void resetData(EntityManagerFactory emf) throws Exception {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		IDatabaseConnection connection = getConnection(em);
		IDataSet dataSet = getDataSet();
		
		try {
			DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
		} finally {
			em.getTransaction().commit();
			connection.close();
			em.close();
		}
	}

	public static void deleteData(EntityManagerFactory emf) throws Exception {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		IDatabaseConnection connection = getConnection(em);
		IDataSet dataSet = getDataSet();
		
		try {
			DatabaseOperation.DELETE_ALL.execute(connection, dataSet);
		} finally {
			em.getTransaction().commit();
			connection.close();
			em.close();
		}
	}

	private static IDatabaseConnection getConnection(EntityManager em) {
		try {
			DatabaseConnection connection = new DatabaseConnection(((SessionImpl) (em.getDelegate())).connection());
			connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());
			return connection;
		} catch (HibernateException | DatabaseUnitException e) {
			throw new RuntimeException(e);
		}
	}

	private static IDataSet getDataSet() throws IOException, DataSetException {
		InputStream stream = new FileInputStream(TEST_DATA_FILE);
		
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		IDataSet dataSet = builder.build(new InputSource(stream));
		return dataSet;
	}
	
}


