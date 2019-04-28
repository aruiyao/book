package com.cry.book.data;

import java.util.List;

import com.cry.book.entity.Review;

public class QueryReviewByCondResp {

    private List < Review > reviewList;

    private int total;

    public List < Review > getReviewList() {
        return reviewList;
    }

    public void setReviewList(List < Review > reviewList) {
        this.reviewList = reviewList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("QueryReviewByCondResp [reviewList=");
        builder.append(reviewList);
        builder.append(", total=");
        builder.append(total);
        builder.append("]");
        return builder.toString();
    }

}
