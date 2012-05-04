Spring Batch Datomic Support
============================

aims to create [ItemReader](http://static.springsource.org/spring-batch/apidocs/org/springframework/batch/item/ItemReader.html) and 
[ItemWriter](http://static.springsource.org/spring-batch/apidocs/org/springframework/batch/item/ItemWriter.html) 
implementation for the [Datomic](http://datomic.com/) database.

WARNING
-------
Early experimental stage, stay tuned.

Installation & Usage
--------------------

You'll need to download and install Datomic manually: 
    
    mvn install:install-file -DgroupId=com.datomic -DartifactId=datomic -Dfile=datomic-${DATOMIC-VERSION}.jar -DpomFile=pom.xml
    
Then you can run the tests:

    cd sb-datomic
    ./gradlew test
	
Once this project is ready for prime time I'll publish it in a maven repository somewhere.

<!---
CI
----

check whether the the code works [here](https://robokasofoss.ci.cloudbees.com/job/spring-batch-datomic/)
-->