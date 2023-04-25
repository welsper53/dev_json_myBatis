package com.kh.test.controller;

import java.util.List;
import lombok.Data;

public class KoreanHouseVO {
	public KoreanHouses items;
	
	@Data
	public static class KoreanHouses {
		private List<KoreanHouse> item;
	}
	
	@Data
	public static class KoreanHouse {
		public String brandnm;
		public int brandno;
		public String goodsnm;
		public int goodsno;
		public int price;
		public String selltype;
		public double posx;
		public double posy;
	}
}