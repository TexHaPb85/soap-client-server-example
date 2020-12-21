package edu.study.soapclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class CountryClient extends WebServiceGatewaySupport {

    public GetCountryResponse getCountry(String country) {
        GetCountryRequest request = new GetCountryRequest();
        request.setCountryName(country);

        GetCountryResponse response = (GetCountryResponse)getWebServiceTemplate().marshalSendAndReceive(request);
        return response;
    }

    public AddCountryResponse addCountry(Country country) {
        AddCountryRequest request = new AddCountryRequest();
        request.setCountry(country);

        AddCountryResponse response = (AddCountryResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response;
    }

    public RemoveCountryResponse removeCountry(String country) {
        RemoveCountryRequest request = new RemoveCountryRequest();
        request.setCountryName(country);

        RemoveCountryResponse response = (RemoveCountryResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response;
    }
}