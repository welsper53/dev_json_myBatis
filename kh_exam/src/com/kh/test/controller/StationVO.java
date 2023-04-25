package com.kh.test.controller;

import java.util.List;
import lombok.Data;

public class StationVO {
	public Stations items;
	
	@Data
	public static class Stations {
		private List<Station> item;
	}
	
	@Data
	public static class Station {
		public double gpsX;
		public double gpsY;
		public int poiId;
		public String poiNm;
		public double posX;
		public double posY;
	}
}
