package com.epam.apartmentbooking.dao;

import com.epam.apartmentbooking.entity.CriteriaTimeBookingEntity;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Serge_Kozik on 4/13/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-dao-test.xml"})
public class BookingDaoImplTest {

    @Autowired
    private BookingDao bookingDao;

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
    public void checkBookedBefore() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CriteriaTimeBookingEntity criteria;
        try {
            criteria = new CriteriaTimeBookingEntity(dateFormat.parse("2017-08-10"),dateFormat.parse("2017-08-15"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        List<Integer> apartmentIds = bookingDao.returnBusyApartmentIdByCriteria(criteria);
        assertTrue("Booking is not found.",apartmentIds.contains(new Integer(1)));
    }

    @Test
    public void checkBookedAfter() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CriteriaTimeBookingEntity criteria;
        try {
            criteria = new CriteriaTimeBookingEntity(dateFormat.parse("2017-08-27"),dateFormat.parse("2017-09-05"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        List<Integer> apartmentIds = bookingDao.returnBusyApartmentIdByCriteria(criteria);
        assertTrue("Booking is not found.",apartmentIds.contains(new Integer(3)));
    }

    @Test
    public void checkBookedDuring() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CriteriaTimeBookingEntity criteria;
        try {
            criteria = new CriteriaTimeBookingEntity(dateFormat.parse("2017-08-01"),dateFormat.parse("2017-10-05"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        List<Integer> apartmentIds = bookingDao.returnBusyApartmentIdByCriteria(criteria);
        assertTrue("Booking is not found.",(apartmentIds.contains(new Integer(3))&apartmentIds.contains(new Integer(1))));
    }
}
