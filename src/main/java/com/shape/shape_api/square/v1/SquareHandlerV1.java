package com.shape.shape_api.square.v1;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.shape.ShapeHandler;
import com.shape.shape_api.shape.ShapeRepository;
import com.shape.shape_api.square.v1.dto.SquareDtoInV1;
import com.shape.shape_api.square.v1.dto.SquareDtoOutV1;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Component;

import java.util.List;

@Hidden
@Component
public class SquareHandlerV1 implements ShapeHandler<SquareDtoInV1, SquareDtoOutV1> {

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
    public List<SquareDtoOutV1> getAllShapes() {
        return shapeRepository.findAllByShapeType(Square.class).stream()
                .map(shape -> (Square) shape)
                .map(squareV1Mapper::mapToDTO)
                .toList();
    }

    @Override
    public SquareDtoOutV1 createShape(SquareDtoInV1 dto) {
        Square square = squareV1Mapper.mapToEntity(dto);
        Square saved = shapeRepository.save(square);
        return squareV1Mapper.mapToDTO(saved);
    }
}
