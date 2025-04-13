package com.shape.shape_api.square.v1;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.shape.ShapeHandler;
import com.shape.shape_api.square.SquareRepository;
import com.shape.shape_api.square.v1.dto.SquareDTOv1;

import java.util.List;

public class SquareHandlerV1 implements ShapeHandler<SquareDTOv1> {

    private final SquareRepository squareRepository;

    public SquareHandlerV1(SquareRepository squareRepository) {
        this.squareRepository = squareRepository;
    }

    @Override
    public String getKey() {
        return "v1:square";
    }

    @Override
    public List<SquareDTOv1> getAllShapes() {
        return squareRepository.findAll().stream()
                .map(square -> new SquareDTOv1(square.getA()))
                .toList();
    }

    @Override
    public SquareDTOv1 createShape(SquareDTOv1 squareDTOv1) {
        Square square = new Square();
        square.setA(squareDTOv1.getA());

        Square savedSquare = squareRepository.save(square);

        return new SquareDTOv1(savedSquare.getA());
    }
}
