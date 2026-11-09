# Практическая работа №7: Архитектура REST в Spring (Вариант 1 — Телевизор)

**Тема:** Поддержка архитектуры REST в Spring. Разработка REST-контроллера и клиентского приложения на базе `RestTemplate`.  
**Базовая работа:** В качестве основы модифицирована **Практическая работа №5 (Spring MVC)**.

---

## 1. Описание функционала и выполненные требования

В исходное веб-приложение каталога телевизоров внедрена поддержка архитектуры REST с сохранением полного предшествующего веб-функционала:

1. **Реорганизация веб-приложения под принципы RESTful**:
   * Все адреса ресурсов приведены к иерархическому виду без глаголов в пути и без параметров идентификации в query-строке (`?id=...`). Идентификатор ресурса передается внутри пути URL через `@PathVariable` (например, `/tvs/{id}`, `/tvs/{id}/edit`, `/tvs/{id}/delete`).
   * Реализовано согласование содержимого (**Content Negotiation**) через `ContentNegotiatingViewResolver`: по одному и тому же базовому URL сервер возвращает либо HTML-страницу (для браузера), либо JSON-данные (для REST-клиентов) на основе заголовка `Accept`.

2. **Поддержка основных методов протокола HTTP**:
   * **`GET /tvs`**:
     * Заголовок `Accept: text/html` $\rightarrow$ рендеринг таблицы всех телевизоров (`tv_list.ftl`).
     * Заголовок `Accept: application/json` $\rightarrow$ сериализация списка всех сущностей в JSON-массив (`@ResponseBody`).
   * **`GET /tvs/{id}`**:
     * Заголовок `Accept: text/html` $\rightarrow$ карточка просмотра конкретного телевизора (`tv_show.ftl`).
     * Заголовок `Accept: application/json` $\rightarrow$ возврат одного объекта в формате JSON (`@ResponseBody`).
   * **`POST /tvs`**: прием JSON-тела (`@RequestBody`), создание новой записи в БД PostgreSQL с возвратом созданного объекта со сгенерированным ID и HTTP-статусом `201 Created`.
   * **`PUT /tvs/{id}`**: прием измененных данных ресурса в JSON (`@RequestBody`), обновление записи в базе данных и возврат статуса `204 No Content`.
   * **`DELETE /tvs/{id}`**: удаление указанного ресурса по первичному ключу с возвратом статуса `204 No Content`.

3. **Разработка консольного REST-клиента (`RestTemplate`)**:
   * В пакете `client` реализовано автономное консольное приложение `RestClientApp`.
   * Клиент последовательно проверяет полный жизненный цикл ресурса в БД через HTTP-методы `RestTemplate`:
     * `exchange()` / `getForObject()` для чтения списка и единичной записи;
     * `postForObject()` для добавления объекта;
     * `put()` для модификации ресурса;
     * `delete()` для удаления объекта.
   * Клиент запускается из окна терминала одной командой Maven.

4. **Сохранение предшествующего функционала**:
   * Веб-интерфейс в браузере полностью работоспособен: доступен просмотр каталога, форма добавления с валидацией Hibernate Validator (`@NotEmpty`, `@Positive`), формы редактирования, подтверждения удаления и поиск по максимальной цене.

---

## 2. Программная структура проекта

```text
tv-web-app/
├── pom.xml                                   # Зависимости Spring MVC, Jackson JSON, FreeMarker, PostgreSQL
├── README.md                                 # Отчет и инструкция по запуску и тестированию
└── src/
    └── main/
        ├── java/
        │   ├── client/
        │   │   └── RestClientApp.java        # Консольный REST-клиент на RestTemplate
        │   ├── controllers/
        │   │   ├── HomeController.java       # Маршрутизация главной страницы (/)
        │   │   └── TvController.java         # REST- и MVC-контроллер (HTML + JSON: GET, POST, PUT, DELETE)
        │   ├── dao/
        │   │   └── TvDao.java                # Слой доступа к БД через JdbcTemplate (с RETURNING id)
        │   └── models/
        │       └── Tv.java                   # Доменная модель с аннотациями Bean Validation
        ├── resources/
        │   ├── application.properties        # Параметры подключения к СУБД PostgreSQL
        │   └── config/
        │       └── applicationContextMVC.xml # Настройка ContentNegotiatingViewResolver и FreeMarker
        └── webapp/
            └── WEB-INF/
                ├── views/                    # Шаблоны FreeMarker (.ftl)
                │   ├── index.ftl             # Главная страница
                │   ├── tv_add.ftl            # Форма добавления записи
                │   ├── tv_delete.ftl         # Форма подтверждения удаления
                │   ├── tv_edit.ftl           # Форма редактирования записи
                │   ├── tv_list.ftl           # Таблица товаров со ссылками в REST-стиле
                │   ├── tv_search.ftl         # Поиск товаров по бюджету
                │   └── tv_show.ftl           # Просмотр единичной записи по /tvs/{id}
                └── web.xml                   # Дескриптор развертывания сервлета DispatcherServlet
```

---

## 3. Стек технологий

* **Язык программирования:** Java 17
* **Сборщик проекта:** Apache Maven
* **Серверная часть:** Spring Framework 5.3 (Spring Context, Spring Web, Spring Web MVC, Spring JDBC)
* **Сериализация JSON:** Jackson Databind 2.13 (`MappingJackson2HttpMessageConverter`)
* **Шаблонизатор:** Apache FreeMarker 2.3
* **Сервер приложений:** Apache Tomcat 9.x
* **СУБД:** PostgreSQL (порт `5432`)

---

## 4. Настройка базы данных

Конфигурация подключения задана в файле `src/main/resources/application.properties`:

```properties
dataSource.driverClassName=org.postgresql.Driver
dataSource.url=jdbc:postgresql://localhost:5432/postgres
dataSource.username=postgres
dataSource.password=postgres
```

При старте бин `TvDao` автоматически проверяет наличие таблицы `public.tv` и создает её, если она отсутствует:

```sql
CREATE TABLE IF NOT EXISTS public.tv (
    id SERIAL PRIMARY KEY,
    brand VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    screen_technology VARCHAR(100) NOT NULL,
    screen_diagonal DOUBLE PRECISION NOT NULL,
    price DOUBLE PRECISION NOT NULL
);
```

---

## 5. Инструкция по сборке, развертыванию и удалению

### 1. Сборка WAR-пакета из командной строки
В корневой папке проекта выполните сборку:
```powershell
mvn clean package
```
В каталоге `target/` будет скомпилирован архив `tv-web-app.war`.

### 2. Развертывание (установка) в Apache Tomcat
1. Скопируйте файл `target/tv-web-app.war` в каталог `webapps/` установленного сервера Tomcat:
   ```powershell
   copy target\tv-web-app.war C:\programming\tomcat\apache-tomcat-9.0.122\webapps\
   ```
2. Перейдите в каталог `bin/` сервера Tomcat и запустите его:
   * **Windows:**
     ```cmd
     startup.bat
     ```
   * **Linux / macOS:**
     ```bash
     ./startup.sh
     ```
3. Сервер запустится на порту `8080`, распакует WAR-архив и инициализирует контекст Spring MVC.

### 3. Свертывание (удаление) приложения
Для полной остановки и удаления приложения из контейнера:
1. Остановите сервер Tomcat из папки `bin/`:
   * **Windows:**
     ```cmd
     shutdown.bat
     ```
   * **Linux / macOS:**
     ```bash
     ./shutdown.sh
     ```
2. Удалите развернутые файлы из директории `webapps/`:
   ```powershell
   del C:\programming\tomcat\apache-tomcat-9.0.122\webapps\tv-web-app.war
   rmdir /S /Q C:\programming\tomcat\apache-tomcat-9.0.122\webapps\tv-web-app
   ```
3. Для очистки артефактов сборки в самом проекте выполните:
   ```powershell
   mvn clean
   ```

---

## 6. Примеры работы и тестирование

### 6.1. Тестирование веб-интерфейса (Браузер)
* **Главное меню:** `http://localhost:8080/tv-web-app/`
* **Таблица товаров (HTML):** `http://localhost:8080/tv-web-app/tvs`
* **Просмотр отдельного телевизора (HTML):** `http://localhost:8080/tv-web-app/tvs/1`
* **Форма добавления:** `http://localhost:8080/tv-web-app/tvs/add`
* **Форма редактирования (REST-путь):** `http://localhost:8080/tv-web-app/tvs/1/edit`
* **Форма удаления (REST-путь):** `http://localhost:8080/tv-web-app/tvs/1/delete`
* **Фильтрация по бюджету:** `http://localhost:8080/tv-web-app/tvs/search?maxPrice=70000`

---

### 6.2. Тестирование консольного REST-клиента

При запущенном сервере Tomcat откройте терминал в папке проекта и запустите клиента командой:

```powershell
mvn exec:java
```

#### Протокол выполнения операций клиентом:

```text
==================================================
   ТЕСТИРОВАНИЕ REST API (RestTemplate Client)    
==================================================

1. [GET /tvs] Извлечение всех телевизоров из БД:
   • Tv{id=8, brand='Samsung', model='QE55Q60A', tech='QLED', diagonal=55.0", price=65000.00}
   • Tv{id=9, brand='Sony', model='KD-43X81J', tech='LED', diagonal=43.0", price=48000.00}

2. [POST /tvs] Добавление нового телевизора (Xiaomi Mi TV Q1):
   -> Создан ресурс: Tv{id=10, brand='Xiaomi', model='Mi TV Q1', tech='QLED', diagonal=55.0", price=54990.00}

3. [GET /tvs/10] Извлечение созданной записи по ID:
   -> Получен ресурс: Tv{id=10, brand='Xiaomi', model='Mi TV Q1', tech='QLED', diagonal=55.0", price=54990.00}

4. [PUT /tvs/10] Обновление модели и цены телевизора:
   -> После обновления: Tv{id=10, brand='Xiaomi', model='Mi TV Q1 Pro (Updated)', tech='QLED', diagonal=55.0", price=49990.00}

5. [DELETE /tvs/10] Удаление телевизора с ID = 10:
   -> Запись успешно удалена.

6. [GET /tvs] Контрольная проверка списка после удаления:
   • Tv{id=8, brand='Samsung', model='QE55Q60A', tech='QLED', diagonal=55.0", price=65000.00}
   • Tv{id=9, brand='Sony', model='KD-43X81J', tech='LED', diagonal=43.0", price=48000.00}

==================================================
        ТЕСТИРОВАНИЕ REST-КЛИЕНТА ЗАВЕРШЕНО       
==================================================
```