package id.swhp.javaee.soteria.business.security.boundary;


import id.swhp.javaee.soteria.business.account.entity.Account;
import id.swhp.javaee.soteria.business.security.entity.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(Arquillian.class)
public class ArquillianEmptyTestIT {


    public static final String DEFAULT_CONF = "test.properties";
//    Account account;

    @Deployment
    public static Archive deploy(){
        //importa dipendenze di terze parti e librerie dichiarate nel pom e dipendenze transitive
        File[] files = Maven.resolver()
                .loadPomFromFile(getPomPath())
                .importRuntimeDependencies()
                .asFile();
//        JavaArchive[] assertjArch = Maven.resolver().resolve("org.assertj:assertj-core").withTransitivity().as(JavaArchive.class);
        //creo filesystem con il war vuoto e aggiungo nel war classi e dipendenze per il test..
        return ShrinkWrap.create(WebArchive.class, "javaee-soteria-1.0.0.war")
                .addAsLibraries(files)
                .addClass(Account.class)
                .addClass(Token.class)
                .addClass(TokenType.class)
//                .addClass(InvalidPasswordException.class)
//                .addClass(InvalidUsernameException.class)
//                .addClass(AccountStore.class)
//                .addClass(TokenStore.class)
//                .addClass(HashGenerator.class)
//                .addClass(HashServiceType.class)
//                .addClass(HashType.class)
//                .addClass(SHAAlgorithm.class)
//                .addClass(Sha.class)
//                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
//                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(new StringAsset("<beans/>"), "beans.xml")
                .addAsWebInfResource(new StringAsset("<web-app></web-app>"), "web.xml");

    }

    @Ignore
    private static String getPomPath(){
        //path alla classe
        String pompath = ArquillianEmptyTestIT.class.getResource(".").getPath();
        try {
            String pathOfThisClass = pompath;
            if (pathOfThisClass.startsWith("/")) {
                pathOfThisClass = pathOfThisClass.substring(1);
            }
            Path pathClass = Paths.get(pathOfThisClass).toAbsolutePath();
            String subpath = pathClass.toString().substring(0, pathClass.toString().indexOf("test-classes") + 13) + DEFAULT_CONF;
            Path pathToProperties = pathClass.resolve(subpath);
            FileInputStream fileInputStream = new FileInputStream(pathToProperties.toFile());
            Properties defaults = loadProperties(fileInputStream);
            pompath = defaults.getProperty("pom.path");
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        return pompath;
    }

    @Ignore
    private static Properties loadProperties(InputStream is) {
        Properties props = new Properties();
        try {
//            is = loader.getResourceAsStream(conf);
            if (is != null) {
                props.load(is);
            } else {
                props = null;
            }
        } catch (IOException ex) {
            System.err.println("Errore nella lettura della configurazione (" + ex.getMessage() + ")");
            props = null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ioe) {
                    System.err.println("Errore nella lettura della configurazione (" + ioe.getMessage() + ")");
                }
            }
        }
        return props;
    }

    @Test
    public void emptyInContainerTest(){
        System.out.println("=========================================");
        System.out.println("This test should run inside the container");
        System.out.println("=========================================");
//        assertThat(account).isNotNull();
    }



}



