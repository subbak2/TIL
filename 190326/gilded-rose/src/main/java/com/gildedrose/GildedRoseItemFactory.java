package com.gildedrose;

public class GildedRoseItemFactory {
	
	private static GildedRoseItemFactory instance;
	
	private GildedRoseItemFactory() {
		
	}

	public static synchronized GildedRoseItemFactory getInstance() {
		if (instance == null) {
			instance = new GildedRoseItemFactory();
		}
		return instance;		
	}

	GildedRoseItem createGildedRoses(Item item) {
		GildedRoseItem gildedRoseItem;
		if (item.name.equals(GildedRoseItem.AGED_BRIE)) {
			gildedRoseItem = new AgedBrieItem(item);
		} else if (item.name.equals(GildedRoseItem.BACKSTAGE_PASSES)) {
			gildedRoseItem = new BackStageItem(item);
		} else if (item.name.equals(GildedRoseItem.SULFURAS)) {
			gildedRoseItem = new SulfurasItem(item);
		} else {
			gildedRoseItem = new NormalItem(item);
		}
		return gildedRoseItem;
	}

}
