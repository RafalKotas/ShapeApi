package com.shape.shape_api.square.v2;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.shape.ShapeHandler;
import com.shape.shape_api.square.SquareRepository;
import com.shape.shape_api.square.v2.dto.SquareDTOv2;

import java.util.List;

public class SquareHandlerV2 implements ShapeHandler<SquareDTOv2, Square> {

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
    public List<Square> getAllShapes() {
        return squareRepository.findAll();
    }

    @Override
    public Square createShape(SquareDTOv2 squareDTOv2) {
        Square square = squareV2Mapper.mapToEntity(squareDTOv2);

        return squareRepository.save(square);
    }
}
