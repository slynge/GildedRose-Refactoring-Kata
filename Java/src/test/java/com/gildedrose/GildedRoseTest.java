package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

    private Item[] createItems(String name, int sellIn, int quality) {
        return new Item[] { new Item(name, sellIn, quality) };
    }

    private Item updateAndGet(String name, int sellIn, int quality) {
        Item[] items = createItems(name, sellIn, quality);
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return items[0];
    }

    // ─── Normal Items ────────────────────────────────────────

    @Nested
    @DisplayName("Normal Items")
    class NormalItems {

        @Test
        void qualityDecreasesByOneBeforeSellDate() {
            Item item = updateAndGet("Normal Item", 10, 20);
            assertEquals(9, item.sellIn);
            assertEquals(19, item.quality);
        }

        @Test
        void qualityDegradesTwiceAsFastAfterSellDate() {
            Item item = updateAndGet("Normal Item", 0, 20);
            assertEquals(-1, item.sellIn);
            assertEquals(18, item.quality);
        }

        @Test
        void qualityIsNeverNegative() {
            Item item = updateAndGet("Normal Item", 5, 0);
            assertEquals(0, item.quality);
        }

        @Test
        void qualityIsNeverNegativeAfterSellDate() {
            Item item = updateAndGet("Normal Item", 0, 1);
            assertEquals(0, item.quality);
        }

        @Test
        void qualityDoesNotGoBelowZeroWhenAlreadyZeroAfterSellDate() {
            Item item = updateAndGet("Normal Item", -1, 0);
            assertEquals(0, item.quality);
        }
    }

    // ─── Aged Brie ───────────────────────────────────────────

    @Nested
    @DisplayName("Aged Brie")
    class AgedBrie {

        @Test
        void qualityIncreasesWithAge() {
            Item item = updateAndGet("Aged Brie", 10, 20);
            assertEquals(21, item.quality);
        }

        @Test
        void qualityIncreasesTwiceAsFastAfterSellDate() {
            Item item = updateAndGet("Aged Brie", 0, 20);
            assertEquals(22, item.quality);
        }

        @Test
        void qualityNeverExceedsFifty() {
            Item item = updateAndGet("Aged Brie", 10, 50);
            assertEquals(50, item.quality);
        }

        @Test
        void qualityNeverExceedsFiftyAfterSellDate() {
            Item item = updateAndGet("Aged Brie", 0, 49);
            assertEquals(50, item.quality);
        }
    }

    // ─── Sulfuras ────────────────────────────────────────────

    @Nested
    @DisplayName("Sulfuras")
    class Sulfuras {

        @Test
        void qualityNeverChanges() {
            Item item = updateAndGet("Sulfuras, Hand of Ragnaros", 10, 80);
            assertEquals(80, item.quality);
        }

        @Test
        void sellInNeverChanges() {
            Item item = updateAndGet("Sulfuras, Hand of Ragnaros", 10, 80);
            assertEquals(10, item.sellIn);
        }

        @Test
        void qualityRemainsEightyAfterSellDate() {
            Item item = updateAndGet("Sulfuras, Hand of Ragnaros", -1, 80);
            assertEquals(80, item.quality);
        }
    }

    // ─── Backstage Passes ────────────────────────────────────

    @Nested
    @DisplayName("Backstage Passes")
    class BackstagePasses {

        private static final String NAME = "Backstage passes to a TAFKAL80ETC concert";

        @Test
        void qualityIncreasesByOneWhenMoreThanTenDays() {
            Item item = updateAndGet(NAME, 15, 20);
            assertEquals(21, item.quality);
        }

        @Test
        void qualityIncreasesByTwoWhenTenDaysOrLess() {
            Item item = updateAndGet(NAME, 10, 20);
            assertEquals(22, item.quality);
        }

        @Test
        void qualityIncreasesByTwoAtSixDays() {
            Item item = updateAndGet(NAME, 6, 20);
            assertEquals(22, item.quality);
        }

        @Test
        void qualityIncreasesByThreeWhenFiveDaysOrLess() {
            Item item = updateAndGet(NAME, 5, 20);
            assertEquals(23, item.quality);
        }

        @Test
        void qualityIncreasesByThreeAtOneDay() {
            Item item = updateAndGet(NAME, 1, 20);
            assertEquals(23, item.quality);
        }

        @Test
        void qualityDropsToZeroAfterConcert() {
            Item item = updateAndGet(NAME, 0, 50);
            assertEquals(0, item.quality);
        }

        @Test
        void qualityNeverExceedsFifty() {
            Item item = updateAndGet(NAME, 5, 49);
            assertEquals(50, item.quality);
        }

        @Test
        void qualityNeverExceedsFiftyEvenWithDoubleIncrease() {
            Item item = updateAndGet(NAME, 10, 50);
            assertEquals(50, item.quality);
        }
    }

    // ─── Conjured Items ──────────────────────────────────────

    @Nested
    @DisplayName("Conjured Items")
    class ConjuredItems {

        @Test
        void qualityDegradesTwiceAsFastBeforeSellDate() {
            Item item = updateAndGet("Conjured Mana Cake", 10, 20);
            assertEquals(18, item.quality);
        }

        @Test
        void qualityDegradesFourTimesAfterSellDate() {
            Item item = updateAndGet("Conjured Mana Cake", 0, 20);
            assertEquals(16, item.quality);
        }

        @Test
        void qualityIsNeverNegative() {
            Item item = updateAndGet("Conjured Mana Cake", 5, 1);
            assertEquals(0, item.quality);
        }

        @Test
        void qualityIsNeverNegativeAfterSellDate() {
            Item item = updateAndGet("Conjured Mana Cake", 0, 3);
            assertEquals(0, item.quality);
        }
    }
}