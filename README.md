# notifier-test

build: mvn clean package

run: mvn spring-boot:run -Dserver.port=9999

API Description get from:

[host root]/v2/api-docs
  
  in Json format

<p>Short API Description</p>
                   <p>/api-tariff-notifier  is app base address</p>
              <p>/test{id} METOD GET PARAMS id - fuction for randomly update details of tariff with {id}
                    <p>/users METOD GET  PARAMS page=1 size=5 -  list of all users in db</p>
                  <p>/tariffs METOD GET  PARAMS page=1 size=5 -  list of all tariffs with details in db</p>
                   <p>PARAMS page - page number for pagination; size - size of page</p>
                   <p>/notify/t METOD POST  PARAMS t=0 corresponds to notification message; t=1 corresponds to notification message template </p>
                    <p>REQUEST BODY json request notification object {user object, tariff object,start of period, end of period} </p>
                    <p>Example</p>
                    <p>{"user":{"id":2,"firstName":"petr","lastName":"petr","tariffId":1,"tariffName":"good"},"tariff":{"id":1,"name":"good","dateCreated":"2017-11-09","tariffDetailsCollection":[{"id":2,"tariffId":1,"pricePerUnit":0.10,"dateLastModified":"2016-11-11","nomenclatureId":2},{"id":1,"tariffId":1,"pricePerUnit":0.01,"dateLastModified":"2016-11-11","nomenclatureId":1}],"active":true},"startOfPeriod":"2017-01-01","endOfPeriod":1510493026701}</p>
                   <p>/tariffs/update/{id} METOD POST PARAMS id REQUEST BODY json object tariff detais</p>
                   <p>/notify/empty - empty request notification object for /notify/t method</p>
