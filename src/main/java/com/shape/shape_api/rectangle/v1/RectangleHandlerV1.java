package com.shape.shape_api.rectangle.v1;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.RectangleRepository;
import com.shape.shape_api.rectangle.v1.dto.RectangleDTOv1;
import com.shape.shape_api.shape.ShapeHandler;

import java.util.List;

public class RectangleHandlerV1 implements ShapeHandler<RectangleDTOv1, Rectangle> {

    private final RectangleRepository rectangleRepository;
    private final RectangleV1Mapper rectangleV1Mapper;

    public RectangleHandlerV1(RectangleRepository rectangleRepository, RectangleV1Mapper rectangleV1Mapper) {
        this.rectangleRepository = rectangleRepository;
        this.rectangleV1Mapper = rectangleV1Mapper;
    }

    @Override
    public String getKey() {
        return "v1:rectangle";
    }

    @Override
    public List<Rectangle> getAllShapes() {
        return rectangleRepository.findAll();
    }

    @Override
    public Rectangle createShape(RectangleDTOv1 rectangleDTOv1) {
        Rectangle rectangle = rectangleV1Mapper.mapToEntity(rectangleDTOv1);
        return rectangleRepository.save(rectangle);
    }
}
