package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-03-16
 */
@Entity
@Table(name = "lessons")
public class Lesson implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "grammar")
    private String grammarContent;

    @Column(name = "kaiwa")
    private String kaiwaContent;

    @Column(name = "matome")
    private String matomeContent;

    @Column(name = "reibun")
    private String reibunContent;

    @Column(name = "kanji")
    private String kanjiContent;

    @Column(name = "ganbarimashou")
    private String ganbarimashouContent;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getGrammarContent() {
        return grammarContent;
    }

    public void setGrammarContent(String grammarContent) {
        this.grammarContent = grammarContent;
    }

    public String getKaiwaContent() {
        return kaiwaContent;
    }

    public void setKaiwaContent(String kaiwaContent) {
        this.kaiwaContent = kaiwaContent;
    }

    public String getMatomeContent() {
        return matomeContent;
    }

    public void setMatomeContent(String matomeContent) {
        this.matomeContent = matomeContent;
    }

    public String getReibunContent() {
        return reibunContent;
    }

    public void setReibunContent(String reibunContent) {
        this.reibunContent = reibunContent;
    }

    public String getKanjiContent() {
        return kanjiContent;
    }

    public void setKanjiContent(String kanjiContent) {
        this.kanjiContent = kanjiContent;
    }

    public String getGanbarimashouContent() {
        return ganbarimashouContent;
    }

    public void setGanbarimashouContent(String ganbarimashouContent) {
        this.ganbarimashouContent = ganbarimashouContent;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
