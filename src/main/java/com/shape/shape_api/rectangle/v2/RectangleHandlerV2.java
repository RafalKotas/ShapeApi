package com.shape.shape_api.rectangle.v2;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.RectangleRepository;
import com.shape.shape_api.rectangle.v2.dto.RectangleDTOv2;
import com.shape.shape_api.shape.ShapeHandler;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Hidden
@Component
public class RectangleHandlerV2 implements ShapeHandler<RectangleDTOv2, RectangleDTOv2> {

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
    public List<RectangleDTOv2> getAllShapes() {
        return rectangleRepository.findAll().stream()
                .map(rectangleV2Mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RectangleDTOv2 createShape(RectangleDTOv2 rectangleDTOv2) {
        Rectangle rectangle = rectangleV2Mapper.mapToEntity(rectangleDTOv2);
        Rectangle savedRectangle = rectangleRepository.save(rectangle);
        return rectangleV2Mapper.mapToDto(savedRectangle);
    }
}
