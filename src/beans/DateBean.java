package beans;

public class DateBean {

    String name, phone, price, location, id, url, img_url;
    int review_count;
    float rating;

    public DateBean(String name, float rating, String phone, String price, String location, String id, String url, int review_count, String img_url) {
        this.name = name;
        this.rating = rating;
        this.phone = phone;
        this.price = price;
        this.location = location;
        this.id = id;
        this.url = url;
        this.review_count = review_count;
        this.img_url= img_url;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public int getReview_count() {
        return review_count;
    }
    public void setReview_count(int review_count) {
        this.review_count = review_count;
    }

    public String getImg_url() {
        return img_url;
    }
    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Override
    public String toString() {
        return "DateBean{" +
                "name='"          + name     + "'" +
                ", phone='"       + phone    + "'" +
                ", price='"       + price    + "'" +
                ", location='"    + location + "'" +
                ", id='"          + id       + "'" +
                ", url='"         + url      + "'" +
                ", review_count=" + review_count +
                ", rating="       + rating +
                "}";
    }
}
