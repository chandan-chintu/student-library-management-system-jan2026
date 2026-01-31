package com.example.student_library_management_system.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="book")
public class Book {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="publisher_name", nullable = false)
    private String publisherName;

    @Column(name="published_date", nullable = false)
    private String publishedDate;

    @Column(name="pages", nullable = false)
    private int pages;

    @Column(name="availability", nullable = false)
    private boolean availability;

    @Column(name="category", nullable = false)
    private String category;

    @Column(name="rack_no", nullable = false)
    private String rackNo;

    @JsonBackReference
    @JoinColumn
    @ManyToOne
    private Card card;

    @JsonManagedReference
    @OneToMany(mappedBy = "book")
    private List<Transaction> transactionList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRackNo() {
        return rackNo;
    }

    public void setRackNo(String rackNo) {
        this.rackNo = rackNo;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
