package com.shape.shape_api.rectangle.v2;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.RectangleRepository;
import com.shape.shape_api.rectangle.v2.dto.RectangleDTOv2;
import com.shape.shape_api.shape.ShapeHandler;

import java.util.List;

public class RectangleHandlerV2 implements ShapeHandler<RectangleDTOv2, Rectangle> {

    private final RectangleRepository rectangleRepository;

    public RectangleHandlerV2(RectangleRepository rectangleRepository) {
        this.rectangleRepository = rectangleRepository;
    }

    @Override
    public String getKey() {
        return "v2:rectangle";
    }

    @Override
    public List<Rectangle> getAllShapes() {
        return rectangleRepository.findAll();
    }

    @Override
    public Rectangle createShape(RectangleDTOv2 rectangleDTOv2) {
        Rectangle rectangle = new Rectangle(rectangleDTOv2.getA(), rectangleDTOv2.getB());

        return rectangleRepository.save(rectangle);
    }
}
