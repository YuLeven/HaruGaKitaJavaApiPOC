package models;



import dao.KanjiDAO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-01-07
 */
@Entity
@Table(name = "kanjis")
@NamedQueries({
    @NamedQuery(
        name= KanjiDAO.FIND_BY_KANJI, query="FROM Kanji k WHERE k.kanji = :kanji"
    ),
    @NamedQuery(
        name=KanjiDAO.FIND_ALL, query="FROM Kanji k"
    )
})
public class Kanji implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String kanji;
    private String jlpt;
    private String school;
    private String tracos;
    private String gakken;
    private String onyomi;
    private String kunyomi;
    private String english;
    private String portuguese;

    public Kanji() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public String getJlpt() {
        return jlpt;
    }

    public void setJlpt(String jlpt) {
        this.jlpt = jlpt;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTracos() {
        return tracos;
    }

    public void setTracos(String tracos) {
        this.tracos = tracos;
    }

    public String getGakken() {
        return gakken;
    }

    public void setGakken(String gakken) {
        this.gakken = gakken;
    }

    public String getOnyomi() {
        return onyomi;
    }

    public void setOnyomi(String onyomi) {
        this.onyomi = onyomi;
    }

    public String getKunyomi() {
        return kunyomi;
    }

    public void setKunyomi(String kunyomi) {
        this.kunyomi = kunyomi;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getPortuguese() {
        return portuguese;
    }

    public void setPortuguese(String portuguese) {
        this.portuguese = portuguese;
    }
}
