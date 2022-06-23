package models;

public class Product {
    private String url;
    private String name;
    private String code;
    private String highlight = "";
    private String imageUrl = "";
    private String collection = "";
    private float price;

    public Product(String url, String name, String code, float price) {
        this.url = url;
        this.name = name;
        this.code = code;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "url: " + this.url + "\n" +
                "name: " + this.name + "\n" +
                "code: " + this.code + "\n" +
                "price: " + this.price + "\n" +
                "collection: " + this.collection + "\n" +
                "imageUrl: " + this.imageUrl + "\n" +
                "highlight: " + this.highlight.length() + "\n";
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }
}
