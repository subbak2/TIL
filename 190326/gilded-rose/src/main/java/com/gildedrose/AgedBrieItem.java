package com.gildedrose;

public class AgedBrieItem extends GildedRoseItem {

	public AgedBrieItem(Item item) {
		super(item);
	}

	@Override
	void updateQuality() {
		if (item.quality < GildedRoseItem.MAX_QUALITY) {
			item.quality = item.quality + 1;
		}
		if (item.sellIn < 1) {
			if (item.quality < GildedRoseItem.MAX_QUALITY) {
				item.quality = item.quality + 1;
			}
		}
	}

	@Override
	void updateSellIn(Item item) {
		item.sellIn--;
	}

}
