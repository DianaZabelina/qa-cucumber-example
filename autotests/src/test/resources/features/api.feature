# language: ru
# noinspection NonAsciiCharacters
@qa-cucumber
@qa-cucumber-api
Функционал: Тест API

  Сценарий: Проверка GET запроса
    Пусть Тест делает GET вызов по адресу "https://jsonplaceholder.typicode.com/posts/1"
    Тогда Тест получает ответ с кодом "200"
    И тело ответа JSON содержит строку "userId"

  Сценарий: Проверка GET запроса и JSON-а полностью
    Пусть Тест делает GET вызов по адресу "https://jsonplaceholder.typicode.com/posts/2"
    Тогда Тест получает ответ с кодом "200"
    И тело ответа JSON соответствует ожидаемому результату "testdata/response/api-jsonplaceholder-typicode-02.json" по правилу проверки "STRICT"