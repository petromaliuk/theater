package com.theater.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "seances")
@Data
public class Seance {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "film", nullable = false, referencedColumnName = "id")
    @JsonView(Views.PartSeance.class)
    private Film film;

    @ManyToOne()
    @JoinColumn(name = "hall",nullable = false, referencedColumnName = "id")
    @JsonView(Views.IdName.class)
    private Hall hall;

    @OneToMany(mappedBy = "seance", orphanRemoval = true)
    @JsonView(Views.FullSeance.class)
    private List<Ticket> tickets;

    @Min(0) @Max(1000000000)
    @Column(name = "price", nullable = false)
    @JsonView(Views.IdName.class)
    private int price;

    @Min(1000) @Max(3000)
    @Column(name = "year", nullable = false)
    @JsonView(Views.IdName.class)
    private int year;

    @Min(1) @Max(12)
    @Column(name = "month", nullable = false)
    @JsonView(Views.IdName.class)
    private int month;

    @Min(1) @Max(31)
    @Column(name = "day", nullable = false)
    @JsonView(Views.IdName.class)
    private int day;

    @Min(0) @Max(23)
    @Column(name = "hour", nullable = false)
    @JsonView(Views.IdName.class)
    private int hour;

    @Min(0) @Max(59)
    @Column(name = "minute", nullable = false)
    @JsonView(Views.IdName.class)
    private int minute;


    public boolean isValid(){
        if(film==null) return false;
        if(month%2==0&&day>30) return false;
        if(month==2&&day>29) return false;
        return year % 4 == 0 || month != 2 || day <= 28;
    }



    public String getDate(){
        return formatDecimalInt(year, 4)+'-'+formatDecimalInt(month, 2)+'-'+formatDecimalInt(day, 2);
    }

    public String getTime(){ return formatDecimalInt(hour, 2)+':'+formatDecimalInt(minute, 2); }

    private String formatDecimalInt(int val, int len) {
        char[] buf = new char[len];
        int charPos = len;
        do {
            buf[--charPos] = (char)('0' + (val % 10));
            val /= 10;
        } while (charPos > 0);
        return new String(buf);
    }

    @Override
    public String toString() {
        return "Seance{" +
                "id=" + id +
                ", film=" + film.getId() +
                ", date=" + getDate() +
                ", time=" + getTime() +
                '}';
    }
}

