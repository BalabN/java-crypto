package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "date",
        "tid",
        "price",
})
public class Transaction {

    @JsonProperty("date")
    private String date;
    @JsonProperty("tid")
    private int tid;
    @JsonProperty("price")
    private String price;

    @JsonProperty("date")
    public int getDate() {
        return Integer.parseInt(date);
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("tid")
    public int getTid() {
        return tid;
    }

    @JsonProperty("tid")
    public void setTid(int tid) {
        this.tid = tid;
    }

    @JsonProperty("price")
    public float getPrice() {
        return Float.parseFloat(price);
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }
}
