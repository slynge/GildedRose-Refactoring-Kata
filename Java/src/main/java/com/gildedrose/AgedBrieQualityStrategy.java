package com.gildedrose;

public class AgedBrieQualityStrategy implements QualityStrategy {
    @Override
    public void updateQuality(Item item) {
        if(item.sellIn > 0) {
            item.quality = Math.min(item.quality + 1, 50);
        }
        else {
            item.quality = Math.min(item.quality + 2, 50);
        }
        item.sellIn--;
    }
}
