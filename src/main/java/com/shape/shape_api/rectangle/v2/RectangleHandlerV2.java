package com.shape.shape_api.rectangle.v2;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.RectangleRepository;
import com.shape.shape_api.rectangle.v2.dto.RectangleDTOv2;
import com.shape.shape_api.shape.ShapeHandler;

import java.util.List;

public class RectangleHandlerV2 implements ShapeHandler<RectangleDTOv2, Rectangle> {

    private final RectangleRepository rectangleRepository;
    private final RectangleV2Mapper rectangleV2Mapper;

    public RectangleHandlerV2(RectangleRepository rectangleRepository, RectangleV2Mapper rectangleV2Mapper) {
        this.rectangleRepository = rectangleRepository;
        this.rectangleV2Mapper = rectangleV2Mapper;
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
        Rectangle rectangle = rectangleV2Mapper.mapToEntity(rectangleDTOv2);

        return rectangleRepository.save(rectangle);
    }
}
