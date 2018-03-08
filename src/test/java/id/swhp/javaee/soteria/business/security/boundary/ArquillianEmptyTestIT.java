package id.swhp.javaee.soteria.business.security.boundary;


import id.swhp.javaee.soteria.business.account.entity.Account;
import id.swhp.javaee.soteria.business.security.entity.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import javax.inject.Inject;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(Arquillian.class)
public class ArquillianEmptyTestIT {


    public static final String DEFAULT_CONF = "test.properties";
    Account account;

    @Deployment
    public static Archive deploy(){
        //importa dipendenze di terze parti e librerie dichiarate nel pom e dipendenze transitive
        File[] files = Maven.resolver()
                .loadPomFromFile("C:/Progetti/altri/javaee-soteria-master/pom.xml")//(getPomPath())
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
    private static String getPomPath() {
        //usare questa sopra per il percorso al pom, ma ora da null pointer
        Properties defaults = loadProperties(ArquillianEmptyTestIT.class.getResourceAsStream(DEFAULT_CONF));
        String pompath = defaults.getProperty("pom.path");
        //System.out.println("*** " + defaults.getProperty("pom.path"));
        return pompath!=null ? pompath : "C:/Progetti/altri/javaee-soteria-master/pom.xml";
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


/**
 * LOG dell'errore
 *
 * [INFO]
 [INFO] --- maven-failsafe-plugin:2.20.1:integration-test (default-cli) @ javaee-soteria ---
 [INFO]
 [INFO] -------------------------------------------------------
 [INFO]  T E S T S
 [INFO] -------------------------------------------------------
 [INFO] Running id.swhp.javaee.soteria.business.security.boundary.ArquillianEmptyTestIT
 log4j:WARN No appenders could be found for logger (org.jboss.logging).
 log4j:WARN Please initialize the log4j system properly.
 Mar 07, 2018 4:55:27 PM org.jboss.as.arquillian.container.managed.ManagedDeployableContainer startInternal
 WARNING: Bundles path is deprecated and no longer used.
 Mar 07, 2018 4:55:27 PM org.jboss.as.arquillian.container.managed.ManagedDeployableContainer startInternal
 INFO: Starting container with: ["C:\Program Files\Java\jdk1.8.0_65\bin\java", -D[Standalone], -Xms64m, -Xmx512m, -Djava.net.preferIPv4Stack=true, -Djava.awt.headless=true, -Djboss.modules.system.pkgs=org.jboss.byteman, -ea, -Djboss.home.dir=C:\Progetti\altri\javaee-soteria-master\cargo\wildfly-10.1.0.Final\wildfly-10.1.0.Final, -Dorg.jboss.boot.log.file=C:\Progetti\altri\javaee-soteria-master\cargo\wildfly-10.1.0.Final\wildfly-10.1.0.Final\standalone\log\server.log, -Dlogging.configuration=file:C:\Progetti\altri\javaee-soteria-master\cargo\wildfly-10.1.0.Final\wildfly-10.1.0.Final\standalone\configuration\logging.properties, -jar, C:\Progetti\altri\javaee-soteria-master\cargo\wildfly-10.1.0.Final\wildfly-10.1.0.Final\jboss-modules.jar, -mp, C:\Progetti\altri\javaee-soteria-master\cargo\wildfly-10.1.0.Final\wildfly-10.1.0.Final\modules;C:\Progetti\altri\javaee-soteria-master\cargo\wildfly-10.1.0.Final\wildfly-10.1.0.Final\modules, org.jboss.as.standalone, -Djboss.home.dir=C:\Progetti\altri\javaee-soteria-master\cargo\wildfly-10.1.0.Final\wildfly-10.1.0.Final, -Djboss.server.base.dir=C:\Progetti\altri\javaee-soteria-master\cargo\wildfly-10.1.0.Final\wildfly-10.1.0.Final\standalone, -Djboss.server.log.dir=C:\Progetti\altri\javaee-soteria-master\cargo\wildfly-10.1.0.Final\wildfly-10.1.0.Final\standalone\log, -Djboss.server.config.dir=C:\Progetti\altri\javaee-soteria-master\cargo\wildfly-10.1.0.Final\wildfly-10.1.0.Final\standalone\configuration]
 16:55:28,705 INFO  [org.jboss.modules] (main) JBoss Modules version 1.5.2.Final
 16:55:29,057 INFO  [org.jboss.msc] (main) JBoss MSC version 1.2.6.Final
 16:55:29,173 INFO  [org.jboss.as] (MSC service thread 1-6) WFLYSRV0049: WildFly Full 10.1.0.Final (WildFly Core 2.2.0.Final) starting
 16:55:31,372 INFO  [org.jboss.as.server] (Controller Boot Thread) WFLYSRV0039: Creating http management service using socket-binding (management-http)
 16:55:31,401 INFO  [org.xnio] (MSC service thread 1-6) XNIO version 3.4.0.Final
 16:55:31,414 INFO  [org.xnio.nio] (MSC service thread 1-6) XNIO NIO Implementation Version 3.4.0.Final
 16:55:31,522 INFO  [org.jboss.remoting] (MSC service thread 1-2) JBoss Remoting version 4.0.21.Final
 16:55:31,596 INFO  [org.jboss.as.connector.subsystems.datasources] (ServerService Thread Pool -- 33) WFLYJCA0004: Deploying JDBC-compliant driver class org.h2.Driver (version 1.3)
 16:55:31,624 INFO  [org.jboss.as.connector.subsystems.datasources] (ServerService Thread Pool -- 33) WFLYJCA0004: Deploying JDBC-compliant driver class org.hsqldb.jdbc.JDBCDriver (version 2.3)
 16:55:31,641 INFO  [org.wildfly.extension.io] (ServerService Thread Pool -- 37) WFLYIO001: Worker 'default' has auto-configured to 8 core threads with 64 task threads based on your 4 available processors
 16:55:31,710 INFO  [org.jboss.as.clustering.infinispan] (ServerService Thread Pool -- 38) WFLYCLINF0001: Activating Infinispan subsystem.
 16:55:31,977 WARN  [org.jboss.as.txn] (ServerService Thread Pool -- 54) WFLYTX0013: Node identifier property is set to the default value. Please make sure it is unique.
 16:55:31,989 INFO  [org.jboss.as.naming] (ServerService Thread Pool -- 46) WFLYNAM0001: Activating Naming Subsystem
 16:55:32,001 INFO  [org.jboss.as.security] (ServerService Thread Pool -- 53) WFLYSEC0002: Activating Security Subsystem
 16:55:32,005 INFO  [org.jboss.as.security] (MSC service thread 1-6) WFLYSEC0001: Current PicketBox version=4.9.6.Final
 16:55:32,016 INFO  [org.jboss.as.webservices] (ServerService Thread Pool -- 56) WFLYWS0002: Activating WebServices Extension
 16:55:32,071 INFO  [org.jboss.as.connector] (MSC service thread 1-4) WFLYJCA0009: Starting JCA Subsystem (WildFly/IronJacamar 1.3.4.Final)
 16:55:32,075 INFO  [org.wildfly.extension.undertow] (MSC service thread 1-1) WFLYUT0003: Undertow 1.4.0.Final starting
 16:55:32,083 INFO  [org.jboss.as.connector.deployers.jdbc] (MSC service thread 1-8) WFLYJCA0018: Started Driver service with driver-name = h2
 16:55:32,084 INFO  [org.jboss.as.connector.deployers.jdbc] (MSC service thread 1-8) WFLYJCA0018: Started Driver service with driver-name = hsqldb
 16:55:32,118 INFO  [org.jboss.as.jsf] (ServerService Thread Pool -- 44) WFLYJSF0007: Activated the following JSF Implementations: [main]
 16:55:32,244 INFO  [org.jboss.as.naming] (MSC service thread 1-1) WFLYNAM0003: Starting Naming Service
 16:55:32,424 INFO  [org.jboss.as.mail.extension] (MSC service thread 1-8) WFLYMAIL0001: Bound mail session [java:jboss/mail/sibMS]
 16:55:32,431 INFO  [org.jboss.as.mail.extension] (MSC service thread 1-5) WFLYMAIL0001: Bound mail session [java:jboss/mail/Default]
 16:55:32,618 INFO  [org.jboss.as.ejb3] (MSC service thread 1-6) WFLYEJB0481: Strict pool slsb-strict-max-pool is using a max instance size of 64 (per class), which is derived from thread worker pool sizing.
 16:55:32,625 INFO  [org.jboss.as.ejb3] (MSC service thread 1-8) WFLYEJB0482: Strict pool mdb-strict-max-pool is using a max instance size of 16 (per class), which is derived from the number of CPUs on this host.
 16:55:32,748 INFO  [org.wildfly.extension.undertow] (ServerService Thread Pool -- 55) WFLYUT0014: Creating file handler for path 'C:\Progetti\altri\javaee-soteria-master\cargo\wildfly-10.1.0.Final\wildfly-10.1.0.Final/welcome-content' with options [directory-listing: 'false', follow-symlink: 'false', case-sensitive: 'true', safe-symlink-paths: '[]']
 16:55:32,858 INFO  [org.wildfly.extension.undertow] (MSC service thread 1-1) WFLYUT0012: Started server default-server.
 16:55:32,860 INFO  [org.wildfly.extension.undertow] (MSC service thread 1-2) WFLYUT0018: Host default-host starting
 16:55:33,424 INFO  [org.wildfly.extension.undertow] (MSC service thread 1-4) WFLYUT0006: Undertow HTTP listener default listening on 127.0.0.1:9090
 16:55:33,737 ERROR [org.jboss.msc.service.fail] (MSC service thread 1-7) MSC000001: Failed to start service jboss.patching.manager: org.jboss.msc.service.StartException in service jboss.patching.manager: java.lang.IllegalStateException: Duplicate layer 'base'
 at org.jboss.as.patching.installation.InstallationManagerService.start(InstallationManagerService.java:86)
 at org.jboss.msc.service.ServiceControllerImpl$StartTask.startService(ServiceControllerImpl.java:1948)
 at org.jboss.msc.service.ServiceControllerImpl$StartTask.run(ServiceControllerImpl.java:1881)
 at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
 at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
 at java.lang.Thread.run(Thread.java:745)
 Caused by: java.lang.IllegalStateException: Duplicate layer 'base'
 at org.jboss.as.patching.installation.LayersFactory$ProcessedLayers.addLayer(LayersFactory.java:305)
 at org.jboss.as.patching.installation.LayersFactory.processRoot(LayersFactory.java:182)
 at org.jboss.as.patching.installation.LayersFactory.process(LayersFactory.java:127)
 at org.jboss.as.patching.installation.LayersFactory.load(LayersFactory.java:86)
 at org.jboss.as.patching.installation.InstallationManagerImpl.<init>(InstallationManagerImpl.java:47)
 at org.jboss.as.patching.installation.InstallationManager.load(InstallationManager.java:185)
 at org.jboss.as.patching.installation.InstallationManagerService.load(InstallationManagerService.java:108)
 at org.jboss.as.patching.installation.InstallationManagerService.start(InstallationManagerService.java:62)
 ... 5 more

 16:55:33,789 WARN  [org.jboss.as.domain.management.security] (MSC service thread 1-1) WFLYDM0111: Keystore C:\Progetti\altri\javaee-soteria-master\cargo\wildfly-10.1.0.Final\wildfly-10.1.0.Final\standalone\configuration\application.keystore not found, it will be auto generated on first use with a self signed certificate for host localhost
 16:55:33,801 INFO  [org.jboss.as.server.deployment.scanner] (MSC service thread 1-2) WFLYDS0013: Started FileSystemDeploymentService for directory C:\Progetti\altri\javaee-soteria-master\cargo\wildfly-10.1.0.Final\wildfly-10.1.0.Final\standalone\deployments
 16:55:34,153 INFO  [org.infinispan.factories.GlobalComponentRegistry] (MSC service thread 1-4) ISPN000128: Infinispan version: Infinispan 'Chakra' 8.2.4.Final
 16:55:34,176 INFO  [org.jboss.as.connector.subsystems.datasources] (MSC service thread 1-2) WFLYJCA0001: Bound data source [java:jboss/datasources/ExampleDS]
 16:55:34,176 INFO  [org.jboss.as.connector.subsystems.datasources] (MSC service thread 1-2) WFLYJCA0001: Bound data source [java:jboss/jdbc/soteria]
 16:55:34,223 INFO  [org.wildfly.extension.undertow] (MSC service thread 1-1) WFLYUT0006: Undertow HTTPS listener https listening on 127.0.0.1:8443
 16:55:34,254 INFO  [org.infinispan.configuration.cache.EvictionConfigurationBuilder] (ServerService Thread Pool -- 58) ISPN000152: Passivation configured without an eviction policy being selected. Only manually evicted entities will be passivated.
 16:55:34,255 INFO  [org.infinispan.configuration.cache.EvictionConfigurationBuilder] (ServerService Thread Pool -- 58) ISPN000152: Passivation configured without an eviction policy being selected. Only manually evicted entities will be passivated.
 16:55:34,253 INFO  [org.infinispan.configuration.cache.EvictionConfigurationBuilder] (ServerService Thread Pool -- 62) ISPN000152: Passivation configured without an eviction policy being selected. Only manually evicted entities will be passivated.
 16:55:34,259 INFO  [org.infinispan.configuration.cache.EvictionConfigurationBuilder] (ServerService Thread Pool -- 62) ISPN000152: Passivation configured without an eviction policy being selected. Only manually evicted entities will be passivated.
 16:55:34,254 INFO  [org.infinispan.configuration.cache.EvictionConfigurationBuilder] (ServerService Thread Pool -- 61) ISPN000152: Passivation configured without an eviction policy being selected. Only manually evicted entities will be passivated.
 16:55:34,264 INFO  [org.infinispan.configuration.cache.EvictionConfigurationBuilder] (ServerService Thread Pool -- 61) ISPN000152: Passivation configured without an eviction policy being selected. Only manually evicted entities will be passivated.
 16:55:34,531 INFO  [org.jboss.ws.common.management] (MSC service thread 1-1) JBWS022052: Starting JBossWS 5.1.5.Final (Apache CXF 3.1.6)
 16:55:34,537 ERROR [org.jboss.as.controller.management-operation] (Controller Boot Thread) WFLYCTL0013: Operation ("boottime-controller-initializer-step") failed - address: ([]) - failure description: {
 "WFLYCTL0080: Failed services" => {"jboss.patching.manager" => "org.jboss.msc.service.StartException in service jboss.patching.manager: java.lang.IllegalStateException: Duplicate layer 'base'
 Caused by: java.lang.IllegalStateException: Duplicate layer 'base'"},
 "WFLYCTL0412: Required services that are not installed:" => ["jboss.patching.manager"],
 "WFLYCTL0180: Services with missing/unavailable dependencies" => undefined
 }
 16:55:34,583 INFO  [org.jboss.as.controller] (Controller Boot Thread) WFLYCTL0183: Service status report
 WFLYCTL0186:   Services which failed to start:      service jboss.patching.manager: org.jboss.msc.service.StartException in service jboss.patching.manager: java.lang.IllegalStateException: Duplicate layer 'base'

 16:55:34,876 INFO  [org.jboss.as] (Controller Boot Thread) WFLYSRV0060: Http management interface listening on http://127.0.0.1:9050/management
 16:55:34,876 INFO  [org.jboss.as] (Controller Boot Thread) WFLYSRV0051: Admin console listening on http://127.0.0.1:9050
 16:55:34,876 ERROR [org.jboss.as] (Controller Boot Thread) WFLYSRV0026: WildFly Full 10.1.0.Final (WildFly Core 2.2.0.Final) started (with errors) in 6705ms - Started 339 of 586 services (1 services failed or missing dependencies, 394 services are lazy, passive or on-demand)
 [ERROR] Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 10.561 s <<< FAILURE! - in id.swhp.javaee.soteria.business.security.boundary.ArquillianEmptyTestIT
 [ERROR] id.swhp.javaee.soteria.business.security.boundary.ArquillianEmptyTestIT  Time elapsed: 10.558 s  <<< ERROR!
 java.lang.RuntimeException: Could not invoke deployment method: public static org.jboss.shrinkwrap.api.Archive id.swhp.javaee.soteria.business.security.boundary.ArquillianEmptyTestIT.deploy()
 at id.swhp.javaee.soteria.business.security.boundary.ArquillianEmptyTestIT.deploy(ArquillianEmptyTestIT.java:39)

 Mar 07, 2018 4:55:36 PM org.jboss.arquillian.core.impl.ObserverImpl resolveArguments
 WARNING: Argument 2 for ArquillianServiceDeployer.undeploy is null. It won't be invoked.
 16:55:36,651 INFO  [org.jboss.as.server] (management-handler-thread - 3) WFLYSRV0236: Suspending server with no timeout.
 16:55:36,674 INFO  [org.jboss.as.server] (Management Triggered Shutdown) WFLYSRV0241: Shutting down in response to management operation 'shutdown'
 16:55:36,855 INFO  [org.jboss.as.connector.subsystems.datasources] (MSC service thread 1-6) WFLYJCA0010: Unbound data source [java:jboss/datasources/ExampleDS]
 16:55:36,865 INFO  [org.jboss.as.connector.subsystems.datasources] (MSC service thread 1-6) WFLYJCA0010: Unbound data source [java:jboss/jdbc/soteria]
 16:55:36,889 INFO  [org.wildfly.extension.undertow] (MSC service thread 1-6) WFLYUT0019: Host default-host stopping
 16:55:36,898 INFO  [org.wildfly.extension.undertow] (MSC service thread 1-6) WFLYUT0008: Undertow HTTPS listener https suspending
 16:55:36,901 INFO  [org.wildfly.extension.undertow] (MSC service thread 1-6) WFLYUT0007: Undertow HTTPS listener https stopped, was bound to 127.0.0.1:8443
 16:55:36,953 INFO  [org.wildfly.extension.undertow] (MSC service thread 1-6) WFLYUT0008: Undertow HTTP listener default suspending
 16:55:36,955 INFO  [org.wildfly.extension.undertow] (MSC service thread 1-6) WFLYUT0007: Undertow HTTP listener default stopped, was bound to 127.0.0.1:9090
 16:55:36,957 INFO  [org.wildfly.extension.undertow] (MSC service thread 1-6) WFLYUT0004: Undertow 1.4.0.Final stopping
 16:55:36,982 INFO  [org.jboss.as.connector.deployers.jdbc] (MSC service thread 1-4) WFLYJCA0019: Stopped Driver service with driver-name = hsqldb
 16:55:37,141 INFO  [org.jboss.as.connector.deployers.jdbc] (MSC service thread 1-2) WFLYJCA0019: Stopped Driver service with driver-name = h2
 16:55:37,170 INFO  [org.jboss.as] (MSC service thread 1-4) WFLYSRV0050: WildFly Full 10.1.0.Final (WildFly Core 2.2.0.Final) stopped in 482ms
 [INFO]
 [INFO] Results:
 [INFO]
 [ERROR] Errors:
 [ERROR]   ArquillianEmptyTestIT.id.swhp.javaee.soteria.business.security.boundary.ArquillianEmptyTestIT » Runtime
 [INFO]
 [ERROR] Tests run: 1, Failures: 0, Errors: 1, Skipped: 0
 [INFO]
 [INFO] ------------------------------------------------------------------------
 [INFO] BUILD SUCCESS
 [INFO] ------------------------------------------------------------------------
 [INFO] Total time: 17.281 s
 [INFO] Finished at: 2018-03-07T16:55:37+01:00
 [INFO] Final Memory: 14M/491M
 [INFO] ------------------------------------------------------------------------
 Java HotSpot(TM) 64-Bit Server VM warning: ignoring option PermSize=256m; support was removed in 8.0
 Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=1024m; support was removed in 8.0

 Process finished with exit code 0
 */
