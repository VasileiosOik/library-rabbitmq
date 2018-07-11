# RabbitMQExample

Send as a POST request using postman:
{
  "header": {
    "event": "as.request.received",
    "messageId": "512"
  },
  "payload": {
  	 "person" :{
  	 "age:": 30,
     "name": "Bill"
  	 }
  }
}
a message will be sent to the queue ready for consumption from DEFAULT handler.
