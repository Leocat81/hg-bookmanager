package org.pagination;

public interface JspPagination<T> extends Pagination<T> {
	public Integer getPageIndex();
	public Integer getPageSize();
	public Integer getPreviousIndex();
	public Integer getNextIndex();
	public Integer getTotalPages();
	public Boolean getIsFirst();
	public Boolean getIsLast();
	public Boolean getIsPrevious();
	public Boolean getIsNext();
	public void setPageNumber(Integer pageNumber);
	public BetweenIndex getBetweenIndex();
}
