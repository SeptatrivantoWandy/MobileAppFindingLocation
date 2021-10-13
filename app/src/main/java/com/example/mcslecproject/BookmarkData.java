package com.example.mcslecproject;

public class BookmarkData {
    private String bookmarkId;
    private String bookmarkUserId;
    private String bookmarkAttractionId;

    public BookmarkData(String bookmarkId, String bookmarkUserId, String bookmarkAttractionId) {
        this.bookmarkId = bookmarkId;
        this.bookmarkUserId = bookmarkUserId;
        this.bookmarkAttractionId = bookmarkAttractionId;
    }

    public String getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(String bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public String getBookmarkUserId() {
        return bookmarkUserId;
    }

    public void setBookmarkUserId(String bookmarkUserId) {
        this.bookmarkUserId = bookmarkUserId;
    }

    public String getBookmarkAttractionId() {
        return bookmarkAttractionId;
    }

    public void setBookmarkAttractionId(String bookmarkAttractionId) {
        this.bookmarkAttractionId = bookmarkAttractionId;
    }
}
