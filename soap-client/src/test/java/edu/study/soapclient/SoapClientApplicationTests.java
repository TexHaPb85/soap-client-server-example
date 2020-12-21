package edu.study.soapclient;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CountryClientConfig.class, loader = AnnotationConfigContextLoader.class)
public class SoapClientApplicationTests extends AbstractTestNGSpringContextTests {

    private static final Logger LOGGER = Logger.getLogger(SoapClientApplicationTests.class);

    @Autowired
    CountryClient countryClient;

    @DataProvider
    public Object[][] countiesCapitals() {
        LOGGER.info("Capitals DataProvider prepared");
        return new Object[][]{
                {"Poland", "Warsaw"},
                {"Spain", "Madrid"},
                {"United Kingdom", "London"}
        };
    }

    @DataProvider
    public Object[][] countiesCurrency() {
        LOGGER.info("Currency DataProvider prepared");
        return new Object[][]{
                {"Poland", Currency.PLN},
                {"Spain", Currency.EUR},
                {"United Kingdom", Currency.GBP}
        };
    }

    @Test(dataProvider = "countiesCapitals")
    public void getCapitalsTest(String country, String capital) {
        GetCountryResponse response = countryClient.getCountry(country);
        Assert.assertEquals(capital, response.getCountry().getCapital());
    }

    @Test(dataProvider = "countiesCurrency")
    public void getCurrencyTest(String country, Currency currency) {
        GetCountryResponse response = countryClient.getCountry(country);
        Assert.assertEquals(currency, response.getCountry().getCurrency());
    }

    @Test
    public void addCountryTest() {
        Country country = new Country();
        country.setName("France");
        country.setCapital("Paris");
        country.setPopulation(12345);
        country.setCurrency(Currency.EUR);

        AddCountryResponse response = countryClient.addCountry(country);
        Assert.assertNotNull(response.addingStatus);
    }

    @Test
    public void removeCountryTest() {
        RemoveCountryResponse response = countryClient.removeCountry("France");
        Assert.assertNotNull(response.removingStatus);
    }
}
