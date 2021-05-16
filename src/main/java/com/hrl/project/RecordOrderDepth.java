package com.hrl.project;

import com.binance.client.SubscriptionClient;
import com.binance.client.SubscriptionErrorHandler;
import com.binance.client.constant.PrivateConfig;
import com.binance.client.exception.BinanceApiException;
import com.binance.client.model.market.OrderBookEntry;
import com.hrl.project.entity.OrderDepth;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
public class RecordOrderDepth {

    public void recordOrderDepth() {
        SubscriptionClient client = SubscriptionClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);

        client.subscribeBookDepthEvent("btcusdt", 10, ((event) -> {
            System.out.println(event);
            //client.unsubscribeAll();
            long timestamp = System.currentTimeMillis();
            List<OrderBookEntry> bids = event.getBids();
            List<OrderBookEntry> asks = event.getAsks();
            OrderDepth orderDepth = calculate(bids, asks);
            orderDepth.setBase("usdt");
            orderDepth.setTarget("btc");
            log.info("order depth:{}", orderDepth);
        }), new SubscriptionErrorHandler() {
            @Override
            public void onError(BinanceApiException exception) {
                System.out.println(exception);
                exception.printStackTrace();
            }
        });

    }

    public OrderDepth calculate(List<OrderBookEntry> bids , List<OrderBookEntry> asks) {

        OrderDepth orderDepth = new OrderDepth();

        //calculate for 100 bid
        BigDecimal bidTotal100 = new BigDecimal("0");
        BigDecimal amountBid100 = new BigDecimal("0");
        BigDecimal quotaBid100 = new BigDecimal("100");
        for(OrderBookEntry orderBookEntry : bids) {
            bidTotal100 = bidTotal100.add(orderBookEntry.getPrice().multiply(orderBookEntry.getQty()));
            amountBid100 = amountBid100.add(orderBookEntry.getQty());

            if(bidTotal100.compareTo(quotaBid100) >= 0) {
                orderDepth.setBidPrice_100(orderBookEntry.getPrice());
                orderDepth.setBidAmount_100(amountBid100);
                break;
            }
        }

        //calculate for 100 ask
        BigDecimal askTotal100 = new BigDecimal("0");
        BigDecimal amountAsk100 = new BigDecimal("0");
        BigDecimal quotaAsk100 = new BigDecimal("100");
        for(OrderBookEntry orderBookEntry : asks) {
            askTotal100 = askTotal100.add(orderBookEntry.getPrice().multiply(orderBookEntry.getQty()));
            amountAsk100 = amountAsk100.add(orderBookEntry.getQty());

            if(askTotal100.compareTo(quotaAsk100) >= 0) {
                orderDepth.setAskPrice_100(orderBookEntry.getPrice());
                orderDepth.setAskAmount_100(amountAsk100);
                break;
            }
        }

        //calculate for 500 bid
        BigDecimal bidTotal500 = new BigDecimal("0");
        BigDecimal amountBid500 = new BigDecimal("0");
        BigDecimal quotaBid500 = new BigDecimal("500");
        for(OrderBookEntry orderBookEntry : bids) {
            bidTotal500 = bidTotal500.add(orderBookEntry.getPrice().multiply(orderBookEntry.getQty()));
            amountBid500 = amountBid500.add(orderBookEntry.getQty());

            if(bidTotal500.compareTo(quotaBid500) >= 0) {
                orderDepth.setBidPrice_500(orderBookEntry.getPrice());
                orderDepth.setBidAmount_500(amountBid500);
                break;
            }
        }


        //calculate for 500 ask
        BigDecimal askTotal500 = new BigDecimal("0");
        BigDecimal amountAsk500 = new BigDecimal("0");
        BigDecimal quotaAsk500 = new BigDecimal("500");
        for(OrderBookEntry orderBookEntry : asks) {
            askTotal500 = askTotal500.add(orderBookEntry.getPrice().multiply(orderBookEntry.getQty()));
            amountAsk500 = amountAsk500.add(orderBookEntry.getQty());

            if(askTotal500.compareTo(quotaAsk500) >= 0) {
                orderDepth.setAskPrice_100(orderBookEntry.getPrice());
                orderDepth.setAskAmount_100(amountAsk500);
                break;
            }
        }

        return  orderDepth;
    }
}
