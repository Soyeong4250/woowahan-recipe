package com.woowahan.recipe.service;

import com.woowahan.recipe.domain.entity.ItemEntity;
import com.woowahan.recipe.domain.entity.StockEntity;
import com.woowahan.recipe.repository.ItemRepository;
import com.woowahan.recipe.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StockServiceTest {
    @Autowired
    private final StockRepository stockRepository;
    @Autowired
    private final ItemRepository itemRepository;

    @Autowired
    public StockServiceTest(StockRepository stockRepository, ItemRepository itemRepository) {
        this.stockRepository = stockRepository;
        this.itemRepository = itemRepository;
    }

    @Test
    void 재고_옮기기() throws Exception {
        // given
        for (Long i = 1L; i <= 116L; i++) {
            ItemEntity item = itemRepository.findById(i).orElseThrow(() -> new RuntimeException("테스트에서 아이템 아이디 못찾았다냥"));
            StockEntity stock = new StockEntity();
            stock.setItem(item);
            stock.setQuantity(item.getItemStock());
            StockEntity newStock = stockRepository.saveAndFlush(stock);
            item.setStock(newStock);
            itemRepository.save(item);
        }
    }
}
