package com.example.prices.repository;

import com.example.prices.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    List<Price> findByStartDateBeforeAndEndDateAfterAndProductIdAndBrandId(LocalDateTime startRequestedDate,
                                                                           LocalDateTime endRequestedDate,
                                                                           Long productId,
                                                                           int brandId);
    List<Price> findAllByBrandId(int branId);

    List<Price> findAll();
}
