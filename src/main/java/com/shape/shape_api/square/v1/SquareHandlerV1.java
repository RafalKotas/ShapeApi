package com.shape.shape_api.square.v1;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.shape.ShapeHandler;
import com.shape.shape_api.shape.ShapeRepository;
import com.shape.shape_api.square.v1.dto.SquareDTOv1;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Component;

import java.util.List;

@Hidden
@Component
public class SquareHandlerV1 implements ShapeHandler<SquareDTOv1, SquareDTOv1> {

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
    public List<SquareDTOv1> getAllShapes() {
        return shapeRepository.findAllByShapeType(Square.class).stream()
                .map(shape -> (Square) shape)
                .map(squareV1Mapper::mapToDTO)
                .toList();
    }

    @Override
    public SquareDTOv1 createShape(SquareDTOv1 squareDTOv1) {
        Square square = squareV1Mapper.mapToEntity(squareDTOv1);
        Square savedSquare = shapeRepository.save(square);
        return squareV1Mapper.mapToDTO(savedSquare);
    }
}
