# RabbitMQExample

Send as a POST request using postman:
{
  "header": {
    "event": "mq.request.received",
    "messageId": "123"
  },
  "payload": {
  	"@class" : "com.example.domain.Payload",
  	"payloadItems": [
  		{
  	     "@class" : "com.example.domain.Person",
  	     "age:": 30,
         "name": "Bill"
  	    }
  	 ]
  }
}
a message will be sent to the queue ready for consumption from DEFAULT handler.
Be very careful to include JsonTypeInfo for the deserialization of the Interface or abstract class.
