public class Tv {
    private Integer id;
    private String brand;
    private String model;
    private String screenTechnology;
    private Double screenDiagonal;
    private Double price;

    public Tv() {
    }

    public Tv(Integer id, String brand, String model, String screenTechnology, Double screenDiagonal, Double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.screenTechnology = screenTechnology;
        this.screenDiagonal = screenDiagonal;
        this.price = price;
    }

    public Tv(String brand, String model, String screenTechnology, Double screenDiagonal, Double price) {
        this(null, brand, model, screenTechnology, screenDiagonal, price);
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

    @Override
    public String toString() {
        return String.format("ID: %-3d | Бренд: %-10s | Модель: %-12s | Экран: %-8s | Диагональ: %4.1f\" | Цена: %9.2f руб.",
                id, brand, model, screenTechnology, screenDiagonal, price);
    }
}