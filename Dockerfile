FROM amazoncorretto:8
COPY build/libs/micronaut-arquetipo-*-all.jar micronaut-arquetipo.jar
EXPOSE 8080
CMD java -Dcom.sun.management.jmxremote -noverify ${JAVA_OPTS} -jar micronaut-arquetipo.jar