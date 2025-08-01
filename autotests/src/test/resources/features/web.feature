# language: ru
# noinspection NonAsciiCharacters
@qa-cucumber
@qa-cucumber-web
@local-only
Функционал: Тест web+selenide

  Сценарий: Проверка заголовка сайта
    Пусть Тест открывает страницу "https://ya.ru"
    И окно браузера развернуто на весь экран
    И подождать 5 секунд
    Тогда заголовок содержит строку "Яндекс"

  Сценарий: Проверка заголовка сайта
    Пусть Тест открывает страницу "https://google.com"
    И размер экрана устройства задан '400' на '800'
    И подождать 3 секунды
    Тогда заголовок содержит строку "Google"

  Сценарий: Проверка заголовка сайта
    Пусть Тест открывает страницу "https://bing.com"
    И размер экрана устройства задан '800' на '400'
    И подождать 3 секунды
    Тогда заголовок содержит строку "Bing"