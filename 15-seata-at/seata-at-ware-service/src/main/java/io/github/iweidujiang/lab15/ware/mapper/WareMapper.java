package io.github.iweidujiang.lab15.ware.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.iweidujiang.lab15.ware.entity.Ware;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 库存 Mapper。
 *
 * @author 苏渡苇
 */
@Mapper
public interface WareMapper extends BaseMapper<Ware> {

    /**
     * 扣减库存。
     *
     * @param skuId 商品 SKU ID
     */
    @Update("update t_ware set stock = stock - 1, update_time = now() where sku_id = #{skuId}")
    void deductStock(@Param("skuId") Long skuId);
}
