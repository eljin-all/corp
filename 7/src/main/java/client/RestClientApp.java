package client;

import models.Tv;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;

/**
 * Консольный REST-клиент на основе RestTemplate для демонстрации
 * операций GET, POST, PUT и DELETE.
 */
public class RestClientApp {

    private static final String BASE_URL = "http://localhost:8080/tv-web-app/tvs";
    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) throws Exception {
        System.out.println("==================================================");
        System.out.println("   ТЕСТИРОВАНИЕ REST API (RestTemplate Client)    ");
        System.out.println("==================================================\n");

        // 1. GET всех записей (JSON)
        System.out.println("1. [GET /tvs] Извлечение всех телевизоров из БД:");
        Tv[] allTvs = getAllTvs();
        printTvArray(allTvs);

        // 2. POST — добавление новой записи
        System.out.println("\n2. [POST /tvs] Добавление нового телевизора (Xiaomi Mi TV Q1):");
        Tv newTv = new Tv(null, "Xiaomi", "Mi TV Q1", "QLED", 55.0, 54990.0);
        Tv createdTv = createTv(newTv);
        System.out.println("   -> Создан ресурс: " + formatTv(createdTv));

        int targetId = createdTv.getId();

        // 3. GET одной записи по ID (JSON)
        System.out.println("\n3. [GET /tvs/" + targetId + "] Извлечение созданной записи по ID:");
        Tv fetchedTv = getTvById(targetId);
        System.out.println("   -> Получен ресурс: " + formatTv(fetchedTv));

        // 4. PUT — обновление записи по ID
        System.out.println("\n4. [PUT /tvs/" + targetId + "] Обновление модели и цены телевизора:");
        fetchedTv.setModel("Mi TV Q1 Pro (Updated)");
        fetchedTv.setPrice(49990.0);
        updateTv(targetId, fetchedTv);
        Tv updatedTv = getTvById(targetId);
        System.out.println("   -> После обновления: " + formatTv(updatedTv));

        // 5. DELETE — удаление записи по ID
        System.out.println("\n5. [DELETE /tvs/" + targetId + "] Удаление телевизора с ID = " + targetId + ":");
        deleteTv(targetId);
        System.out.println("   -> Запись успешно удалена.");

        // 6. Итоговая проверка списка (GET всех записей)
        System.out.println("\n6. [GET /tvs] Контрольная проверка списка после удаления:");
        printTvArray(getAllTvs());

        System.out.println("\n==================================================");
        System.out.println("        ТЕСТИРОВАНИЕ REST-КЛИЕНТА ЗАВЕРШЕНО       ");
        System.out.println("==================================================");
    }

    /**
     * GET-запрос для получения списка всех ресурсов.
     */
    public static Tv[] getAllTvs() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Tv[]> response = restTemplate.exchange(
                BASE_URL, HttpMethod.GET, entity, Tv[].class);
        return response.getBody();
    }

    /**
     * GET-запрос для получения одного ресурса по ID через getForObject().
     */
    public static Tv getTvById(int id) {
        return restTemplate.getForObject(BASE_URL + "/{id}", Tv.class, id);
    }

    /**
     * POST-запрос для создания ресурса через postForObject().
     */
    public static Tv createTv(Tv tv) {
        return restTemplate.postForObject(BASE_URL, tv, Tv.class);
    }

    /**
     * PUT-запрос для редактирования ресурса через put().
     */
    public static void updateTv(int id, Tv tv) throws Exception {
        URI uri = new URI(BASE_URL + "/" + id);
        restTemplate.put(uri, tv);
    }

    /**
     * DELETE-запрос для удаления ресурса через delete().
     */
    public static void deleteTv(int id) throws Exception {
        URI uri = new URI(BASE_URL + "/" + id);
        restTemplate.delete(uri);
    }

    private static void printTvArray(Tv[] tvs) {
        if (tvs == null || tvs.length == 0) {
            System.out.println("   [Список пуст]");
            return;
        }
        for (Tv tv : tvs) {
            System.out.println("   • " + formatTv(tv));
        }
    }

    private static String formatTv(Tv tv) {
        if (tv == null) return "null";
        return String.format("Tv{id=%d, brand='%s', model='%s', tech='%s', diagonal=%.1f\", price=%.2f}",
                tv.getId(), tv.getBrand(), tv.getModel(), tv.getScreenTechnology(),
                tv.getScreenDiagonal(), tv.getPrice());
    }
}