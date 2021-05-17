package com.hrl.project.mapper;

import com.hrl.project.entity.OrderDepth;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDepthMapper {
    Integer insertOrderDepth(OrderDepth orderDepth);
}
