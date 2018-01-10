package c4q.nyc.bookstore;

/**
 * Created by franciscoandrade on 12/18/17.
 */

import java.io.Serializable;

/**
 * Created by franciscoandrade on 12/14/17.
 */

public class Model implements Serializable {
    private String id, name, author, series_t, genre_s;
    private String[] cat;
    private int sequence_i, pages_i;
    private Boolean inStock;
    private double price;

    public Model(String id, String name, String author, String series_t, int sequence_i, String genre_s, Boolean inStock, double price, int pages_i) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.series_t = series_t;
        this.sequence_i = sequence_i;
        this.genre_s = genre_s;
        this.inStock = inStock;
        this.price = price;
        this.pages_i = pages_i;
    }

    public Model(String id, String name, String author, int sequence_i, String genre_s, Boolean inStock, double price, int pages_i) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.sequence_i = sequence_i;
        this.genre_s = genre_s;
        this.inStock = inStock;
        this.price = price;
        this.pages_i = pages_i;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getCat() {
        return cat;
    }

    public void setCat(String[] cat) {
        this.cat = cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSeries_t() {
        return series_t;
    }

    public void setSeries_t(String series_t) {
        this.series_t = series_t;
    }

    public int getSequence_i() {
        return sequence_i;
    }

    public void setSequence_i(int sequence_i) {
        this.sequence_i = sequence_i;
    }

    public String getGenre_s() {
        return genre_s;
    }

    public void setGenre_s(String genre_s) {
        this.genre_s = genre_s;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPages_i() {
        return pages_i;
    }

    public void setPages_i(int pages_i) {
        this.pages_i = pages_i;
    }
}

