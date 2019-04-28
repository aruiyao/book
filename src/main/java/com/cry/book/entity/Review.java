package com.cry.book.entity;

public class Review {

    private Integer id;

    private Integer userId;

    private String userName;

    private Integer bookId;

    private String bookName;

    private Double score;

    private String review;

    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
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
        builder.append("Review [id=");
        builder.append(id);
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", userName=");
        builder.append(userName);
        builder.append(", bookId=");
        builder.append(bookId);
        builder.append(", bookName=");
        builder.append(bookName);
        builder.append(", score=");
        builder.append(score);
        builder.append(", review=");
        builder.append(review);
        builder.append(", createTime=");
        builder.append(createTime);
        builder.append("]");
        return builder.toString();
    }

}
