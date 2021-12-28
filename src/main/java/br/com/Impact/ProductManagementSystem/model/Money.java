package br.com.Impact.ProductManagementSystem.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Money {

    HashMap<String, MoneyInfo> mapa;

    public Money() {
//        mapa.put("USD", new MoneyInfo(""));
//        mapa.put("USDT", new MoneyInfo(""));
//        mapa.put("CAD", new MoneyInfo(""));
//        mapa.put("GBP", new MoneyInfo(""));
//        mapa.put("ARS", new MoneyInfo(""));
//        mapa.put("BTC", new MoneyInfo(""));
//        mapa.put("LTC", new MoneyInfo(""));
//        mapa.put("EUR", new MoneyInfo(""));
//        mapa.put("JPY", new MoneyInfo(""));
//        mapa.put("CHF", new MoneyInfo(""));
//        mapa.put("AUD", new MoneyInfo(""));
//        mapa.put("CNY", new MoneyInfo(""));
//        mapa.put("ILS", new MoneyInfo(""));
//        mapa.put("ETH", new MoneyInfo(""));
//        mapa.put("XRP", new MoneyInfo(""));
//        mapa.put("DOGE", new MoneyInfo(""));
//        mapa.keySet().add(Arrays.asList("USD", "USDT", "CAD", "GBP", "ARS", "BTC", "LTC", "EUR", "JPY", "CHF", "AUD", "CNY", "ILS", "ETH", "XRP", "DOGE"));
    }

    public HashMap<String, MoneyInfo> getMapa() {
        return mapa;
    }

    public void setMapa(HashMap<String, MoneyInfo> mapa) {
        this.mapa = mapa;
    }
}
