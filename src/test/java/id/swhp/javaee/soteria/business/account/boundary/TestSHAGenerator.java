package id.swhp.javaee.soteria.business.account.boundary;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;


@RunWith(MockitoJUnitRunner.class)
public class TestSHAGenerator {


    @Mock
    protected AccountStore accountStore;

    public void initAccountStore(EntityManager _em){
        accountStore.em = _em;
    }


}
