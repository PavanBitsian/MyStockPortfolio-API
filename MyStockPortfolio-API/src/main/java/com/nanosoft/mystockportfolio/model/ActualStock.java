package com.nanosoft.mystockportfolio.model;

import java.util.List;

public class ActualStock {
	Metadata metadata;
	List<DayWiseStock> dayWiseStock;
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return metadata.symbol;//+" "+dayWiseStock.get(0).high;
	}
	

}
