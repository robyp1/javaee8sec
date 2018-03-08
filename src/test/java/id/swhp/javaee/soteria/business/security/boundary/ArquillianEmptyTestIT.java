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
    public static final String TEST_CLASSES_DIR = "test-classes";
    public static final String XML_POM_PATH = "pom.path";
    public static final String XML_PERSISTENCE_PATH = "persistence.path";
    public static final String XML_BEANS_PATH = "beans.path";
    public static final String XML_WEB_PATH = "web.path";
    private static String pompath;
    private static String persistencePath;
    private static String beansPath;
    private static String webPath;

    Account account;

    @Deployment
    public static Archive deploy(){
        //importa dipendenze di terze parti e librerie dichiarate nel pom e dipendenze transitive
        resolveProjectSrcFilesPath();
        File[] files = Maven.resolver()
                .loadPomFromFile(pompath)
                .importRuntimeAndTestDependencies()
                .resolve()
                .withTransitivity()
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
                .addAsResource(new File(persistencePath))
                .addAsWebInfResource (new File(beansPath))//(new StringAsset("<beans/>"), "beans.xml")
                .addAsWebInfResource(new File(webPath)); //(new StringAsset("<web-app></web-app>"), "web.xml");

    }

    @Ignore
    private static void resolveProjectSrcFilesPath(){
        //path alla classe
        pompath = ArquillianEmptyTestIT.class.getResource(".").getPath();
        persistencePath = ArquillianEmptyTestIT.class.getResource(".").getPath();
        try {
            String pathOfThisClass = pompath;
            if (pathOfThisClass.startsWith("/")) {
                pathOfThisClass = pathOfThisClass.substring(1);
            }
            Path pathClass = Paths.get(pathOfThisClass).toAbsolutePath();
            String subpath = pathClass.toString().substring(0, pathClass.toString().indexOf(TEST_CLASSES_DIR) + 13) + DEFAULT_CONF;
            Path pathToProperties = pathClass.resolve(subpath);
            FileInputStream fileInputStream = new FileInputStream(pathToProperties.toFile());
            Properties defaults = loadProperties(fileInputStream);
            pompath = defaults.getProperty(XML_POM_PATH);
            persistencePath = defaults.getProperty(XML_PERSISTENCE_PATH);
            beansPath = defaults.getProperty(XML_BEANS_PATH);
            webPath = defaults.getProperty(XML_WEB_PATH);
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
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
        assertThat(account).isNotNull();
    }



}



