package id.swhp.javaee.soteria.business.security.boundary;

import id.swhp.javaee.soteria.business.account.boundary.AccountStore;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ArquillianEmptyTestIT {

    @Deployment
    public static Archive deploy(){

        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(AccountStore.class)
                .addClass(TokenStore.class)
                .addClass(HashGenerator.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/persistence.xml");
    }


    @Test
    public void emptyInContainerTest(){
        System.out.println("=========================================");
        System.out.println("This test should run inside the container");
        System.out.println("=========================================");
    }
}
