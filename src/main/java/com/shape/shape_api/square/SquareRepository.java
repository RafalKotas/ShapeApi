package com.shape.shape_api.square;

import com.shape.shape_api.model.Square;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SquareRepository extends JpaRepository<Square, Long> {

}
