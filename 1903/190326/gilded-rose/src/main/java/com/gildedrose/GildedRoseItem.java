package com.gildedrose;

public abstract class GildedRoseItem {

	protected Item item;
	public static final int MAX_QUALITY = 50;
	public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
	public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	public static final String AGED_BRIE = "Aged Brie";

	public GildedRoseItem(Item item) {
		this.item = item;
	}

	abstract void updateQuality();
	abstract void updateSellIn(Item item);

}