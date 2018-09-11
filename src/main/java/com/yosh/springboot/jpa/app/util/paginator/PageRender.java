package com.yosh.springboot.jpa.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {

	private String url;
	private Page<T> page;
	private List<PageItem> pages;
	
	private int totalPages;
	private int numElementPerPage;
	private int currentPage;
	
	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.pages = new ArrayList<PageItem>();
		
		this.numElementPerPage = page.getSize();
		this.totalPages = page.getTotalPages();
		this.currentPage = page.getNumber() +1;  // Starts with 0
		
		int from, to;
		if (this.totalPages <= this.numElementPerPage) {
			from = 1;
			to = this.totalPages;
			
		} else {
			if (this.currentPage <= this.numElementPerPage/2) {
				from = 1;
				to = this.numElementPerPage;
				
			} else if (this.currentPage >= this.totalPages - this.numElementPerPage/2) {
				from = this.totalPages - this.numElementPerPage +1;
				to = this.numElementPerPage;
			} else {
				from = this.currentPage - this.numElementPerPage/2;
				to = this.numElementPerPage;
			}
		}
		
		for (int i=0; i < to; i++) {
			this.pages.add(new PageItem(from+i, this.currentPage == from+i));
		}
	}

	public String getUrl() {
		return url;
	}

	public List<PageItem> getPages() {
		return pages;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	
	public boolean isFirst() {
		return this.page.isFirst();
	}
	
	public boolean isHasNext() {
		return this.page.hasNext();
	}
	
	public boolean isHasPrevious() {
		return this.page.hasPrevious();
	}
	
	public boolean isLast() {
		return this.page.isLast();
	}
	
}
