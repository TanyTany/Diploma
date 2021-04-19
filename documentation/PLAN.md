# План автоматизации

**Объект тестирования:**

Веб-сервис оплаты путешествия двумя способами:
- дебетовой картой
- выдачей кредита по реквизитам банковской карты

**Валидные данные тестовых карт:**

- Номер карты APPROVED: 4444 4444 4444 4441
- Номер карты DECLINED: 4444 4444 4444 4442
- Месяц: двузначное число в диапазоне 01...12
- Год: больше текущего на два, в формате двузначного числа
- Владелец: Имя и Фамилия латиницей
- CVC: Трёхзначное число в диапазоне 000...999


## Перечень автоматизируемых сценариев:
+ [Позитивные сценарии](#Positive)
+ [Тест кейсы для поля "Номер карты"](№Number)
+ [Тест кейсы для поля "Месяц"](#Month)
+ [Тест кейсы для поля "Год"](#Year)
+ [Тест кейсы для поля "Владелец"](#Owner)
+ [Тест кейсы для поля "CVC/CVV"](#CVC)

### <a name="Positive"></a> 1. Позитивные сценарии

**1.1 Заполнение валидными данными Payment Gate с картой APPROVED:**

* Нажать кнопку “Купить”
* В поле “номер карты” ввести: 4444 4444 4444 4441
* В поле "Месяц" ввести: 08
* В поле "Год" ввести: 23
* В поле "Владелец" ввести: Ivan Ivanov
* В поле "CVC/CVV" ввести: 999
* Нажать кнопку "Продолжить"

**Ожидаемый результат**: Сообщение “Успешно. Операция одобрена Банком.”

**1.2 Заполнение валидными данными Credit Gate с картой APPROVED:**

* Нажать кнопку “Купить в кредит”
* В поле “номер карты” ввести: 4444 4444 4444 4441
* В поле "Месяц" ввести: 08
* В поле "Год" ввести: 23
* В поле "Владелец" ввести: Ivan Ivanov
* В поле "CVC/CVV" ввести: 999
* Нажать кнопку "Продолжить"

**Ожидаемый результат**: Сообщение “Успешно. Операция одобрена Банком.”

**1.3 Заполнение валидными данными Payment Gate с картой DECLINED:**

* Нажать кнопку “Купить”
* В поле “номер карты” ввести: 4444 4444 4444 4442
* В поле "Месяц" ввести: 08
* В поле "Год" ввести: 23
* В поле "Владелец" ввести: Ivan Ivanov
* В поле "CVC/CVV" ввести: 999
* Нажать кнопку "Продолжить"

**Ожидаемый результат:** Сообщение “Ошибка! Банк отказал в проведении операции”

**1.4 Заполнение валидными данными Credit Gate с картой DECLINED:**

* Нажать кнопку “Купить в кредит”
* В поле “номер карты” ввести: 4444 4444 4444 4442
* В поле "Месяц" ввести: 08
* В поле "Год" ввести: 23
* В поле "Владелец" ввести: Ivan Ivanov
* В поле "CVC/CVV" ввести: 999
* Нажать кнопку "Продолжить"

**Ожидаемый результат:** Сообщение “Ошибка! Банк отказал в проведении операции”

### 2. <a name="Number"></a> Работа с полем “Номер карты” для Payment Gate и Credit Gate
*Сначала проверяется поле по кнопке “Купить”, далее по кнопке “Купить в кредит”*

**2.1 Номер карты пустой**

* Поле “номер карты” оставить не заполненным
* В поле "Месяц" ввести: 08
* В поле "Год" ввести: 23
* В поле "Владелец" ввести: Ivan Ivanov
* В поле "CVC/CVV" ввести: 999
* Нажать кнопку "Продолжить"

**Ожидаемый результат:** под полем “номер карты” появляется сообщение “Неверный формат”, заявка не отправляется.

**2.2 Номер карты из одинаковых цифр**

* В поле “номер карты” ввести: 9999 9999 9999 9999
* В поле "Месяц" ввести: 08
* В поле "Год" ввести: 23
* В поле "Владелец" ввести: Ivan Ivanov
* В поле "CVC/CVV" ввести: 999
* Нажать кнопку "Продолжить"

**Ожидаемый результат:** Сообщение “Ошибка! Банк отказал в проведении операции”

**2.3 Номер карты заполнен не полностью**

* В поле “номер карты” ввести: 9999 9999 9999 99
* В поле "Месяц" ввести: 08
* В поле "Год" ввести: 23
* В поле "Владелец" ввести: Ivan Ivanov
* В поле "CVC/CVV" ввести: 999
* Нажать кнопку "Продолжить"

**Ожидаемый результат:** под полем “номер карты” появляется сообщение “Неверный формат”, заявка не отправляется.

### <a name="Month"></a> 3. Работа с полем “Месяц” для Payment Gate и Credit Gate
*Сначала проверяется поле по кнопке “Купить”, далее по кнопке “Купить в кредит”*

**3.1 Заполнение поля невалидным значением**

* В поле “номер карты” ввести: 4444 4444 4444 4441
* В поле "Месяц" ввести: 14
* В поле "Год" ввести: 23
* В поле "Владелец" ввести: Ivan Ivanov
* В поле "CVC/CVV" ввести: 999
* Нажать кнопку "Продолжить"

**Ожидаемый результат:** под полем “месяц” появляется сообщение “Неверно указан срок действия карты”, заявка не отправляется.

**3.2 Заполнение поля однозначным числом**

* В поле “номер карты” ввести: 4444 4444 4444 4441
* В поле "Месяц" ввести: 1
* В поле "Год" ввести: 23
* В поле "Владелец" ввести: Ivan Ivanov
* В поле "CVC/CVV" ввести: 999
* Нажать кнопку "Продолжить"

**Ожидаемый результат:** под полем “месяц” появляется сообщение “Неверный формат”, заявка не отправляется.

**3.3 Поле оставить пустым**

* В поле “номер карты” ввести: 4444 4444 4444 4441
* Поле "Месяц" оставить не заполненным
* В поле "Год" ввести: 23
* В поле "Владелец" ввести: Ivan Ivanov
* В поле "CVC/CVV" ввести: 999
* Нажать кнопку "Продолжить"

**Ожидаемый результат:** под полем “месяц” появляется сообщение “Неверный формат”, заявка не отправляется.

**3.4 Заполнение поля нулевым значением**

* В поле “номер карты” ввести: 4444 4444 4444 4441
* В поле "Месяц" ввести: 00
* В поле "Год" ввести: 23
* В поле "Владелец" ввести: Ivan Ivanov
* В поле "CVC/CVV" ввести: 999
* Нажать кнопку "Продолжить"

**Ожидаемый результат:** под полем “месяц” появляется сообщение “Неверный формат”, заявка не отправляется.


### <a name="Year"></a> 4. Работа с полем “Год” для Payment Gate и Credit Gate
*Сначала проверяется поле по кнопке “Купить”, далее по кнопке “Купить в кредит”*

**4.1 Заполнение поля прошедшей датой**

* В поле “номер карты” ввести: 4444 4444 4444 4441
* В поле "Месяц" ввести: 08
* В поле "Год" ввести: 19
* В поле "Владелец" ввести: Ivan Ivanov
* В поле "CVC/CVV" ввести: 999
* Нажать кнопку "Продолжить"

**Ожидаемый результат:** под полем “год” появляется сообщение “Истёк срок действия карты”, заявка не отправляется.

**4.2 Заполнение поля однозначным числом**

* В поле “номер карты” ввести: 4444 4444 4444 4441
* В поле "Месяц" ввести: 08
* В поле "Год" ввести: 2
* В поле "Владелец" ввести: Ivan Ivanov
* В поле "CVC/CVV" ввести: 999
* Нажать кнопку "Продолжить"

**Ожидаемый результат:** под полем “месяц” появляется сообщение “Неверный формат”, заявка не отправляется.

**4.3 Поле оставить пустым**

* В поле “номер карты” ввести: 4444 4444 4444 4441
* В поле "Месяц" ввести: 09
* Поле "Год" оставить пустым
* В поле "Владелец" ввести: Ivan Ivanov
* В поле "CVC/CVV" ввести: 999
* Нажать кнопку "Продолжить"

**Ожидаемый результат:** под полем “год” появляется сообщение “Неверный формат”, заявка не отправляется.

**4.4 Заполнение поля невалидным значением**

* В поле “номер карты” ввести: 4444 4444 4444 4441
* В поле "Месяц" ввести: 09
* В поле "Год" ввести: 30
* В поле "Владелец" ввести: Ivan Ivanov
* В поле "CVC/CVV" ввести: 999
* Нажать кнопку "Продолжить"

**Ожидаемый результат:** под полем “год” появляется сообщение “Неверно указан срок действия карты”, заявка не отправляется.

### <a name="Owner"></a> 5. Работа с полем “Владелец” для Payment Gate и Credit Gate
*Сначала проверяется поле по кнопке “Купить”, далее по кнопке “Купить в кредит”*

**5.1 Заполнение поля невалидным значением (не указав фамилию)**

* В поле “номер карты” ввести: 4444 4444 4444 4441
* В поле "Месяц" ввести: 08
* В поле "Год" ввести: 22
* В поле "Владелец" ввести: Ivan
* В поле "CVC/CVV" ввести: 999
* Нажать кнопку "Продолжить"

**Ожидаемый результат:** под полем “Владелец” появляется сообщение “Неверный формат”, заявка не отправляется.


**5.2 Заполнение поля кириллицей**

* В поле “номер карты” ввести: 4444 4444 4444 4441
* В поле "Месяц" ввести: 08
* В поле "Год" ввести: 22
* В поле "Владелец" ввести: Иван Иванов
* В поле "CVC/CVV" ввести: 999
* Нажать кнопку "Продолжить"

**Ожидаемый результат:** под полем “Владелец” появляется сообщение “Неверный формат”, заявка не отправляется.


**5.3 Поле оставить пустым**

* В поле “номер карты” ввести: 4444 4444 4444 4441
* В поле "Месяц" ввести: 09
* В поле "Год" ввести: 22
* Поле "Владелец" оставить пустым
* В поле "CVC/CVV" ввести: 999
* Нажать кнопку "Продолжить"

**Ожидаемый результат:** под полем “Владелец” появляется сообщение “Поле обязательно для заполнения”, заявка не отправляется.

**5.4 Заполнение поля невалидным значением (цифрами)**

* В поле “номер карты” ввести: 4444 4444 4444 4441
* В поле "Месяц" ввести: 09
* В поле "Год" ввести: 22
* В поле "Владелец" ввести: 4356
* В поле "CVC/CVV" ввести: 999
* Нажать кнопку "Продолжить"

**Ожидаемый результат:** под полем “Владелец” появляется сообщение “Неверный формат”, заявка не отправляется.


### <a name="CVC"></a> 6. Работа с полем “CVC/CVV” для Payment Gate и Credit Gate
*Сначала проверяется поле по кнопке “Купить”, далее по кнопке “Купить в кредит”*

**6.1 Заполнение поля невалидным значением (не полностью)**

* В поле “номер карты” ввести: 4444 4444 4444 4441
* В поле "Месяц" ввести: 08
* В поле "Год" ввести: 22
* В поле "Владелец" ввести: Ivan Ivanov
* В поле "CVC/CVV" ввести: 99
* Нажать кнопку "Продолжить"

**Ожидаемый результат:** под полем “CVC/CVV” появляется сообщение “Неверный формат”, заявка не отправляется


**6.2 Поле оставить пустым**

* В поле “номер карты” ввести: 4444 4444 4444 4441
* В поле "Месяц" ввести: 09
* В поле "Год" ввести: 22
* В поле "Владелец" ввести: Ivan Ivanov
* Поле "CVC/CVV" оставить пустым
* Нажать кнопку "Продолжить"

**Ожидаемый результат:** под полем “CVC/CVV” появляется сообщение “Неверный формат”, заявка не отправляется.


## Перечень используемых инструментов:
- IntelliJ IDE, Gradle для написания авто тестов
- Docker для развертывания сервера SQL и запуска симулятора данных на Node.js
- Selenide для взаимодействия с web элементами на странице
- Google Chrome для работы с web формой
- Java - для написания автотестов
- Git, Github - для хранения кода
- Junit 5 для работы с annotations и assertions
- Allure  для отчета по автотестам
- Appveyor для непрерывной интеграции CI

## Перечень и описание возможных рисков при автоматизации:
- Нет документации к тестируемому приложению. О корректном поведении приходится строить догадки.
- Определение CSS элементов на странице для записи в поля будет затруднен, так как разработчики не добавили вспомогательных элементов
- Реализация некоторых тестовых сценариев, где в форму невозможно ввести не валидное значение


## Интервальная оценка с учётом рисков (в часах)
- Анализ приложения и написание тест плана (с корректировками) - 8 часов
- Автоматизация тестирования - 40 часов, с учетом рисков возможно +16 часов
- Написание отчетов - 8 часов

**Итоговая оценка** - 72 часа

**План сдачи работ** 19 апреля 2021г