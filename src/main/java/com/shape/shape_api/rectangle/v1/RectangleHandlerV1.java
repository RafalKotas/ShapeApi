package com.shape.shape_api.rectangle.v1;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.RectangleRepository;
import com.shape.shape_api.rectangle.v1.dto.RectangleDTOv1;
import com.shape.shape_api.shape.ShapeHandler;

import java.util.List;

public class RectangleHandlerV1 implements ShapeHandler<RectangleDTOv1> {

    private final RectangleRepository rectangleRepository;

    public RectangleHandlerV1(RectangleRepository rectangleRepository) {
        this.rectangleRepository = rectangleRepository;
    }

    @Override
    public String getKey() {
        return "v1:rectangle";
    }

    @Override
    public List<RectangleDTOv1> getAllShapes() {
        return rectangleRepository.findAll().stream()
                .map(rectangle -> new RectangleDTOv1(rectangle.getHeight(), rectangle.getWidth()))
                .toList();
    }

    @Override
    public RectangleDTOv1 createShape(RectangleDTOv1 rectangleDTOv1) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(rectangleDTOv1.getHeight());
        rectangle.setWidth(rectangleDTOv1.getWidth());

        Rectangle savedRectangle = rectangleRepository.save(rectangle);

        return new RectangleDTOv1(savedRectangle.getHeight(), savedRectangle.getWidth());
    }
}
