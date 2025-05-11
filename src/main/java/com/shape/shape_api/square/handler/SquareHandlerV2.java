package com.shape.shape_api.square.handler;

import com.shape.shape_api.shape.handler.ShapeHandler;
import com.shape.shape_api.shape.repository.ShapeRepository;
import com.shape.shape_api.square.dto.SquareDtoInV2;
import com.shape.shape_api.square.mapper.SquareV2Mapper;
import com.shape.shape_api.square.model.Square;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Component;

import java.util.List;

@Hidden
@Component
public class SquareHandlerV2 implements ShapeHandler<SquareDtoInV2, Square> {

    private final ShapeRepository shapeRepository;
    private final SquareV2Mapper squareV2Mapper;

    public SquareHandlerV2(ShapeRepository shapeRepository, SquareV2Mapper squareV2Mapper) {
        this.shapeRepository = shapeRepository;
        this.squareV2Mapper = squareV2Mapper;
    }

    @Override
    public String getKey() {
        return "v2:square";
    }

    @Override
    public List<Square> getAllShapes() {
        return shapeRepository.findAllByShapeType(Square.class).stream()
                .map(Square.class::cast)
                .toList();
    }

    @Override
    public Square createShape(SquareDtoInV2 dto) {
        Square entity = squareV2Mapper.mapToEntity(dto);
        return shapeRepository.save(entity);
    }
}
