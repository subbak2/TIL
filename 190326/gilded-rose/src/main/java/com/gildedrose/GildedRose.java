package com.gildedrose;

class GildedRose {
	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (int i = 0; i < items.length; i++) {
			Item item = items[i];
			GildedRoseItem gildedRoseItem = GildedRoseItemFactory.getInstance().createGildedRoses(item);
			gildedRoseItem.updateQuality();
			gildedRoseItem.updateSellIn(item);
		}
	}
}
