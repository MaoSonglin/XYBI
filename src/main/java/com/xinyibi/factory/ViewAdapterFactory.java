package com.xinyibi.factory;

import com.xinyibi.adapter.SingleViewAdapter;
import com.xinyibi.adapter.ViewAdapter;

public class ViewAdapterFactory {

	public static ViewAdapter getViewAdapter(){
		
		return new SingleViewAdapter();
	}
}
