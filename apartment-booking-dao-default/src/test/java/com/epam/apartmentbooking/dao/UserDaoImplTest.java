package com.epam.apartmentbooking.dao;

import com.epam.apartmentbooking.entity.UserEntity;
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

import static org.junit.Assert.assertEquals;

/**
 * Created by Serge_Kozik on 4/5/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-dao-test.xml"})
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

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
    public void findNull() {
        UserEntity userEntity = userDao.findOne(5);
        assertEquals("Exception with empty ResultSet",null,userEntity);
    }

    @Test
    public void findById() {
        UserEntity userEntity = userDao.findOne(1);
        assertEquals("Returns wrong user in userDao.findOne","Bob",userEntity.getNick());
    }

    @Test
    public void findByEmailUser() {
        UserEntity userEntity = userDao.findByEmail("angela@angela.com");
        assertEquals("Returns wrong user in userDao.findByEmail","Angela",userEntity.getNick());
    }

    @Test
    public void findByEmailAndPassword() {
        UserEntity userEntity = userDao.findByEmailAndPassword("bob@bob.com","Bob1");
        assertEquals("Returns wrong user in userDao.findByEmailAndPassword","Bob",userEntity.getNick());
    }

    @Test
    public void findByNickAndPassword() {
        UserEntity userEntity = userDao.findByNickAndPassword("Givi","Givi1");
        assertEquals("Returns wrong user in userDao.findByNickAndPassword","givi@givi.com",userEntity.getEmail());
    }

    @Test
    public void saveUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setNick("Juan");
        userEntity.setEmail("juan@juan.com");
        userEntity.setPassword("Juan1");
        UserEntity savedEntity = userDao.save(userEntity);
        assertEquals("Returns wrong saved entity in userDao.save",userEntity.getNick(),savedEntity.getNick());
        UserEntity returnedEntity = userDao.findByEmail("juan@juan.com");
        assertEquals("Returns wrong entity from findOne after userDao.save",userEntity.getNick(),returnedEntity.getNick());
    }

}
