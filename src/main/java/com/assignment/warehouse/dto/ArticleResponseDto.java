package com.assignment.warehouse.dto;

public class ArticleResponseDto {

    private String id;
    private String artName;
    private Integer count;

    public ArticleResponseDto() {
    }

    public ArticleResponseDto(
            String id,
            String artName,
            Integer count) {

        this.id = id;
        this.artName = artName;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public String getArtName() {
        return artName;
    }

    public Integer getCount() {
        return count;
    }
}