package com.Human.mini_Prj.Repository;

import com.Human.mini_Prj.Constant.ItemSellstatus;
import com.Human.mini_Prj.Entity.Item;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional

class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository; // 필드를 통한 의존성 주입

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest(){
        for (int i =1; i <= 10; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품"+i);
            item.setPrice(1000*i);
            item.setItemDetail("테스트 상품 상세 설명"+i);
            item.setItemSellstatus(ItemSellstatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
            log.info("Item 저장 : {}", savedItem);
        }
//        System.out.println(savedItem);
//        log.info("Item 저장 : {}", item);
    }

    @Test
    @DisplayName("상품 조회 테스트")
    public void findByItemNumTest(){
        this.createItemTest();;
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품5");
        for(Item item : itemList){
            log.info("상품 조회 테스트 : {}", item);
        }
    }

    @Test
    @DisplayName("상품명 OR 상세 설명으로 조회 ")
    public void findByItemNmOrItemDetailTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1","테스트 상품 상세 설명");
        for (Item item : itemList){
            log.info("상품명 OR 상품 상세 설명으로 조회 테스트: {}",item);

        }
    }

    @Test
    @DisplayName("가격 LessThan 조회")
    public void findByPriceLessThanTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThan(5000);
        for (Item item : itemList){
            log.info("가격 LessThan 조회 테스트: {}",item);
        }
    }

    @Test
    @DisplayName("가격 LEssThan + Order By 조회")
    public void findByPriceLessThanOrderByPriceDescTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(5000);
        for (Item item : itemList){
            log.info("가격 LessThan + Order By 조회 테스트: {}",item);
        }
    }

    @Test
    @DisplayName("가격 범위 검색 조회")
    public void  findByPriceBetweenTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceBetween(0,30000);
        for (Item item : itemList){
            log.info("가격 범위 검색 조회 테스트: {}",item);

        }
    }

    @Test
    @DisplayName("상품명에 특정 문자열")
    public void findByItemNmContainingTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNmContaining("테스트");
        for (Item item : itemList){
            log.info("상품명에 특정 문자열 테스트: {}",item);
        }
    }

    @Test
    @DisplayName("판매 상태 조회")
    public void findByItemSellstatusTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemSellstatus(ItemSellstatus.SELL);
        for (Item item : itemList){
            log.info("판매 상태 조회 테스트: {}",item);
        }
    }

    @Test
    @DisplayName("판매 상태 같으면서 가격이 특정 값 이상인 조회")
    public void findByItemSellstatusAndPriceGreaterThanEqualTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemSellstatusAndPriceGreaterThanEqual(ItemSellstatus.SELL,10000);
        for (Item item : itemList){
            log.info("판매 상태 같으면서 가격이 특정 값 이상인 조회 테스트: {}",item);
        }
    }

    @Test
    @DisplayName("가장 비싼 상품 3개")
    public void findTop3ByOrderByPriceDescTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findTop3ByOrderByPriceDesc();
        for (Item item : itemList){
            log.info("가장 비싼 상품3개 조회 테스트: {}",item);
        }
    }

    @Test
    @DisplayName("가격이 목록 중 하나에 해당되는 테스트")
    public void findByPriceInTest(){
        this.createItemTest();
        List<Integer> price = List.of(10000,20000,30000);
        List<Item> itemList = itemRepository.findByPriceIn(price);
//        itemRepository.findByPriceIn(List.of(10000,20000,30000));
        for (Item item: itemList){
            log.info("가격이 목록 중 하나에 해당되는 테스트: {}",item);
        }
    }

    @Test
    @DisplayName("판매 상태별 상품 개수")
    public void countByItemSellstatusTest(){
        this.createItemTest();
        long count =itemRepository.countByItemSellstatus(ItemSellstatus.SELL);
        log.info("판매 상태별 상품 개수 테스트: {}", count);

    }


    @Test
    @DisplayName("native query 조회")
    public void nativeQueryTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemDetailLikeNative("테스트 상품 상세 설명 ");

        for (Item item : itemList){
            log.info("native query 조회 테스트: {}",item);

        }

    }

}