package org.pagination;

import java.util.List;

public interface Pagination<T> {
	public Long getTotalElements();
	public List<T> getPageList();
}
