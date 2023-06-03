container?=docker-mysql

clean:
	-rm ./target/spring-boot-URL-shortener-service*.jar
jar:
	mvn clean install

run:
	mvn spring-boot:run

default:
	echo ${container}

.PHONY: docker-up
docker-up:
	@echo "Run docker container - ${container}!"
	docker-compose -f docker-compose.yaml up --build

.PHONY: docker-down
docker-down: ## Stop docker containers and clear artefacts.
	@echo "Terminate docker container - ${container}"
	docker-compose -f docker-compose.yaml down
	docker system prune
