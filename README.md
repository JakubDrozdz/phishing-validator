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