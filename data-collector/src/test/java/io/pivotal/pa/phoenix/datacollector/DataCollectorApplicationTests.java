package io.pivotal.pa.phoenix.datacollector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataCollectorApplicationTests {

	@Autowired
	@Qualifier("uaaClientCredentials")
	private ClientCredentialsResourceDetails oAuth2ProtectedResourceDetails;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testClientCredentialsResourceDetails() {
		assertThat("testClient", is(oAuth2ProtectedResourceDetails.getClientId()));
		assertThat("testSecret", is(oAuth2ProtectedResourceDetails.getClientSecret()));
		assertThat("http://localhost:10001/oauth/token", is(oAuth2ProtectedResourceDetails.getAccessTokenUri()));
	}

}
