package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

	private static final String NONAME = "noname";

	@Test
	public void should_be_nothing_when_no_item() {

		// given (arrange)
		Item[] items = new Item[] {};
		GildedRose gildedRose = new GildedRose(items);

		// when (act)
		gildedRose.updateQuality();

		// then (assert)
		assertEquals(0, items.length);

	}

	@Test
	public void quality_of_noname_is_0_when_quality_is_0() throws Exception {

		// arrange
		Item[] items = new Item[] { new Item(NONAME, 0, 0) };
		GildedRose gildedRose = new GildedRose(items);

		// act
		gildedRose.updateQuality();

		// assert
		assertEquals(0, items[0].quality);
		assertEquals(-1, items[0].sellIn);

	}

	@Test
	public void quality_of_SULFURAS_is_0_when_quality_is_0() throws Exception {

		// arrange
		Item[] items = new Item[] { new Item(GildedRoseItem.SULFURAS, 0, 0) };
		GildedRose gildedRose = new GildedRose(items);

		// act
		gildedRose.updateQuality();

		// assert
		assertEquals(0, items[0].quality);
		assertEquals(0, items[0].sellIn);

	}

	@Test
	public void quality_of_noname_is_0_when_quality_is_1() throws Exception {

		// arrange
		Item[] items = new Item[] { new Item(NONAME, 0, 1) };
		GildedRose gildedRose = new GildedRose(items);

		// act
		gildedRose.updateQuality();

		// assert
		assertEquals(0, items[0].quality);
		assertEquals(-1, items[0].sellIn);

	}

	@Test
	public void quality_of_noname_is_1_when_quality_is_3() throws Exception {

		// arrange
		Item[] items = new Item[] { new Item(NONAME, 0, 3) };
		GildedRose gildedRose = new GildedRose(items);

		// act
		gildedRose.updateQuality();

		// assert
		assertEquals(1, items[0].quality);
		assertEquals(-1, items[0].sellIn);

	}

	@Test
	public void quality_of_noname_is_2_when_quality_is_3_and_sellin_1() throws Exception {

		// arrange
		Item[] items = new Item[] { new Item(NONAME, 1, 3) };
		GildedRose gildedRose = new GildedRose(items);

		// act
		gildedRose.updateQuality();

		// assert
		assertEquals(2, items[0].quality);
		assertEquals(0, items[0].sellIn);

	}

	@Test
	public void quality_of_SULFURAS_is_1_when_quality_is_1() throws Exception {

		// arrange
		Item[] items = new Item[] { new Item(GildedRoseItem.SULFURAS, 0, 1) };
		GildedRose gildedRose = new GildedRose(items);

		// act
		gildedRose.updateQuality();

		// assert
		assertEquals(1, items[0].quality);
		assertEquals(0, items[0].sellIn);

	}

	@Test
	public void quality_of_SULFURAS_is_1_when_quality_is_1_and_sellin_minus_1() throws Exception {

		// arrange
		Item[] items = new Item[] { new Item(GildedRoseItem.SULFURAS, -1, 1) };
		GildedRose gildedRose = new GildedRose(items);

		// act
		gildedRose.updateQuality();

		// assert
		assertEquals(1, items[0].quality);
		assertEquals(-1, items[0].sellIn);

	}

	@Test
	public void quality_of_AGED_BRIE_is_2_when_quality_is_0() throws Exception {

		// arrange
		Item[] items = new Item[] { new Item(GildedRoseItem.AGED_BRIE, 0, 0) };
		GildedRose gildedRose = new GildedRose(items);

		// act
		gildedRose.updateQuality();

		// assert
		assertEquals(2, items[0].quality);
		assertEquals(-1, items[0].sellIn);

	}

	@Test
	public void quality_of_AGED_BRIE_is_1_when_quality_is_0_and_sellin_is_1() throws Exception {

		// arrange
		Item[] items = new Item[] { new Item(GildedRoseItem.AGED_BRIE, 1, 0) };
		GildedRose gildedRose = new GildedRose(items);

		// act
		gildedRose.updateQuality();

		// assert
		assertEquals(1, items[0].quality);
		assertEquals(0, items[0].sellIn);

	}

	@Test
	public void quality_of_AGED_BRIE_is_51_when_quality_is_51() throws Exception {

		// arrange
		Item[] items = new Item[] { new Item(GildedRoseItem.AGED_BRIE, 0, 51) };
		GildedRose gildedRose = new GildedRose(items);

		// act
		gildedRose.updateQuality();

		// assert
		assertEquals(51, items[0].quality);
		assertEquals(-1, items[0].sellIn);

	}

	@Test
	public void quality_of_AGED_BRIE_is_51_when_quality_is_49() throws Exception {

		// arrange
		Item[] items = new Item[] { new Item(GildedRoseItem.AGED_BRIE, 0, 49) };
		GildedRose gildedRose = new GildedRose(items);

		// act
		gildedRose.updateQuality();

		// assert
		assertEquals(50, items[0].quality);
		assertEquals(-1, items[0].sellIn);

	}

	@Test
	public void quality_of_BACKSTAGE_is_0_when_quality_is_0() throws Exception {

		// arrange
		Item[] items = new Item[] { new Item(GildedRoseItem.BACKSTAGE_PASSES, 0, 0) };
		GildedRose gildedRose = new GildedRose(items);

		// act
		gildedRose.updateQuality();

		// assert
		assertEquals(0, items[0].quality);
		assertEquals(-1, items[0].sellIn);

	}

	@Test
	public void quality_of_BACKSTAGE_is_1_when_sellin_is_12() throws Exception {

		// arrange
		Item[] items = new Item[] { new Item(GildedRoseItem.BACKSTAGE_PASSES, 12, 0) };
		GildedRose gildedRose = new GildedRose(items);

		// act
		gildedRose.updateQuality();

		// assert
		assertEquals(1, items[0].quality);
		assertEquals(11, items[0].sellIn);

	}

	@Test
	public void quality_of_BACKSTAGE_is_0_when_sellin_is_49() throws Exception {

		// arrange
		Item[] items = new Item[] { new Item(GildedRoseItem.BACKSTAGE_PASSES, 0, 49) };
		GildedRose gildedRose = new GildedRose(items);

		// act
		gildedRose.updateQuality();

		// assert
		assertEquals(0, items[0].quality);
		assertEquals(-1, items[0].sellIn);

	}

	@Test
	public void quality_of_BACKSTAGE_is_0_when_quality_is_51() throws Exception {

		// arrange
		Item[] items = new Item[] { new Item(GildedRoseItem.BACKSTAGE_PASSES, 0, 51) };
		GildedRose gildedRose = new GildedRose(items);

		// act
		gildedRose.updateQuality();

		// assert
		assertEquals(0, items[0].quality);
		assertEquals(-1, items[0].sellIn);

	}

}
