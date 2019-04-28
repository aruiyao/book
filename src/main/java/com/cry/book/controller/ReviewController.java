package com.cry.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cry.book.data.QueryReviewByCondReq;
import com.cry.book.data.QueryReviewByCondResp;
import com.cry.book.entity.Book;
import com.cry.book.entity.Review;
import com.cry.book.entity.User;
import com.cry.book.service.BookService;
import com.cry.book.service.ReviewService;

@Controller
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private BookService bookService;

    @PostMapping(value = "/queryReviewByCond")
    @ResponseBody
    public QueryReviewByCondResp queryReviewByCond(@RequestBody QueryReviewByCondReq req) {
        System.out.println(req);
        QueryReviewByCondResp resp = new QueryReviewByCondResp();
        List < Review > reviewList = reviewService.queryReviewByCond(req);
        int total = reviewService.queryReviewNum(req);
        resp.setReviewList(reviewList);
        resp.setTotal(total);
        return resp;
    }

    @PostMapping(value = "/addReview")
    @ResponseBody
    public void addReview(@RequestBody Review review) {
        reviewService.addReview(review);
        QueryReviewByCondReq req = new QueryReviewByCondReq();
        Book book =  new Book();
        User user = new User();
        book.setId(review.getBookId());
        req.setBook(book);
        req.setUser(user);
        Double totalScore = 0.0;
        Double score = 0.0;
        List < Review > reviewList = reviewService.queryReviewByCond(req);
        for (Review eachReview : reviewList) {
            totalScore = totalScore + eachReview.getScore();
        }
        score = totalScore / reviewList.size();
        System.out.println("score=" + score);
        book.setScore(score);
        System.out.println(book);
        bookService.updateBook(book);
    }

}
