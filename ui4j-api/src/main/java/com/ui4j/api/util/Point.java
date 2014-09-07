package com.ui4j.api.util;

public class Point {

    private int top;

    private int left;

    public Point() {
    }

    public Point(int top, int left) {
        this.top = top;
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + left;
        result = prime * result + top;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Point other = (Point) obj;
        if (left != other.left)
            return false;
        if (top != other.top)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Point [top=" + top + ", left=" + left + "]";
    }
}
