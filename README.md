# notifier-test

build: <b>mvn package</b>

run: <b>mvn spring-boot:run -Dserver.port=9999</b>

API Description get from:

<b>[host root]/v2/api-docs</b>
  
  in Json format

<p><b>Short API Description</b></p>
                   <p><b>/api-tariff-notifier</b>  is app base address</p>
              <p><b>/test/{id}</b> METOD GET PARAMS id - fuction for randomly update details of tariff with {id}
                    <p><b>/users</b> METOD GET  PARAMS page=1 size=5 -  list of all users in db</p>
                  <p><b>/tariffs</b> METOD GET  PARAMS page=1 size=5 -  list of all tariffs with details in db</p>
                   <p>PARAMS <b>page</b> - page number for pagination; size - size of page</p>
                   <p><b>/notify</b> METOD POST  PARAMS  corresponds to notification message; </p>
                   <p><b>/notify/t</b> METOD POST  PARAMS  corresponds to notification message template </p>
                    <p>REQUEST BODY json request notification object {user object, tariff object,start of period, end of period} </p>
                    <p><b>Example of request notification object</b></p>
                    <p>{"user":{"id":2,"firstName":"petr","lastName":"petr","tariffId":1,"tariffName":"good"},"tariff":{"id":1,"name":"good","dateCreated":"2017-11-09","tariffDetailsCollection":[{"id":2,"tariffId":1,"pricePerUnit":0.10,"dateLastModified":"2016-11-11","nomenclatureId":2},{"id":1,"tariffId":1,"pricePerUnit":0.01,"dateLastModified":"2016-11-11","nomenclatureId":1}],"active":true},"startOfPeriod":"2017-01-01","endOfPeriod":1510493026701}</p>
                   <p><b>/tariffs/update</b> METOD POST PARAMS  REQUEST BODY json object tariff detais</p>
                   <p><b>Example of json object tariff detai</b></p>
                   {
                    "id": 5,
                    "tariffId": 3,
                    "pricePerUnit": 1.5,
                    "dateLastModified": "2017-11-15",
                    "nomenclatureId": 1
                }
                   <p><b>/notify/empty</b> - empty request notification object for <b>/notify</b> and <b>/notify/t</b> methods</p>
