package com.hrl.project;

import com.binance.client.SubscriptionClient;
import com.binance.client.constant.PrivateConfig;
import com.binance.client.model.market.OrderBookEntry;
import com.hrl.project.entity.OrderDepth;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class RecordOrderDepth {



    public void recordOrderDepth() {
        SubscriptionClient client = SubscriptionClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);

        client.subscribeBookDepthEvent("btcusdt", 5, ((event) -> {
            System.out.println(event);
            //client.unsubscribeAll();
            List<OrderBookEntry> bids = event.getBids();
            List<OrderBookEntry> asks = event.getAsks();


        }), null);
    }

    public OrderDepth buildOrderDepth(List<OrderBookEntry> bids , List<OrderBookEntry> asks, double total) {

        OrderDepth orderDepth = new OrderDepth();
/*
        BigDecimal bidTotal = new BigDecimal();
        for(OrderBookEntry orderBookEntry : bids) {
            bidTotal += orderBookEntry.getPrice().multiply(orderBookEntry.getQty());
        }*/

        return  null;
    }
}
