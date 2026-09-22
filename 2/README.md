
# Практическая работа №2: Внедрение зависимостей в Spring Framework (Вариант 1)

## Описание проекта
Консольное приложение на базе **Spring Framework**, демонстрирующее конфигурирование IoC-контейнера и внедрение зависимостей (Dependency Injection) при помощи **XML-конфигурации**.

### Реализованный вариант
* **Вариант:** 1 — «Музыкальный плеер» (`MusicPlayer`).
* **Интерфейс зависимости:** `Music` с методом `getSong()`.
* **Имплементации интерфейса:**
  * `RockMusic` (содержит уникальное поле `bandName`).
  * `ClassicalMusic` (содержит уникальное поле `composer`).

---


## Структура проекта
```text
.
├── pom.xml
├── README.md
└── src
    └── main
        ├── java
        │   ├── ClassicalMusic.java
        │   ├── Main.java
        │   ├── Music.java
        │   ├── MusicPlayer.java
        │   └── RockMusic.java
        └── resources
            ├── applicationContext.xml
            └── musicPlayer.properties

```

---

## Системные требования

* **Java Development Kit (JDK):** версия 17 или выше.


* **Apache Maven:** версия 3.6+.



---

## Инструкция по сборке и запуску из командной строки

### 1. Очистка и сборка проекта

Для компиляции исходного кода и копирования ресурсов выполните:

```bash
mvn clean compile

```

### 2. Запуск приложения

Запуск класса `Main` через Maven-плагин `exec-maven-plugin`:

```bash
mvn exec:java

```

### 3. Сборка исполняемого jar-пакета

Для упаковки проекта в `target/music-player-app-1.0-SNAPSHOT.jar`:

```bash
mvn clean package

```

---

## Пример работы программы

При выполнении команды `mvn exec:java` в консоль выводится:

```text
----------------------------------------
Модель плеера: Sony Walkman Pro
Уровень громкости: 85%
Сейчас играет: Рок-трек группы Queen
----------------------------------------

```

---

## Конфигурация контекста (`applicationContext.xml`)

Связывание компонентов выполнено следующим образом:

* `<context:property-placeholder location="classpath:musicPlayer.properties"/>` — регистрирует обработчик внешних параметров.


* Бин `musicPlayer` получает ссылку на бин музыки через конструктор и числовые/строковые поля через сеттеры:



```xml
<bean id="musicPlayer" class="MusicPlayer">
    <constructor-arg ref="rockMusicBean"/>
    <property name="volume" value="${musicPlayer.volume}"/>
    <property name="model" value="${musicPlayer.model}"/>
</bean>

```
