# Explore with me

----------------------

![Java 11](https://img.shields.io/badge/JavaSE-11-orange)  ![Spring Boot 2.7.2](https://img.shields.io/badge/SpringBoot-2.7.2-brightgreen)
![Postgres SQL](https://img.shields.io/badge/Postgres%20SQL-14-blue) ![Hibernate](https://img.shields.io/badge/Hibernate-5.6.10-lightgrey)
![Lombok](https://img.shields.io/badge/Lombok-1.8.24-red) ![MapStruct](https://img.shields.io/badge/MapStruct-1.5.3.Final-red)
![Maven](https://img.shields.io/badge/Maven-4.0.0-green) 
![Docker](https://badgen.net/badge/icon/docker?icon=docker&label) ![Git](https://badgen.net/badge/icon/github?icon=github&label)

----------------------
[Pull request №1](https://github.com/ilyankin/java-explore-with-me/pull/1)

-----------------------

**Explore with me (EWM)** — приложение, котрое предоставляет удобное RESTful API для **создания**
и **размещения** афиш, в которых можно предложить какое-либо событие с возможностью подачи заявлений на участие в нём.

-----------------------

Схемотичное представление **главной** страницы EWM:
![](.resources/prototype-main-page.png "Application homepage prototype")

-----------------------

## Архитектура

-----------------------

Приложение разделено на три сервиса

- _ewm-gateway-service_ — сервис отвечающий за обработку внешних HTTP-запросов и валидацию получаемых данных.
- _ewm-main-service_ — сервис содержащий всю основную бизнесс логику приложения.
- _ewm-stats-service_ — сервис, который обрабатывает статистику по просмотрам событий.

## API

-----------------------
API основного сервиса разделён на три части:

- Публичная (public) - доступна без регистрации любому пользователю сети.
- Закрытая (private) - доступна только авторизованным пользователям.
- Административная (amin) - доступна только для администраторов сервиса.

### Публичное API

1. Сортировка списка событий по количеству просмотров либо по датам событий.
2. Просмотр подробной информации о конкретном событии.
3. Есть возможность получения всех имеющихся категорий и подборок событий (такие подборки могут составлять администраторы ресурса).
4. Каждый публичный запрос для получения списка событий или полной информации о мероприятии фиксируется сервисом статистики.

### Закрытое API

1. Пользователи могут добавлять в приложение новые мероприятия, редактировать их и просматривать после добавления.
2. Могут подавать заявки на участие в интересующих мероприятиях.
3. Создатель мероприятия имеет возможность подтверждать заявки, которые отправили другие пользователи сервиса.

### Административное API

1. Добавление, изменение и удаление категорий для событий.
2. Возможность добавлять, удалять и закреплять на главной странице подборки мероприятий.
3. Модерацию событий, размещённых пользователями, — публикация или отклонение.
4. Управление пользователями — добавление, просмотр и удаление.

## Запуск

Запуск осуществляется с использованием Docker-контейнеров.

Требования:
- _Docker 20+_
- _Docker-compose 2.10+_

```
git clone git@github.com:ilyankin/java-explore-with-me.git
cd java-explore-with-me
mvn package
docker-compose up -d
```

-----------------------

## Спецификация

Спецификация API сервисов была создана c помощью [Swagger](https://editor-next.swagger.io).  
1. [Основного сервиса](.swagger/ewm-main-service-spec.json)
2. [Сервиса статистики](.swagger/ewm-stats-service-spec.json)

-----------------------

## Тестирование

Тестировалось приложение с помощью postman-тестов.
1. [Основного сервиса](.postman/ewm-main-service.json)
2. [Сервиса статистики](.postman/ewm-stats-service.json)

<a href="#" onClick="scroll(0,0); return false" title="up">Вверх страницы</a>