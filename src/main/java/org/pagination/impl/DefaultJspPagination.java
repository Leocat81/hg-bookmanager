package org.pagination.impl;

import org.pagination.BetweenIndex;
import org.pagination.JspPagination;
import org.pagination.QueryHandler;

public class DefaultJspPagination<T> extends DefaultPagination<T> implements JspPagination<T> {
	private Integer pageNumber;
	public DefaultJspPagination(Integer pageIndex, Integer pageSize,
			QueryHandler<T> queryHandler) {
		super(pageIndex, pageSize, queryHandler);
	}
	public DefaultJspPagination(Integer pageIndex, Integer pageSize, Integer pageNumber,
			QueryHandler<T> queryHandler) {
		super(pageIndex, pageSize, queryHandler);
		setPageNumber(pageNumber);
	}

	@Override
	public Integer getPageIndex() {
		return pageIndex;
	}

	@Override
	public Integer getPageSize() {
		return pageSize;
	}

	@Override
	public Integer getPreviousIndex() {
		return getIsPrevious() ? pageIndex - 1 : pageIndex;
	}

	@Override
	public Integer getNextIndex() {
		return getIsNext() ? pageIndex + 1 : pageIndex;
	}

	@Override
	public Integer getTotalPages() {
		return totalPages;
	}

	@Override
	public Boolean getIsFirst() {
		return pageIndex <= 1;
	}

	@Override
	public Boolean getIsLast() {
		return pageIndex >= totalPages;
	}

	@Override
	public Boolean getIsPrevious() {
		return pageIndex > 1;
	}

	@Override
	public Boolean getIsNext() {
		return pageIndex < totalPages;
	}
	
	@Override
	public void setPageNumber(Integer pageNumber) {
		if (pageNumber < 1) {
			this.pageNumber = 0;
		} else if (pageNumber > totalPages && totalPages > 0) {
			this.pageNumber = totalPages;
		} else {
			this.pageNumber = pageNumber;
		}
	}
	
	@Override
	public BetweenIndex getBetweenIndex() {
		return new BetweenIndex() {
			int begin = pageIndex - (pageNumber % 2 == 0 ? pageNumber / 2 - 1 : pageNumber / 2);
			int end = pageIndex + pageNumber / 2;
			//代码块，在每次new当前匿名类的对象前执行
			{
				if (begin < 1) {
					begin = 1;
					end = pageNumber;
				}
				if (end > totalPages) {
					end = totalPages;
					begin = end - pageNumber + 1;
				}
				if (begin <= 0) begin = 0;
				if (end <= 0) end = -1;
			}
			@Override
			public Integer getEndIndex() {
				return end;
			}
			@Override
			public Integer getBeginIndex() {
				return begin;
			}
		};
	}
}
