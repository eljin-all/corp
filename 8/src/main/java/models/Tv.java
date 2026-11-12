package models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

/**
 * Модель сущности «Телевизор» с поддержкой сериализации для JMS.
 */
public class Tv implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Поле бренда не должно быть пустым")
    private String brand;

    @NotEmpty(message = "Поле модели не должно быть пустым")
    private String model;

    @NotEmpty(message = "Укажите технологию экрана (например: OLED, QLED, LED)")
    private String screenTechnology;

    @NotNull(message = "Укажите диагональ экрана")
    @Positive(message = "Диагональ должна быть положительным числом")
    private Double screenDiagonal;

    @NotNull(message = "Укажите цену телевизора")
    @Positive(message = "Цена должна быть больше нуля")
    private Double price;

    private boolean sold = false;

    public Tv() {
    }

    public Tv(Integer id, String brand, String model, String screenTechnology, Double screenDiagonal, Double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.screenTechnology = screenTechnology;
        this.screenDiagonal = screenDiagonal;
        this.price = price;
        this.sold = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getScreenTechnology() {
        return screenTechnology;
    }

    public void setScreenTechnology(String screenTechnology) {
        this.screenTechnology = screenTechnology;
    }

    public Double getScreenDiagonal() {
        return screenDiagonal;
    }

    public void setScreenDiagonal(Double screenDiagonal) {
        this.screenDiagonal = screenDiagonal;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    @Override
    public String toString() {
        return String.format("Tv{id=%d, brand='%s', model='%s', tech='%s', diagonal=%.1f\", price=%.2f руб.}",
                id, brand, model, screenTechnology, screenDiagonal, price);
    }
}