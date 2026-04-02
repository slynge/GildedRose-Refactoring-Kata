package com.gildedrose;

import java.util.HashMap;
import java.util.Map;

class GildedRose {
    Item[] items;
    Map<String, QualityStrategy> strategyMap;

    public GildedRose(Item[] items) {
        this.items = items;
        strategyMap = new HashMap<>();
        strategyMap.put("Aged Brie", new AgedBrieQualityStrategy());
        strategyMap.put("Backstage passes to a TAFKAL80ETC concert", new BackstagePassesQualityStrategy());
        strategyMap.put("Sulfuras, Hand of Ragnaros", new SulfurasQualityStrategy());
        strategyMap.put("Conjured Mana Cake", new ConjuredQualityStrategy());

    }

    public void updateQuality() {

        for (Item item : items) {
            strategyMap.getOrDefault(item.name, new DefaultQualityStrategy()).updateQuality(item);
        }
    }
}
