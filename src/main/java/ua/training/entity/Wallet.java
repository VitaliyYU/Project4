package ua.training.entity;

/**
 * Created by vitaliy on 21.05.17.
 */
public class Wallet {
    private Integer id;
    private String code;
    private Double count;

    public Wallet() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

}
