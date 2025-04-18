package com.shape.shape_api.square.v1;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.shape.ShapeHandler;
import com.shape.shape_api.square.SquareRepository;
import com.shape.shape_api.square.v1.dto.SquareDTOv1;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Hidden
@Component
public class SquareHandlerV1 implements ShapeHandler<SquareDTOv1, SquareDTOv1> {

    private final SquareRepository squareRepository;
    private final SquareV1Mapper squareV1Mapper;

    public SquareHandlerV1(SquareRepository squareRepository, SquareV1Mapper squareV1Mapper) {
        this.squareRepository = squareRepository;
        this.squareV1Mapper = squareV1Mapper;
    }

    @Override
    public String getKey() {
        return "v1:square";
    }

    @Override
    public List<SquareDTOv1> getAllShapes() {
        return squareRepository.findAll().stream()
                .map(squareV1Mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SquareDTOv1 createShape(SquareDTOv1 squareDTOv1) {
        Square square = squareV1Mapper.mapToEntity(squareDTOv1);
        Square savedSquare = squareRepository.save(square);
        return squareV1Mapper.mapToDto(savedSquare);
    }
}
