package xyz.likailing.cloud.common.base.util;

import java.io.Serializable;

public class WOD<T> implements Comparable<WOD<T>>, Serializable {
    private static final long serialVersionUID = -2317609898674927526L;
    private T obj;
    private double score;
    private String nature = "";

    public WOD() {
    }

    public WOD(T obj) {
        this.obj = obj;
    }

    public WOD(T obj, double score) {
        this.obj = obj;
        this.score = score;
    }

    public WOD(T obj, String nature) {
        this.obj = obj;
        this.nature = nature;
    }

    public WOD(T obj, double score, String nature) {
        this.obj = obj;
        this.score = score;
        this.nature = nature;
    }

    public String getName() {
        return this.obj.toString();
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return this.obj;
    }

    public double getScore() {
        return this.score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getNature() {
        return this.nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String toString() {
        return this.getName() + "/" + this.score;
    }

    public String toDetailString() {
        return this.getName() + "/" + this.nature + "/" + this.score;
    }

    public String toSimpleString() {
        return this.getName();
    }

    public int compareTo(WOD<T> o) {
        if (this.score > o.score) {
            return -1;
        } else {
            return this.score == o.score ? 0 : 1;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof WOD) {
            WOD w = (WOD)obj;
            return w.getName().equals(this.getName());
        } else {
            return false;
        }
    }
}