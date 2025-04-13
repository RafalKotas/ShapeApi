package com.shape.shape_api.square.v1;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.shape.ShapeHandler;
import com.shape.shape_api.square.SquareRepository;
import com.shape.shape_api.square.v1.dto.SquareDTOv1;

import java.util.List;

public class SquareHandlerV1 implements ShapeHandler<SquareDTOv1, Square> {

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
    public List<Square> getAllShapes() {
        return squareRepository.findAll();
    }

    @Override
    public Square createShape(SquareDTOv1 squareDTOv1) {
        Square square = squareV1Mapper.mapToEntity(squareDTOv1);

        return squareRepository.save(square);
    }
}
