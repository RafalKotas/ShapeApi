package com.shape.shape_api.square.v2;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.shape.ShapeHandler;
import com.shape.shape_api.square.SquareRepository;
import com.shape.shape_api.square.v2.dto.SquareDTOv2;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Hidden
@Component
public class SquareHandlerV2 implements ShapeHandler<SquareDTOv2, SquareDTOv2> {

    private final SquareRepository squareRepository;
    private final SquareV2Mapper squareV2Mapper;

    public SquareHandlerV2(SquareRepository squareRepository, SquareV2Mapper squareV2Mapper) {
        this.squareRepository = squareRepository;
        this.squareV2Mapper = squareV2Mapper;
    }

    @Override
    public String getKey() {
        return "v2:square";
    }

    @Override
    public List<SquareDTOv2> getAllShapes() {
        return squareRepository.findAll().stream()
                .map(squareV2Mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SquareDTOv2 createShape(SquareDTOv2 squareDTOv2) {
        Square square = squareV2Mapper.mapToEntity(squareDTOv2);
        Square savedSquare = squareRepository.save(square);
        return squareV2Mapper.mapToDto(savedSquare);
    }
}
