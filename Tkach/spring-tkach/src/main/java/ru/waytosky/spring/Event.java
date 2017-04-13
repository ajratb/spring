/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.spring;

import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author Ayrat
 */
public class Event {
    int id;//Auto-generated
    String msg;
    Date date;
    DateFormat df;

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
    }    
     
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", msg=" + msg + ", date=" 
                + df.format(date) + '}';
    }
}
