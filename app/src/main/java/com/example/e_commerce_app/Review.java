package com.example.e_commerce_app;

public class Review {
    private int id;
    private String reviewText;
    private String rivewDate;

    public Review(int id, String reviewText, String rivewDate) {
        this.id = id;
        this.reviewText = reviewText;
        this.rivewDate = rivewDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getRivewDate() {
        return rivewDate;
    }

    public void setRivewDate(String rivewDate) {
        this.rivewDate = rivewDate;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", reviewText='" + reviewText + '\'' +
                ", rivewDate='" + rivewDate + '\'' +
                '}';
    }
}
