package com.shape.shape_api.square.v2;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.shape.ShapeHandler;
import com.shape.shape_api.square.SquareRepository;
import com.shape.shape_api.square.v2.dto.SquareDTOv2;

import java.util.List;

public class SquareHandlerV2 implements ShapeHandler<SquareDTOv2, Square> {

    private final SquareRepository squareRepository;

    public SquareHandlerV2(SquareRepository squareRepository) {
        this.squareRepository = squareRepository;
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
        Square square = new Square(squareDTOv2.getA());

        return squareRepository.save(square);
    }
}
