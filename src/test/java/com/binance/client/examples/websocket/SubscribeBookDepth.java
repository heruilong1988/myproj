package com.binance.client.examples.websocket;

import com.binance.client.SubscriptionClient;
import com.binance.client.examples.constants.PrivateConfig;
import com.binance.client.model.market.OrderBookEntry;

import java.util.List;

public class SubscribeBookDepth {

    public static void main(String[] args) {

        // HTTP 代理，只能代理 HTTP 请求
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "8118");

        // HTTPS 代理，只能代理 HTTPS 请求
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyPort", "8118");


        SubscriptionClient client = SubscriptionClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
   
        client.subscribeBookDepthEvent("btcusdt", 5, ((event) -> {
            System.out.println(event);
            //client.unsubscribeAll();
            List<OrderBookEntry> bids = event.getBids();
            List<OrderBookEntry> asks = event.getAsks();


        }), null);

    }

}
