package com.blejson.webapp.domain;

import javax.persistence.*;

/**
 * Created by Blejson on 12.08.2020
 */
@Entity
public class PostMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String message;
    @ManyToOne
    private User author;

    public PostMessage() {
    }

    public PostMessage(String message, User author) {
        this.message = message;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
