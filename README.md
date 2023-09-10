## Architecture

I've decided to create RESTful API implementing MVC model.
Classes responsible for each functionality are defined as model, repository and service.
To mock usage of provided external service I've implemented logic to retrieve json array of potential phishing domains.
I'm using cert.pl API to retrieve the list and cache possible phishing domains for further use.
Cache manager is implemented as singleton to provide one cache in multiple injection points. Method for updating cache
is scheduled to run every 10 minutes.

Phone number which is used to register whether customer wants to enable phishing validation is stored in properties
file: 22123123999

## Database

Application use H2 database to provide less complexity and allow to prepare only one docker image.
When the phishing validation is enabled only non-phishing messages are stored in DB.

## Further improvements:

* implement usage of Web Risk Lookup API
* improve test coverage
* add logic to assign message with subscriber and persist
* add view for invoking requests

## Testing

Application has Swagger UI enabled to allow quick test of endpoints.
The shortened URL for swagger is available in properties file

### Subscribers

To add subscribers the following JSON should be send to /api/v1/subscribers. Field isCheckEnabled is optional and can be
removed.

{
"phoneNumber": "string",
"isCheckEnabled": 0
}

### SMS

To register SMS the following JSON should be send to /api/v1/sms. Field isPhishing is optional and can be removed.

{
"sender": "string",
"recipient": "string",
"message": "string",
"isPhishing": 0
}

### Phishing validator activation

To activate/deactivate phishing validation service the following JSON should be send to /api/v1/sms/. Field isPhishing
is optional and can be removed.

{
"sender": "string",
"recipient": "string",
"message": "string",
"isPhishing": 0
}