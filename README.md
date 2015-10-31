# Spring Boot Template

Spring Boot template with Java 8, PostgreSQL 9.4, JPA, CrudRepository.

[http://localhost:8080/people](http://localhost:8080/people)

[http://localhost:8080/people/1](http://localhost:8080/people/1)

## Build

```
mvn clean compile
```

## Run

```
mvn spring-boot:run
```

## One-Jar

```
mvn clean package
java -jar ${NAME}-${VERSION}-exec.jar
```

## References
* [Spring Boot](http://projects.spring.io/spring-boot/)
* [Use Jetty instead of Tomcat](http://docs.spring.io/spring-boot/docs/current/reference/html/howto-embedded-servlet-containers.html#howto-use-jetty-instead-of-tomcat)
* [Jackson Mappers Wrapper (Karol Król)](https://github.com/kjkrol/jackson-mappers-wrapper)
* [NoSuchMethodError when serializing Guava types](https://github.com/FasterXML/jackson-datatype-guava/issues/78)