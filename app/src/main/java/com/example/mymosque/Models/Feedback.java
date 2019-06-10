package com.example.mymosque.Models;

import com.google.gson.annotations.SerializedName;

public class Feedback
{
    @SerializedName("message")
    private String message;

    public Feedback(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
