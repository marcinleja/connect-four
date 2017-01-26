# Connect four

How to run:

1. Install mysql
2. Create database and database user
3. Build using maven, and then run:

`java -Dfile.encoding=UTF-8 -jar connect-four-web/target/connect-four-web-0.0.1-SNAPSHOT.jar --spring.config.location=config/connect-four.properties`

where `config/connect-four.properties` is path to your own configuration file.

This project has default configuration provided, so it is not required to include your own properties file.
If you decide not to override the default configuration, please create database and database user according to the configuration defined in `connect-four-web -> src/main/resources/connect-four.properties`




Frontend part comming soon...
