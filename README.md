# Java EE Security Soteria

Playing around with new JSR-375 (Security API) and _Reference Implementation_ Soteria.

Linked from the real Project (MIT license):
https://medium.com/@swhp/playing-with-java-ee-security-jsr-375-soteria-38e8d2b094d4

This implementation is for Wildfly 10x and HSQL

## Installation

### Requirements
* Java 8
* Apache Maven
* HSQL 3.2.2
* Wildfly 10+ WebServer

### Database Schema
Run application server with persistence.xml this property set:

 &lt;property name="hibernate.hbm2ddl.auto" value="create-drop"/&gt;

After database creation reset property to "validate"


### Application Server (Wildfly)

#### HSQL JDBC Driver

Go to src\jboss\modules

Copy directory \hsqldb in

\wildfly-10.1.0.Final\modules\system\layers\base\org



### Compile and Package
Being Maven centric, compile and package can be done:

```
mvn clean compile
mvn clean package
```

To simplified it can be done:

```
mvn clean install

Once you have the war file, you can deploy it.

```
If you want, you can run cargo with wildfly installation in process:

```
1. mvn cargo:install  (install Wildfly in target\cargo directory)
2. copy hsql as descrived before
3. mvn cargo:run (start wildfly server)
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would
like to change.

Please make sure to update tests as appropriate.

Resume steps:

* Running in web-App application (standalone external wildfly server)*

1)mvn process-test-resources -Psetup-cargo
2)mvn clean install -Pdefault (you found war in target dir)
3)copy war in external wildfly10x server or deploy it using wildfly:deploy (wildfly maven plugin)
4)install HsqlDb, run hsqldb and create empty database soteria
5)run wildfly by standalone.bat
6)open browser to
http://localhost:8080/javaee-soteria-1.0.0/register.xhtml to register new user
http://localhost:8080/javaee-soteria-1.0.0/login.xhtml to login with a valid user

*Running test*
1)mvn process-test-resources -Psetup-cargo
2)mvn clean install -Pdefault
3)mvn failsafe:integration-test -> run integration tests (*IT.java) -> cargo launch wildfly and integration tests

## License
[MIT](https://choosealicense.com/licenses/mit/)
