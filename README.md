### Инструкция подключения БД и запуску SUT
* C помощью команды git clone осуществить клонирование репозитория
* Открыть проект в Intellij IDEA
* Для запуска контейнеров ввести в терминале Intellij IDEA команду `docker-compose up -d`.
* Запустить SUT командой:
  * на БД MySQL `java -Dspring.datasource-mysql.url=jdbc:mysql://localhost:3306/app -jar aqa-shop.jar`
  * на БД PostgreSQL `java -Dspring.datasource-postgresql.url=jdbc:postgresql://localhost:5432/app -jar aqa-shop.jar`  
* Войти в браузер Google Chrome и открыть ссылку http://localhost:8080.
* Для остановки приложения команда `CTRL+C`


### Запуск тестов и генерация отчетов

`./gradlew test allureReport` - первичный запуск

`./gradlew clean test` - сгенерировать отчеты

`./gradlew allureServe` - сгенерировать отчеты и открыть в браузере

