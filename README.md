# tests-for-todo

Тексты реализовала на Java, в качестве базы взяла проект Баранцева с использованием testng.
https://github.com/barancev/webdriver-testng-archetype

### Структура проекта:
#### Файлы с тестами (/src/test/java/myTestProject/):
- BaseTest.java - Базовые тесты. Открытие страницы, добавление записи, редактирование, удаление.
- DeleteAllTests.java и SortTests.java - Дополнительные тесты. Тесты на массовое удаление и на сортировку на id и названию todo.

#### Page object (/src/test/java/myTestProject/pages)
- Page.java - Базовый. Вынесены общие функции.
- HomePage.java - На основную страницу, с которой работаем. Содержит локаторы и функции по работе со страницей.

#### Модель для данных (/src/test/java/myTestProject/models)
- ToDoModel.java - Модель для todo

#### Xml сьюты для запуска (/src/test/resources)
- BaseTests.xml - Базовые тесты;
- AdditionalTests.xml - Дополнительные тесты;
- AllTests.xml - Все тесты.

В базовом проекте был уже настроенный pom.xml файл. Я доработала его под себя - добавила профили для разных сьютов.
Тесты разработала под Chrome, на других браузерах не запускала. 
Для запуска тестов необходимо, чтобы были установлены Java и maven. 

### Команды запуска:
#### хром драйвера и селениум сервера (/src/test/resources/Server&Driver)
**java -jar -Dwebdriver.chrome.driver=chromedriver.exe selenium-server-standalone-3.141.59.jar**
#### Тестов:
- Базовые: **mvn -P chrome,grid test**
- Дополнительные: **mvn -P chrome,grid,additional Tests**
- Все тесты: **mvn -P chrome,grid,allTests test**
