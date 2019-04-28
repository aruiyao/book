package com.cry.book.service;

import java.util.List;

import com.cry.book.data.QueryReviewByCondReq;
import com.cry.book.entity.Review;

public interface ReviewService {

    List < Review > queryReviewByCond(QueryReviewByCondReq req);

    int queryReviewNum(QueryReviewByCondReq req);

    public void addReview(Review review);
}