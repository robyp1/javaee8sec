package id.swhp.javaee.soteria.business.security.boundary;

import id.swhp.javaee.soteria.business.account.boundary.AccountStoreMocked;
import id.swhp.javaee.soteria.business.account.entity.Account;
import id.swhp.javaee.soteria.business.security.control.SHAGenerator;
import id.swhp.javaee.soteria.business.security.entity.*;
import org.assertj.core.description.Description;
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
public class TestTokenStore extends AccountStoreMocked {

    @InjectMocks
//    @HashServiceType(HashType.SHA)
//    @Sha(algorithm = SHAAlgorithm.SHA256)
    private HashGenerator hashTest =new SHAGenerator(SHAAlgorithm.SHA256.getAlgorithmName());
//

    @InjectMocks
    private TokenStore tokenStore;


    @Mock
    private EntityManager _em;

    @Mock
    HashGenerator hash;

    @Before
    public void initTests(){
//        tokenStore.hash = mock(HashGenerator.class); see @Mock
        tokenStore.em = _em; //em is annotated, instead of annotation you can use here : em = mock(EntityManager.class)
        tokenStore.accountStore = initAccountStore(_em );
    }

//    @Test sportare in altro test dove save è il metodo del mock(TokenStore.class)
//    public void test_SHAAlgorithm_generator(){
//        String passwordhash = hashTest.getHashedText("123456AB");
//        when(accountStore.getByUsername(any())).thenReturn(Optional.empty());
//        when(tokenStore.generate(any(), any(), any(), any())).thenReturn(passwordhash);
//        assertThat(tokenStore.generate("","","", TokenType.REMEMBER_ME)).isEqualTo(passwordhash);
//
//    }

    @Test
    public void test_TokeStore_Save(){

        //create a virtual account
        Account accountExpected = new Account("admin", "password", "email@email");
        accountExpected.setId(null);
        Token token = new Token();
        token.setTokenHash("1234");
        token.setExpiration(null);
        token.setDescription("");
        token.setTokenType(TokenType.REMEMBER_ME);
        token.setIpAddress("");
        accountExpected.addToken(token);

        //**what expected if wrapping with mock**
        //force return to excpected
        when(accountStore.getByUsername("admin")).thenReturn(Optional.of(accountExpected));

        //force result hash to 1234 with input ABCD
        when(tokenStore.hash.getHashedText("ABCD")).thenReturn("1234");

        //force virtual merge (not happen) with void result
        when(tokenStore.em.merge(any())).thenReturn(null);

        //so ..TEST METHOD SAVE with condition above
        Account accountActual = tokenStore.save("ABCD", "admin", "", "", TokenType.REMEMBER_ME, null);

        System.out.println(String.format("Test account %s = %s", accountActual, accountExpected));
        assertThat(accountActual).isEqualTo(accountExpected);

    }


}
