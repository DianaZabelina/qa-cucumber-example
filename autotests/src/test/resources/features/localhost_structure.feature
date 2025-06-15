# language: ru
# noinspection NonAsciiCharacters
@qa-cucumber
@qa-cucumber-loc-str
@local-only
Функционал: Тест localhost+structure

  Структура сценария: Тест localhost+structure

    Дано Сценарий теста: "<codeHTTP> - <title>"
    Пусть Тест делает GET вызов по адресу "http://localhost:8080/?param=<param>"
    Тогда Тест получает ответ с кодом "<codeHTTP>"
    И тело ответа JSON содержит строку "<response>"

    Примеры:
      | param | codeHTTP | title                                | response |
      | 200   | 200      | Корневая страница                    | 8080     |
      |       | 500      | Неизвестная ошибка (пустой параметр) | 3000     |
      | 500   | 500      | Неизвестная ошибка                   | 3000     |