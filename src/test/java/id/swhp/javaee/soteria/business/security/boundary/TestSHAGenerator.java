package id.swhp.javaee.soteria.business.security.boundary;

import id.swhp.javaee.soteria.business.account.boundary.AccountStore;
import id.swhp.javaee.soteria.business.account.entity.Account;
import id.swhp.javaee.soteria.business.security.control.SHAGenerator;
import id.swhp.javaee.soteria.business.security.entity.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TestSHAGenerator {

    @InjectMocks
//    @HashServiceType(HashType.SHA)
//    @Sha(algorithm = SHAAlgorithm.SHA256)
    private HashGenerator hash =new SHAGenerator(SHAAlgorithm.SHA256.getAlgorithmName());


    @Mock
    private TokenStore tokenStore;

    @Mock
    private AccountStore accountStore;

    @Mock
    private EntityManager em;

    @Test
    public void test_SHAAlgorithm_generator(){
        String passwordhash = hash.getHashedText("123456AB");
        when(tokenStore.generate(any(), any(), any(), any())).thenReturn(passwordhash);
        assertThat(tokenStore.generate("","","", TokenType.REMEMBER_ME)).isEqualTo(passwordhash);


    }

    @Test
    public void test_TokeStore_Save(){

        Account accountExpected = new Account("admin", "password", "email@email");
        accountExpected.setId(1L);
        when(accountStore.getByUsername(any()))
                .thenReturn(Optional.of(accountExpected));

        when(hash.getHashedText("ABCD")).thenReturn("1234");

        when(em.merge(any())).thenReturn("");

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
