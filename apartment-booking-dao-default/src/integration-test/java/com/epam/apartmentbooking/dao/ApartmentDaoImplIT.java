package com.epam.apartmentbooking.dao;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Serge_Kozik on 4/20/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-dao-integration-test.xml"})
public class ApartmentDaoImplIT {

    @Autowired
    private ApartmentDao apartmentDao;

    @Autowired
    private DataSource dataSource;

    private final ResourceDatabasePopulator POPULATOR = new ResourceDatabasePopulator(new ClassPathResource("sql_script_create_integration_test.sql"),
            new ClassPathResource("sql_script_insert_integration_test.sql"));

    @BeforeClass
    public static void suppressOracleDriverBug() {
        System.setProperty("javax.xml.parsers.DocumentBuilderFactory","com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
        System.setProperty("javax.xml.parsers.SAXParserFactory","com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
        System.setProperty("javax.xml.transform.TransformerFactory","com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");
    }

    @Before
    public void setDatabase()
    {
        POPULATOR.setSeparator(";/");
        DatabasePopulatorUtils.execute(POPULATOR,dataSource);
    }

    @Test
    public void returnIdByOwnerIdTest() {
        List<Integer> ownerIds= new ArrayList<>(3);
        ownerIds.add(2);
        ownerIds.add(4);
        ownerIds.add(6);
        List<Integer> apartmentIds = apartmentDao.returnIdByOwnerId(ownerIds);
        assertTrue("Wrong list of apartment ids, got by owner ids",
                (apartmentIds.size()==3)
                &&(apartmentIds.contains(2))
                &&(apartmentIds.contains(4))
                &&(apartmentIds.contains(6)));
    }
}
