
# Практическая работа 4.

# Spring, работа с БД (Вариант 1 — Телевизор)

## Описание проекта
Консольное CRUD-приложение на базе **Spring Framework** и **Spring JDBC (`JdbcTemplate`)** для взаимодействия с реляционной базой данных PostgreSQL.

### Сущность: Телевизор (`Tv`)
* `id` — идентификатор (SERIAL PRIMARY KEY)
* **Текстовые поля (3):**
  * `brand` — производитель (Samsung, LG, Sony)
  * `model` — наименование модели
  * `screenTechnology` — технология матрицы (OLED, QLED, LED)
* **Числовые поля (2):**
  * `screenDiagonal` — диагональ экрана в дюймах
  * `price` — стоимость в рублях

---

## Структура проекта
```text
.
├── pom.xml
├── README.md
└── src
    └── main
        ├── java
        │   ├── Main.java
        │   ├── SpringConfig.java
        │   ├── Tv.java
        │   └── TvDao.java
        └── resources
            └── application.properties

```

---

## Развертывание базы данных PostgreSQL

### 1. Запуск СУБД в Docker

Запуск контейнера с пробросом порта `5433`:

```bash
docker run --name postgres-tv -e POSTGRES_PASSWORD=postgres -p 5433:5432 -d postgres

```

### 2. Создание таблицы и заполнение начальными данными

Выполните инициализацию структуры таблицы `tv`:

```bash
docker exec -i postgres-tv psql -U postgres -c "
CREATE TABLE tv (
    id SERIAL PRIMARY KEY,
    brand VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    screen_technology VARCHAR(100) NOT NULL,
    screen_diagonal DOUBLE PRECISION NOT NULL,
    price DOUBLE PRECISION NOT NULL
);

INSERT INTO tv (brand, model, screen_technology, screen_diagonal, price) VALUES
('Samsung', 'QE55Q60A', 'QLED', 55.0, 65000.0),
('LG', 'OLED55C2', 'OLED', 55.0, 115000.0),
('Sony', 'KD-43X81J', 'LED', 43.0, 48000.0);
"
```

### 3. Настройка подключения


---

## Системные требования

* **Java Development Kit (JDK):** 17 или выше
* **Apache Maven:** 3.6+
* **PostgreSQL:** 12+ (или запущенный Docker-контейнер)

---

## Инструкция по сборке и запуску из командной строки

### 1. Компиляция проекта

```bash
mvn clean compile
```

### 2. Запуск приложения

```bash
mvn exec:java
```

### 3. Сборка jar-пакета

```bash
mvn clean package
```

---

## Пример работы программы

```text
=== УПРАВЛЕНИЕ ТОВАРАМИ (ТЕЛЕВИЗОРЫ) ===
1. Вывести все записи
2. Добавить новый телевизор
3. Редактировать запись по ID
4. Удалить запись по ID
5. Поиск телевизоров с ценой не выше заданной
0. Выход
Выберите пункт меню: 1

Список телевизоров в БД:
ID: 1   | Бренд: Samsung    | Модель: QE55Q60A     | Экран: QLED     | Диагональ: 55.0" | Цена:  65000.00 руб.
ID: 2   | Бренд: LG         | Модель: OLED55C2     | Экран: OLED     | Диагональ: 55.0" | Цена: 115000.00 руб.
ID: 3   | Бренд: Sony       | Модель: KD-43X81J    | Экран: LED      | Диагональ: 43.0" | Цена:  48000.00 руб.

Выберите пункт меню: 5
Введите максимальную цену: 70000

Результаты поиска:
ID: 3   | Бренд: Sony       | Модель: KD-43X81J    | Экран: LED      | Диагональ: 43.0" | Цена:  48000.00 руб.
ID: 1   | Бренд: Samsung    | Модель: QE55Q60A     | Экран: QLED     | Диагональ: 55.0" | Цена:  65000.00 руб.


