package com.epam.apartmentbooking.dao;

import com.epam.apartmentbooking.entity.ApartmentEntity;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.datatype.IDataTypeFactory;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.oracle.OracleDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-dao-test.xml"})
public class ApartmentDaoImplTest {

    @Autowired
    private ApartmentDao apartmentDao;

    @Autowired
    private DataSource dataSource;

    @BeforeClass
    public static void suppressOracleDriverBug() {
        System.setProperty("javax.xml.parsers.DocumentBuilderFactory","com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
        System.setProperty("javax.xml.parsers.SAXParserFactory","com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
        System.setProperty("javax.xml.transform.TransformerFactory","com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");
    }

    @Before
    public void setUp() {
        try{
            IDatabaseConnection dbConnection = new DatabaseDataSourceConnection(dataSource,"APARTMENTSTEST");
            IDataTypeFactory dataTypeFactory = new OracleDataTypeFactory();
            DatabaseConfig config = dbConnection.getConfig();
            config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,dataTypeFactory);
            IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File("./src/test/resources/test-dataset-apartment.xml"));
            IDataSet fullDataSet = new FilteredDataSet(new DatabaseSequenceFilter(dbConnection),dataSet);
            DatabaseOperation.CLEAN_INSERT.execute(dbConnection,fullDataSet);
        } catch (IOException e) {
            throw new RuntimeException("Problems with dataset file.",e);
        } catch (SQLException e2) {
            throw new RuntimeException("Problems with populating test table.",e2);
        } catch (DatabaseUnitException e3) {
            throw new RuntimeException("Problems with DBUnit.",e3);
        }
    }

    @Test
    public void findByAddress() {
        List<ApartmentEntity> apartments = apartmentDao.showByAddress("Spain");
        assertEquals("Wrong list size from ApartmentDao.showByAddress",2,apartments.size());
    }


}
