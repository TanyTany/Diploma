### Инструкция подключения БД и запуску SUT
* C помощью команды git clone осуществить клонирование репозитория
* Открыть проект в Intellij IDEA
* Для запуска контейнеров ввести в терминале Intellij IDEA команду `docker-compose up -d`.
* Подключиться к контейнеру командой `docker-compose exec mysql mysql -u app -p app`
* Запустить SUT командой `java -jar aqa-shop.jar`
* Войти в браузер Google Chrome и открыть ссылку http://localhost:8080.
