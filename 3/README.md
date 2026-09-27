
# Практическая работа №3: Внедрение зависимостей в Spring Framework при помощи аннотаций (Вариант 1)

## Описание проекта
Консольное приложение на базе **Spring Framework**, демонстрирующее конфигурирование IoC-контейнера и внедрение зависимостей (Dependency Injection) при помощи **Java-конфигурации** и аннотаций (`@Configuration`, `@Bean`, `@PropertySource`, `@Value`, `@PostConstruct`, `@PreDestroy`).

### Реализованный вариант
* **Вариант:** 1 — «Музыкальный плеер» (`MusicPlayer`).
* **Интерфейс зависимости:** `Music` с методом `getSong()`.
* **Имплементации интерфейса:**
  * `RockMusic` (содержит уникальное поле `bandName`, инициализируемое через конструктор).
  * `ClassicalMusic` (содержит уникальное поле `composer`, создается через **фабричный метод**).
* **Методы жизненного цикла:** класс `MusicPlayer` снабжен init- и destroy-методами (`@PostConstruct` и `@PreDestroy`).



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
        │   ├── RockMusic.java
        │   └── SpringConfig.java
        └── resources
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
Init method: MusicPlayer initialized
Модель плеера: Sony Walkman Pro
Уровень громкости: 85%
Сейчас играет: Рок-трек группы Queen
Destroy method: MusicPlayer destroyed
