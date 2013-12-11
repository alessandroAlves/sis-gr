package br.com.sisgr.model;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

public class GoogleOauthHelper {

	/**
	 * CLIENT_ID da aplicação, gerada em https://code.google.com/apis/console/
	 */
	private static final String CLIENT_ID = "935693435080.apps.googleusercontent.com";
	/**
	 * CLIENT_SECRET da aplicação, gerada em
	 * https://code.google.com/apis/console/
	 */
	private static final String CLIENT_SECRET = "FuWjUN8MSxjkx5Gci_N89cE3";

	/**
	 * Callback URI que o Google redireciona após a autenticação
	 */
	private static final String CALLBACK_URI = "http://localhost:8080/sis-gr/google";

	// Início das variáveis de autenticação do goole
	private static final Iterable<String> SCOPE = Arrays
			.asList("https://www.googleapis.com/auth/userinfo.profile;https://www.googleapis.com/auth/userinfo.email"
					.split(";"));
	private static final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo";
	private JsonFactory JSON_FACTORY = new JacksonFactory();
	private HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	// fim das variáveis de autenticação

	private String stateToken;

	private GoogleAuthorizationCodeFlow flow;

	/**
	 * Construtor inicializa o Google Authorization Code Flow com o CLIENT_ID,
	 * CLIENT_SECRET e o SCOPE
	 */
	public GoogleOauthHelper()
	{
		flow = new GoogleAuthorizationCodeFlow(HTTP_TRANSPORT, 
				JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, SCOPE);
	}
	
	/**
	 * Cria a URL de login baseada no id do cliente, na chave, na callback URI e
	 * no scope
	 * 
	 * @return String
	 */
	public String buildLoginUrl() {
	    GoogleAuthorizationCodeRequestUrl url = flow
				.newAuthorizationUrl();

		return url.setRedirectUri(CALLBACK_URI).setState(generateStateToken()).build();
	}

	/**
	 * Gera um state token seguro
	 */
	private String generateStateToken() {
		SecureRandom sr1 = new SecureRandom();

		stateToken = "google;" + sr1.nextInt();
		
		return stateToken;
	}

	/**
	 * Acess0 ao state token
	 * 
	 * @return String
	 */
	public String getStateToken() {
		return stateToken;
	}

	/**
	 * Recebe um Authentication Code e faz uma requisição autenticada para pegar
	 * informações do perfil do usuário
	 * 
	 * @param authCode
	 * @return
	 * @throws IOException
	 */
	public String getUsuarioInfoJson(final String authCode) throws IOException {
		final GoogleTokenResponse response = flow.newTokenRequest(authCode)
				.setRedirectUri(CALLBACK_URI).execute();
		final Credential credential = flow.createAndStoreCredential(response,
				null);
		final HttpRequestFactory requestFactory = HTTP_TRANSPORT
				.createRequestFactory(credential);

		// Faz uma requisição autenticada
		final GenericUrl url = new GenericUrl(USER_INFO_URL);
		final HttpRequest request = requestFactory.buildGetRequest(url);
		request.getHeaders().setContentType("application/json");
		final String jsonIdentity = request.execute().parseAsString();

		return jsonIdentity;
	}

}
