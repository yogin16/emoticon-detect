package com.emoticon;

import java.io.Serializable;

/**
 * Date: 05/12/16
 * Time: 12:05 AM
 *
 * @author yogin
 */
public class Emoticon implements Serializable {
    private static final long serialVersionUID = 42L;

    private String value;
    private int start;
    private int end;

    public Emoticon() {
    }

    public Emoticon(String value, int start, int end) {
        this.value = value;
        this.start = start;
        this.end = end;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Emoticon{" +
                "value='" + value + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
