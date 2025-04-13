package com.shape.shape_api.square.v2;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.shape.ShapeHandler;
import com.shape.shape_api.square.SquareRepository;
import com.shape.shape_api.square.v2.dto.SquareDTOv2;

import java.util.List;

public class SquareHandlerV2 implements ShapeHandler<SquareDTOv2> {

    private final SquareRepository squareRepository;

    public SquareHandlerV2(SquareRepository squareRepository) {
        this.squareRepository = squareRepository;
    }

    @Override
    public String getKey() {
        return "v2:square";
    }

    @Override
    public List<SquareDTOv2> getAllShapes() {
        return squareRepository.findAll().stream()
                .map(square -> new SquareDTOv2(square.getA()))
                .toList();
    }

    @Override
    public SquareDTOv2 createShape(SquareDTOv2 squareDTOv2) {
        Square square = new Square();
        square.setA(squareDTOv2.getA());

        Square savedSquare = squareRepository.save(square);

        return new SquareDTOv2(savedSquare.getA());
    }
}
