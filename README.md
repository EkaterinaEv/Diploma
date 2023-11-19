# Дипломный проект по профессии «Тестировщик»

Автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

## Начало работы

Чтобы получить локальную копию проекта, необходимо склонировать репозиторий командой 

*git clone git@github.com:EkaterinaEv/Diploma.git*


## Prerequisites

На ПК должны быть установлены и настроены:

- Git
- Браузер Google Chrome
- IntelliJ IDEA
- Docker Desktop

## Установка и запуск

1. Открыть проект Diploma в IntelliJ IDEA

2. В терминале запустить контейнеры Docker командой:
   
*docker-compose up --build*

3. Запустить приложение с БД MySQL из терминала командой:

*java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar aqa-shop.jar*

4. Запустить тесты с БД MySQL из терминала командой:
   
*./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"*

5. Запустить приложение с БД PostgresSQL из терминала командой:
   
*java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app"-jar aqa-shop.jar*

6. Запустить тесты с БД PostgresSQL из терминала командой:
    
*./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"*

7. Генерация отчетов по тестированию и их просмотр в браузере запускается командой в терминале:

   *./gradlew allureServe*
