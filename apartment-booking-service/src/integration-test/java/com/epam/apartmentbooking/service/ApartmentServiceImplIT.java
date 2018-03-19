package com.epam.apartmentbooking.service;

import com.epam.apartmentbooking.bean.ApartmentViewBean;
import com.epam.apartmentbooking.bean.CriteriaApartmentBean;
import com.epam.apartmentbooking.bean.CriteriaOwnerBean;
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
 * Created by Serge_Kozik on 4/17/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-service-integration-test.xml"})
public class ApartmentServiceImplIT {

    @Autowired
    private ApartmentService apartmentService;

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
    public void setDatabase() {
        POPULATOR.setSeparator(";/");
        DatabasePopulatorUtils.execute(POPULATOR,dataSource);
    }

    @Test
    public void filterAvailableCountry() {
        CriteriaOwnerBean criteriaOwner = new CriteriaOwnerBean(null,"Spain");
        CriteriaApartmentBean criteriaApartment = new CriteriaApartmentBean(criteriaOwner,null,null,null,null,null);
        List<ApartmentViewBean> apartmentList = apartmentService.filterAvailable(criteriaApartment);
        List<String> titles = new ArrayList<>(apartmentList.size());
        for (ApartmentViewBean apartment:apartmentList) {
            titles.add(apartment.getOwner().getTitle());
        }
        assertTrue("Wrong results selected by owner address mask.",(titles.size()==2)
                &&(titles.contains("Fantastic treehut between old oaks"))
                &&(titles.contains("Loft in historic Cadiz")) );
    }
}
