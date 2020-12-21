package edu.study.soapserver;

import com.baeldung.springsoap.gen.Country;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class CountryRepository {
    private List<Country> countries = new ArrayList<>();

    @PostConstruct
    public void initData() {
        // initialize countries map
    }

    public Country findCountry(String name) {
        return countries.stream()
                .filter(country -> country.getName().equals(name))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}
