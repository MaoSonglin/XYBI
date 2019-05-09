package com.xinyibi.adapter;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import com.tsc9526.monalisa.core.query.datatable.DataMap;
import com.tsc9526.monalisa.core.query.datatable.DataTable;
import com.xinyibi.pojo.ViewField;
import com.xinyibi.pojo.ViewFieldItem;

import lombok.AllArgsConstructor;
import lombok.Setter;

public interface Formula {

	DataTable<DataMap> map(List<ViewField> fields) throws Exception;
}

@Setter
@AllArgsConstructor
class FormulaImpl implements Formula{
	
	private DataTable<DataMap> originDataTable;
	
	@Override
	public DataTable<DataMap> map(List<ViewField> fields) throws Exception {
		DataTable<DataMap> dataTable = new DataTable<>();
		for(DataMap line : originDataTable){
			DataMap dataMap = new DataMap();
			for (ViewField view : fields) {
				List<ViewFieldItem> items = view.getItems(); 
				Stack<Object> valueStack = new Stack<>();
				Stack<String> operator = new Stack<>();
				for (int i = 0; i < items.size(); i++) {
					ViewFieldItem viewFieldItem = items.get(i);
					String type = viewFieldItem.getType();
					String content = viewFieldItem.getContent();
					switch(type){
					case "column":
						valueStack.push(line.get(content));
						break;
					case "number":
					case "string":
						valueStack.push(content);
						break;
					case "operator":
						String pop = operator.peek();//operator.top();
						int equal = equal(pop,content);
						if(equal == 0){// 优先级相同
							operator.pop();
						}else if(equal > 0){// 栈顶运算符优先级高
							Object b = valueStack.pop();
							Object a = valueStack.pop();
							Object value = calcute(a,b,pop);
							valueStack.push(value);
							i--;
						}else{// 栈外运算符优先级高
							operator.push(content);
						}
						break;
					case "function":
						throw new UnsupportedOperationException();
					default:
						throw new UnsupportedOperationException();
					}
				} 
				dataMap.put(view.getFieldName(), valueStack.peek());
			}
			dataTable.add(dataMap);
		}
		return dataTable;
	}

	private Object calcute(Object a, Object b, String pop) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 计算两个操作符的优先级
	 * @param pop		操作符
	 * @param content
	 * @return
	 */
	private int equal(String pop, String content) {
		return 0;
	}
	
}