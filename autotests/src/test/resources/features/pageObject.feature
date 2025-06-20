# language: ru
# noinspection NonAsciiCharacters
@qa-cucumber
@qa-cucumber-po
@local-only
Функционал: Тест Page Object

  Сценарий: Проверка пользовательского сценария
    Дано совершен переход на страницу 'Main Page'
    И окно браузера развернуто на весь экран
    Также пользователь авторизовался с помощью логина и пароля

    Пусть загрузилась страница 'Products'
    И подождать 5 секунд
    Тогда элемент с текстом 'Sauce Labs Backpack' отобразился на странице
    И элемент с текстом 'Sauce Labs Bike Light' отобразился на странице
    И элемент с текстом 'Sauce Labs Bolt T-Shirt' отобразился на странице
    И элемент с текстом 'Sauce Labs Onesie' отобразился на странице

    Затем выполнено нажатие на кнопку 'Add to cart Sauce Labs Backpack'
    Тогда кнопка 'Remove Sauce Labs Backpack' отобразилась на экране
    И элемент 'Shopping cart badge' отобразился на экране
    Также значение элемента 'Shopping cart badge' равно '1'

    Затем выполнено нажатие на кнопку 'Add to cart Sauce Labs Bike Light'
    Тогда кнопка 'Remove Sauce Labs Bike Light' отобразилась на экране
    И значение элемента 'Shopping cart badge' равно '2'

    Затем выполнено нажатие на кнопку 'Remove Sauce Labs Bike Light'
    Тогда значение элемента 'Shopping cart badge' равно '1'

    Затем выполнено нажатие на кнопку 'Add to cart Sauce Labs Bolt T-Shirt'
    Тогда кнопка 'Remove Sauce Labs Bolt T-Shirt' отобразилась на экране
    И значение элемента 'Shopping cart badge' равно '2'

    Затем выполнен скролл вниз
    Также выполнено нажатие на кнопку 'Add to cart Sauce Labs Onesie'
    Тогда кнопка 'Remove Sauce Labs Onesie' отобразилась на экране
    И значение элемента 'Shopping cart badge' равно '3'

    Затем выполнено нажатие на элемент 'Shopping cart'

    Пусть загрузилась страница 'Your Cart'
    И подождать 3 секунды
    Также выполнен скролл вверх
    Тогда элемент 'Shopping cart badge' отобразился на экране
    Также значение элемента 'Shopping cart badge' равно '3'
    И блок 'Your Cart' содержит значения как в таблице:
      | QTYDescription          |
      | 1                       |
      | Sauce Labs Backpack     |
      | $29.99                  |
      | Remove                  |
      | 1                       |
      | Sauce Labs Bolt T-Shirt |
      | $15.99                  |
      | Remove                  |
      | 1                       |
      | Sauce Labs Onesie       |
      | $7.99                   |
      | Remove                  |

    Затем выполнено нажатие на кнопку 'Remove Sauce Labs Onesie'
    Тогда значение элемента 'Shopping cart badge' равно '2'
    И блок 'Your Cart' содержит значения как в таблице:
      | QTYDescription          |
      | 1                       |
      | Sauce Labs Backpack     |
      | $29.99                  |
      | Remove                  |
      | 1                       |
      | Sauce Labs Bolt T-Shirt |
      | $15.99                  |
      | Remove                  |

    Затем выполнено нажатие на кнопку 'Checkout'

    Пусть загрузилась страница 'Checkout Your Information'
    И подождать 3 секунды
    Затем выполнено нажатие на кнопку 'Continue'
    Тогда элемент 'Error' отобразился на экране
    Также значение элемента 'Error' равно 'Error: First Name is required'

    Затем в поле 'First-name' введено значение 'Jane'
    И выполнено нажатие на кнопку 'Continue'
    Тогда значение элемента 'Error' равно 'Error: Last Name is required'

    Затем в поле 'Last-name' введено значение 'Doe'
    И выполнено нажатие на кнопку 'Continue'
    Тогда значение элемента 'Error' равно 'Error: Postal Code is required'

    Затем в поле 'Postal-code' введено значение '10001'
    И выполнено нажатие на кнопку 'Continue'

    Пусть загрузилась страница 'Checkout Overview'
    И подождать 3 секунды
    Также выполнен скролл вверх
    И подождать 1 секунду
    Тогда элемент 'Shopping cart badge' отобразился на экране
    Также значение элемента 'Shopping cart badge' равно '2'
    И блок 'Your Cart' содержит значения как в таблице:
      | QTYDescription          |
      | 1                       |
      | Sauce Labs Backpack     |
      | $29.99                  |
      | 1                       |
      | Sauce Labs Bolt T-Shirt |
      | $15.99                  |

    Затем выполнено нажатие на кнопку 'Finish'

    Пусть загрузилась страница 'Checkout Complete'
    И подождать 3 секунды
    Тогда элемент 'Complete-header' отобразился на экране
    И  кнопка 'Back Home' отобразилась на экране