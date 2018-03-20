package id.swhp.javaee.soteria.business.account.boundary;

import id.swhp.javaee.soteria.business.security.boundary.HashGenerator;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.inject.Inject;
import javax.persistence.EntityManager;


@RunWith(MockitoJUnitRunner.class)
public class AccountStoreMocked {


    @Mock
    HashGenerator tokenHash;

    @Mock
    HashGenerator passwordHash;

    @Mock
    protected AccountStore accountStore;

    public AccountStore initAccountStore(EntityManager _em){
        accountStore.em = _em;
        return accountStore;
    }


}
