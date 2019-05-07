package com.xinyibi.model;

import java.util.List;

import com.xinyibi.pojo.ViewField;
import com.xinyibi.pojo.ViewFieldItem;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Deprecated
@Data
@EqualsAndHashCode(callSuper=false)
public class ViewFieldAndItems extends ViewField {
	
	private List<ViewFieldItem> items;
}
