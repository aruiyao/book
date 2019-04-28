package com.cry.book.entity;

public class Book {

    private Integer id;

    private String name;

    private String picture;

    private String author;

    private String press;

    private String releaseTime;

    private String page;

    private String type;

    private String price;

    /**影片评分*/
    private Double score;

    private String bookBrife;

    private String authBrife;

    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getBookBrife() {
        return bookBrife;
    }

    public void setBookBrife(String bookBrife) {
        this.bookBrife = bookBrife;
    }

    public String getAuthBrife() {
        return authBrife;
    }

    public void setAuthBrife(String authBrife) {
        this.authBrife = authBrife;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Book [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", picture=");
        builder.append(picture);
        builder.append(", author=");
        builder.append(author);
        builder.append(", press=");
        builder.append(press);
        builder.append(", releaseTime=");
        builder.append(releaseTime);
        builder.append(", page=");
        builder.append(page);
        builder.append(", type=");
        builder.append(type);
        builder.append(", price=");
        builder.append(price);
        builder.append(", score=");
        builder.append(score);
        builder.append(", bookBrife=");
        builder.append(bookBrife);
        builder.append(", authBrife=");
        builder.append(authBrife);
        builder.append(", createTime=");
        builder.append(createTime);
        builder.append("]");
        return builder.toString();
    }

}
