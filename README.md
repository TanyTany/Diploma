[План автоматизации](https://github.com/TanyTany/Diploma/blob/master/documentation/PLAN.md)

[Отчет о проведенном тестирования](https://github.com/TanyTany/Diploma/blob/master/documentation/Report.md)

[Отчет о проведенной автоматизации](https://github.com/TanyTany/Diploma/blob/master/documentation/Summary.md)

### Инструкция подключения БД и запуску SUT
* C помощью команды git clone осуществить клонирование репозитория
* Открыть проект в Intellij IDEA
* Для запуска контейнеров ввести в терминале Intellij IDEA команду `docker-compose up -d`.
* Запустить SUT командой:
  * на БД MySQL `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar aqa-shop.jar`
  * на БД PostgreSQL `java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar aqa-shop.jar`  
* Войти в браузер Google Chrome и открыть ссылку http://localhost:8080.
* Для остановки приложения команда `CTRL+C`


### Запуск тестов и генерация отчетов

`./gradlew clean test` - первичный запуск

`./gradlew allureReport` - сгенерировать отчеты

`./gradlew allureServe` - сгенерировать отчеты и открыть в браузере

