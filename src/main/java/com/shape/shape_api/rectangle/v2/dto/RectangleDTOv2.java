package com.shape.shape_api.rectangle.v2.dto;

public class RectangleDTOv2 {
    private Long a;
    private Long b;

    public RectangleDTOv2(Long a, Long b) {
        this.a = a;
        this.b = b;
    }

    public Long getA() {
        return this.a;
    }

    public Long getB() {
        return this.b;
    }

    public void setA(Long a) {
        this.a = a;
    }

    public void setB(Long b) {
        this.b = b;
    }
}
