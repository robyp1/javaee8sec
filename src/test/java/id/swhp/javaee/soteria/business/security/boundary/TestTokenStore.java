package id.swhp.javaee.soteria.business.security.boundary;

import id.swhp.javaee.soteria.business.account.boundary.AccountStore;
import id.swhp.javaee.soteria.business.account.boundary.TestSHAGenerator;
import id.swhp.javaee.soteria.business.account.entity.Account;
import id.swhp.javaee.soteria.business.security.control.SHAGenerator;
import id.swhp.javaee.soteria.business.security.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TestTokenStore extends TestSHAGenerator {

    @InjectMocks
//    @HashServiceType(HashType.SHA)
//    @Sha(algorithm = SHAAlgorithm.SHA256)
    private HashGenerator hashTest =new SHAGenerator(SHAAlgorithm.SHA256.getAlgorithmName());
//

    @InjectMocks
    private TokenStore tokenStore;


    @Mock
    private EntityManager _em;


    @Before
    public void initTests(){
        tokenStore.em = _em;
        tokenStore.hash = mock(HashGenerator.class);
        initAccountStore(_em);
    }

//    @Test
//    public void test_SHAAlgorithm_generator(){
//        String passwordhash = hashTest.getHashedText("123456AB");
//        when(tokenStore.generate(any(), any(), any(), any())).thenReturn(passwordhash);
//        assertThat(tokenStore.generate("","","", TokenType.REMEMBER_ME)).isEqualTo(passwordhash);
//
//
//    }

    @Test
    public void test_TokeStore_Save(){

        Account accountExpected = new Account("admin", "password", "email@email");
        accountExpected.setId(null);

        when(accountStore.getByUsername1("admin"))
                .thenReturn(accountExpected);

        when(tokenStore.hash.getHashedText("ABCD")).thenReturn("1234");

        when(tokenStore.em.merge(any())).thenReturn(accountExpected);

        //TEST METHOD SAVE
        Account accountActual = tokenStore.save("ABCD", "", "", "", TokenType.REMEMBER_ME, null);

        Token token = new Token();

        token.setTokenHash("1234");
        token.setExpiration(null);
        token.setDescription("");
        token.setTokenType(TokenType.REMEMBER_ME);
        token.setIpAddress("");

        accountExpected.addToken(token);

        assertThat(accountActual).isEqualTo(accountExpected);

    }


}
