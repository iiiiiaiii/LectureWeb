package com.example.demo.dto;

import lombok.Data;

@Data
public class BookDTO {
    private String name;
    private String author;
    private int price;
    private int stock;
    private String lecturer;
}
