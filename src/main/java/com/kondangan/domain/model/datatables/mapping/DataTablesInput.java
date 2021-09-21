package com.kondangan.domain.model.datatables.mapping;



import com.kondangan.domain.model.datatables.parameter.ColumnParameter;
import com.kondangan.domain.model.datatables.parameter.SearchParameter;
import com.kondangan.domain.model.datatables.parameter.OrderParameter;
import com.kondangan.domain.model.datatables.parameter.SearchParameterQuery;

import java.util.ArrayList;
import java.util.List;


public class DataTablesInput {


	/**
	 * Draw counter. This is used by DataTables to ensure that the Ajax returns
	 * from server-side processing requests are drawn in sequence by DataTables
	 * (Ajax requests are asynchronous and thus can return out of sequence).
	 * This is used as part of the draw return parameter (see below).
	 */
	private Integer draw;

	/**
	 * Paging first record indicator. This is the start point in the current
	 * data set (0 index based - i.e. 0 is the first record).
	 */
	private Integer start;

	/**
	 * Number of records that the table can display in the current draw. It is
	 * expected that the number of records returned will be equal to this
	 * number, unless the server has fewer records to return. Note that this can
	 * be -1 to indicate that all records should be returned (although that
	 * negates any benefits of server-side processing!)
	 */
	private Integer length;

	/**
	 * Global search parameter.
	 */

	private SearchParameter search;

	/**
	 * Order parameter
	 */

	private List<OrderParameter> order;

	private List<SearchParameterQuery> searchQuery;

	public List<SearchParameterQuery> getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(List<SearchParameterQuery> searchQuery) {
		this.searchQuery = searchQuery;
	}

	private String param1;
	private String param2;
	private String param3;
	private String param4;
	private String param5;
	private String param6;
	private String param7;
	private String param8;
	private String param9;
	private String param10;
	private String param11;
	private String param12;


	private List<ColumnParameter> columns;

	public DataTablesInput() {
		this.search = new SearchParameter();
		this.order = new ArrayList<OrderParameter>();
		this.setColumns(new ArrayList<ColumnParameter>());
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public SearchParameter getSearch() {
		return search;
	}

	public void setSearch(SearchParameter search) {
		this.search = search;
	}

	public List<OrderParameter> getOrder() {
		return order;
	}

	public void setOrder(List<OrderParameter> order) {
		this.order = order;
	}

	/**
	 * Per-column search parameter
	 */
	public List<ColumnParameter> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnParameter> columns) {
		this.columns = columns;
	}


	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public String getParam3() {
		return param3;
	}

	public void setParam3(String param3) {
		this.param3 = param3;
	}

	public String getParam4() {
		return param4;
	}

	public void setParam4(String param4) {
		this.param4 = param4;
	}

	public String getParam5() {
		return param5;
	}

	public void setParam5(String param5) {
		this.param5 = param5;
	}

	public String getParam6() {
		return param6;
	}

	public void setParam6(String param6) {
		this.param6 = param6;
	}

	public String getParam7() {
		return param7;
	}

	public void setParam7(String param7) {
		this.param7 = param7;
	}

	public String getParam8() {
		return param8;
	}

	public void setParam8(String param8) {
		this.param8 = param8;
	}

	public String getParam9() {
		return param9;
	}

	public void setParam9(String param9) {
		this.param9 = param9;
	}

	public String getParam10() {
		return param10;
	}

	public void setParam10(String param10) {
		this.param10 = param10;
	}

	public String getParam11() {
		return param11;
	}

	public void setParam11(String param11) {
		this.param11 = param11;
	}

	public String getParam12() {
		return param12;
	}

	public void setParam12(String param12) {
		this.param12 = param12;
	}
}
