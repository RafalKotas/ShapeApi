package com.shape.shape_api.square.v1;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.shape.ShapeHandler;
import com.shape.shape_api.square.SquareRepository;
import com.shape.shape_api.square.v1.dto.SquareDTOv1;

import java.util.List;

public class SquareHandlerV1 implements ShapeHandler<SquareDTOv1, Square> {

    private final SquareRepository squareRepository;

    public SquareHandlerV1(SquareRepository squareRepository) {
        this.squareRepository = squareRepository;
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
        Square square = new Square(squareDTOv1.getA());

        return squareRepository.save(square);

    }
}
