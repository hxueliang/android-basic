package com.hxl.dlocaldatalasting.enity;

/**
 * 购物车信息
 */
public class CartInfo {
    /**
     * 购物车编号
     */
    public int id;
    /**
     * 商品编号
     */
    public int goodsId;
    /**
     * 商品数量
     */
    public int count;

    public CartInfo() {
    }

    public CartInfo(int id, int goodsId, int count) {
        this.id = id;
        this.goodsId = goodsId;
        this.count = count;
    }

    @Override
    public String toString() {
        return "CartInfo{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", count=" + count +
                '}';
    }
}
