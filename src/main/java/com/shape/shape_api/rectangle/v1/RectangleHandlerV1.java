package com.shape.shape_api.rectangle.v1;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.RectangleRepository;
import com.shape.shape_api.rectangle.v1.dto.RectangleDTOv1;
import com.shape.shape_api.shape.ShapeHandler;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Hidden
@Component
public class RectangleHandlerV1 implements ShapeHandler<RectangleDTOv1, RectangleDTOv1> {

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
    public List<RectangleDTOv1> getAllShapes() {
        return rectangleRepository.findAll().stream()
                .map(rectangleV1Mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RectangleDTOv1 createShape(RectangleDTOv1 rectangleDTOv1) {
        Rectangle rectangle = rectangleV1Mapper.mapToEntity(rectangleDTOv1);
        Rectangle savedRectangle = rectangleRepository.save(rectangle);
        return rectangleV1Mapper.mapToDto(savedRectangle);
    }
}
