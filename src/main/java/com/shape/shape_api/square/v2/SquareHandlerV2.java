package com.shape.shape_api.square.v2;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.shape.ShapeHandler;
import com.shape.shape_api.shape.ShapeRepository;
import com.shape.shape_api.square.v2.dto.SquareDtoInV2;
import com.shape.shape_api.square.v2.dto.SquareDtoOutV2;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Component;

import java.util.List;

@Hidden
@Component
public class SquareHandlerV2 implements ShapeHandler<SquareDtoInV2, SquareDtoOutV2, Square> {

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
    public List<SquareDtoOutV2> getAllShapes() {
        return shapeRepository.findAllByShapeType(Square.class).stream()
                .map(shape -> (Square) shape)
                .map(squareV2Mapper::mapToDTO)
                .toList();
    }

    @Override
    public Square createShape(SquareDtoInV2 dto) {
        Square entity = squareV2Mapper.mapToEntity(dto);
        return shapeRepository.save(entity);
    }
}
