package com.hmdp.service.impl;

import static com.hmdp.utils.RedisConstants.CACHE_NULL_TTL;
import static com.hmdp.utils.RedisConstants.CACHE_SHOP_KEY;
import static com.hmdp.utils.RedisConstants.CACHE_SHOP_TTL;

import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.Shop;
import com.hmdp.mapper.ShopMapper;
import com.hmdp.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.utils.CacheClient;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

  @Autowired
  private StringRedisTemplate stringRedisTemplate;
  @Resource
  private CacheClient cacheClient;
  @Override
  public Result queryID(Long id) {
//    Shop shop = cacheClient.queryWithPassThrough(CACHE_SHOP_KEY, id, Shop.class, this::getById,
//        CACHE_SHOP_TTL, TimeUnit.MINUTES);
    Shop shop = cacheClient.queryWithLogicalExpire(CACHE_SHOP_KEY, id, Shop.class, this::getById,
        CACHE_SHOP_TTL, TimeUnit.MINUTES);
    if(shop==null){
      return Result.fail("店铺不存在");
    }
    return Result.ok();
  }

  @Override
  @Transactional
  public Result update(Shop shop) {
    Long id = shop.getId();
    if(id==null){
      return Result.fail("店铺ID不能为空");
    }
    updateById(shop);

   stringRedisTemplate.delete(CACHE_SHOP_KEY+id);
   return Result.ok();
  }
}
