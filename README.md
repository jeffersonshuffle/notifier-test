# notifier-test

build: mvn package

run: mvn spring-boot:run --server.port=8080


{"user":{"id":2,"firstName":"petr","lastName":"petr","tariffId":1,"tariffName":"good"},"tariff":{"id":1,"name":"good","dateCreated":"2017-11-09","tariffDetailsCollection":[{"id":2,"tariffId":1,"pricePerUnit":0.10,"dateLastModified":"2016-11-11","nomenclatureId":2},{"id":1,"tariffId":1,"pricePerUnit":0.01,"dateLastModified":"2016-11-11","nomenclatureId":1}],"active":true},"startOfPeriod":"2017-01-01","endOfPeriod":1510493026701}
