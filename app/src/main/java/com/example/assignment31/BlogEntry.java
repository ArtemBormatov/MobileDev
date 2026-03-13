package com.example.assignment31;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BlogEntry {
    private String userName;
    private String comment;
    private Date date;

    public BlogEntry(String userName, String comment) {
        this.userName = userName;
        this.comment = comment;
        this.date = new Date(); // submission time
    }

    public String getUserName() { return userName; }
    public String getComment() { return comment; }
    public Date getDate() { return date; }

    public boolean searchByText(String text) {
        text = text.toLowerCase();
        return userName.toLowerCase().contains(text) || comment.toLowerCase().contains(text);
    }

    public boolean searchByDate(Date searchDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(date).equals(sdf.format(searchDate));
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return "User: " + userName + "\n" +
                "Comment: " + comment + "\n" +
                "Date: " + sdf.format(date) + "\n-----------------\n";
    }
}