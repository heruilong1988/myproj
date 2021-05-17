package com.hrl.project.dao;

import com.hrl.project.entity.OrderDepth;
import com.hrl.project.mapper.OrderDepthMapper;
import lombok.Data;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class OrderDepthDAO {


    @Autowired
    OrderDepthMapper orderDepthMapper;

   /* @Autowired
    private SqlSessionFactory sqlSessionFactory;
*/
    public void saveOrderDepth(OrderDepth orderDepth) {
       /* try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            OrderDepthMapper orderDepthMapper = sqlSession.getMapper(OrderDepthMapper.class);
            Integer result = orderDepthMapper.insertOrderDepth(orderDepth);
            System.out.println(result);
        }
*/
        Integer result = orderDepthMapper.insertOrderDepth(orderDepth);
        System.out.println(result);
    }
}
