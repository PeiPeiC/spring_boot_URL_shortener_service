
clean:
	-rm ./target/spring-boot-hello-world*.jar

jar:
	mvn clean install

run:
	mvn spring-boot:run
