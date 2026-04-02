package com.gildedrose;

public class DefaultQualityStrategy implements QualityStrategy {
    @Override
    public void updateQuality(Item item) {
        if(item.sellIn > 0) {
            item.quality = Math.max(item.quality - 1, 0);
        }
        else {
            item.quality = Math.max(item.quality - 2, 0);
        }
        item.sellIn--;
    }
}
