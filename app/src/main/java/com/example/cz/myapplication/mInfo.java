package com.example.cz.myapplication;

/**
 * Created by cz on 2015/6/26.
 */
public class mInfo {
    //课序号
    private String number;
    //课程编码
    private String ser;
    //名称
    private String name;
    //教师
    private String teacher;
    //考试方式
    private String style;
    //考试日期
    private String date;
    //考试时间
 private String time;
    //考场
    private String kaochang;
    //座位编号
    private String zuowei;
    //是否缓考
    private String huankao;

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setSer(String ser) {
        this.ser = ser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setKaochang(String kaochang) {
        this.kaochang = kaochang;
    }

    public void setZuowei(String zuowei) {
        this.zuowei = zuowei;
    }

    public void setHuankao(String huankao) {
        this.huankao = huankao;
    }

    public String getSer() {
        return ser;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getStyle() {
        return style;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getKaochang() {
        return kaochang;
    }

    public String getZuowei() {
        return zuowei;
    }

    public String getHuankao() {
        return huankao;
    }
    @Override
    public String toString() {


        return "mInfo{" +
                "number='" + number + '\'' +
                ", ser='" + ser + '\'' +
                ", name='" + name + '\'' +
                ", teacher='" + teacher + '\'' +
                ", style='" + style + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", kaochang='" + kaochang + '\'' +
                ", zuowei='" + zuowei + '\'' +
                ", huankao='" + huankao + '\'' +
                '}';
    }
}
