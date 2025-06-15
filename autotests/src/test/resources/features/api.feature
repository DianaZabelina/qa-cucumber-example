# language: ru
# noinspection NonAsciiCharacters
@qa-cucumber
@qa-cucumber-api
Функционал: Тест API

  Сценарий: Проверка GET запроса
    Пусть Тест делает GET вызов по адресу "https://jsonplaceholder.typicode.com/posts/1"
    Тогда Тест получает ответ с кодом "200"
    И тело ответа JSON содержит строку "userId"