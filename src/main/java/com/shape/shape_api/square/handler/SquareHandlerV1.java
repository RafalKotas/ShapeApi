package com.shape.shape_api.square.handler;

import com.shape.shape_api.shape.handler.ShapeHandler;
import com.shape.shape_api.shape.repository.ShapeRepository;
import com.shape.shape_api.square.dto.SquareDtoInV1;
import com.shape.shape_api.square.mapper.SquareV1Mapper;
import com.shape.shape_api.square.model.Square;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Component;

import java.util.List;

@Hidden
@Component
public class SquareHandlerV1 implements ShapeHandler<SquareDtoInV1, Square> {

    private final ShapeRepository shapeRepository;
    private final SquareV1Mapper squareV1Mapper;

    public SquareHandlerV1(ShapeRepository shapeRepository, SquareV1Mapper squareV1Mapper) {
        this.shapeRepository = shapeRepository;
        this.squareV1Mapper = squareV1Mapper;
    }

    @Override
    public String getKey() {
        return "v1:square";
    }

    @Override
    public List<Square> getAllShapes() {
        return shapeRepository.findAllByShapeType(Square.class).stream()
                .map(Square.class::cast)
                .toList();
    }

    @Override
    public Square createShape(SquareDtoInV1 dto) {
        Square square = squareV1Mapper.mapToEntity(dto);
        return shapeRepository.save(square);
    }
}
