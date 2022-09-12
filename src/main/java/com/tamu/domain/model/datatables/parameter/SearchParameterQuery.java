package com.tamu.domain.model.datatables.parameter;

public class SearchParameterQuery {

	private String key;
	private String value;
	private boolean matchModeEquals;

	public SearchParameterQuery() {
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isMatchModeEquals() {
		return matchModeEquals;
	}

	public void setMatchModeEquals(boolean matchModeEquals) {
		this.matchModeEquals = matchModeEquals;
	}
}