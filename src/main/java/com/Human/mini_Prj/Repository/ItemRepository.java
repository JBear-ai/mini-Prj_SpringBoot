package com.Human.mini_Prj.Repository;

import com.Human.mini_Prj.Constant.ItemSellstatus;
import com.Human.mini_Prj.Entity.Item;
import lombok.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // JPA 설계 명세서
    // 기본적인 CRUD를 모두 포함하고 있음

    // 상풍명으로 상품 검색
//    Item findByItemNm(String itemNm); // findBy + 변수이름

    //
    List<Item> findByItemNm(String itemNm);

    // 1. Or : 상품명 또는 상품 상세 설명으로 검색
    // SQL : where item_nm = ? or item_detail = ?
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    // 2. LessThan : 가격이 특정 값보다 작은 상품
    // SQL : where price < ?
    List<Item> findByPriceLessThan(int price);

    // 3. LessThan + OrderBy : 가격이 특정 값보다 작은 상품을 가격 내림차순으로
    // SQL : where price < ? order by price desc
    List<Item> findByPriceLessThanOrderByPriceDesc(int price);

    // 4. Between : 가격 범위 검색 (양 끝 값 포함)
    // SQL : where price between ? and ?
    List<Item> findByPriceBetween(int minPrice, int maxPrice);

    // 5. Containing : 상품명에 특정 문자열이 포함된 상품
    // SQL : where item_nm like '%?%'
    List<Item> findByItemNmContaining(String itemNm);

    // 6. Enum 필드 검색 : 판매 상태로 검색
    // SQL : where item_sell_status = ?
    List<Item> findByItemSellstatus(ItemSellstatus itemSellstatus);

    // 7. And + GreaterThanEqual : 판매 상태가 같고 가격이 특정 값 이상인 상품
    // SQL : where item_sell_status = ? and price >= ?
    List<Item> findByItemSellstatusAndPriceGreaterThanEqual(ItemSellstatus itemSellstatus, int price);

    // 8. Top + OrderBy : 가장 비싼 상품 3개
    // SQL : order by price desc limit 3
    List<Item> findTop3ByOrderByPriceDesc();

    // 9. In : 가격이 목록 중 하나에 해당하는 상품
    // SQL : where price in (?, ?, ...)
    List<Item> findByPriceIn(Collection<Integer> price);

    // 10. count : 판매 상태별 상품 개수 (List가 아니라 long 반환)
    // SQL : select count(*) from item where item_sell_status = ?
    long countByItemSellstatus(ItemSellstatus itemSellstatus);

    // findBy,countBy,deleteBy,existsBy

    // JPQL(JPA Query Language) 쿼리: 객체지향 쿼리
    @Query("SELECT i FROM Item i WHERE i.itemDetail Like %:itemDetail% ORDER BY i.price DESC")
    List<Item> findByItemDetailLike(String itemDetail);

    // NativeQuery: 실제 DB에서 사용하는 쿼리를 그대로 사용

    @Query(value = "SELECT * FROM item i where i.item_detail like %:itemDetail% ORDER BY  i.price desc",nativeQuery = true)
    List<Item> findByItemDetailLikeNative(String itemDetail);




}