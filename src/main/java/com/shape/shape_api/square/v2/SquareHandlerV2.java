package com.shape.shape_api.square.v2;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.shape.ShapeHandler;
import com.shape.shape_api.shape.ShapeRepository;
import com.shape.shape_api.square.v2.dto.SquareDTOv2;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Component;

import java.util.List;

@Hidden
@Component
public class SquareHandlerV2 implements ShapeHandler<SquareDTOv2, SquareDTOv2> {

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
    public List<SquareDTOv2> getAllShapes() {
        return shapeRepository.findAllByShapeType(Square.class).stream()
                .map(shape -> (Square) shape)
                .map(squareV2Mapper::mapToDTO)
                .toList();
    }

    @Override
    public SquareDTOv2 createShape(SquareDTOv2 squareDTOv2) {
        Square square = squareV2Mapper.mapToEntity(squareDTOv2);
        Square savedSquare = shapeRepository.save(square);
        return squareV2Mapper.mapToDTO(savedSquare);
    }
}
