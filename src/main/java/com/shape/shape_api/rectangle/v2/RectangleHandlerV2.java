package com.shape.shape_api.rectangle.v2;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.RectangleRepository;
import com.shape.shape_api.rectangle.v2.dto.RectangleDTOv2;
import com.shape.shape_api.shape.ShapeHandler;

import java.util.List;

public class RectangleHandlerV2 implements ShapeHandler<RectangleDTOv2> {

    private final RectangleRepository rectangleRepository;

    public RectangleHandlerV2(RectangleRepository rectangleRepository) {
        this.rectangleRepository = rectangleRepository;
    }

    @Override
    public String getKey() {
        return "v2:rectangle";
    }

    @Override
    public List<RectangleDTOv2> getAllShapes() {
        return rectangleRepository.findAll().stream()
                .map(rectangle -> new RectangleDTOv2(rectangle.getHeight(), rectangle.getWidth()))
                .toList();
    }

    @Override
    public RectangleDTOv2 createShape(RectangleDTOv2 rectangleDTOv2) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(rectangleDTOv2.getA());
        rectangle.setWidth(rectangleDTOv2.getB());

        Rectangle savedRectangle = rectangleRepository.save(rectangle);

        return new RectangleDTOv2(savedRectangle.getHeight(), savedRectangle.getWidth());
    }
}
