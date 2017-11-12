
package org.example.tariff.resources;

import java.io.Serializable;
import java.util.List;


import org.springframework.data.domain.Page;


public  class CollectionResource<D> implements Serializable
{
	private List<D> content;
	private long totalRecords;
	private int currentPage;
	private int pageSize;
	private boolean hasNextPage;
	private boolean hasPrevPage;
        

	public CollectionResource(Page<D> pageData)
	{
		this.content = pageData.getContent();
		this.totalRecords = pageData.getTotalElements();
		this.currentPage = pageData.getNumber();
		this.pageSize = pageData.getSize();
		this.hasNextPage = pageData.hasNext();
		this.hasPrevPage = pageData.hasPrevious();
	}

	

	public List<D> getContent()
	{
		return content;
	}

	public void setContent(List<D> content)
	{
		this.content = content;
	}

	public long getTotalRecords()
	{
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords)
	{
		this.totalRecords = totalRecords;
	}

	public int getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public boolean isHasNextPage()
	{
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage)
	{
		this.hasNextPage = hasNextPage;
	}

	public boolean isHasPrevPage()
	{
		return hasPrevPage;
	}

	public void setHasPrevPage(boolean hasPrevPage)
	{
		this.hasPrevPage = hasPrevPage;
	}

	

}
