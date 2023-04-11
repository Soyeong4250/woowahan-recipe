package com.woowahan.recipe.repository;

import com.woowahan.recipe.domain.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select s.quantity from StockEntity s where s.id = :id")
    Optional<StockEntity> findByIdWithPessimisticLock(Long id);
}
