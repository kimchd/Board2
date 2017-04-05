package org.zerock.persistence;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Pager {

	// 현재 페이지 번호
	private int current;
	// 토탈
	private int total;
	// start
	private int start;
	// end
	private int end;
	// prev
	private boolean prev;
	// next
	private boolean next;

	// default
	private double viewCount = 10.0;

	public Pager(int pageNum, int totalCount) {

		if (pageNum <= 0) {
			pageNum = 1;
		}

		this.current = pageNum;
		this.total = totalCount;
		calcPage();
	}

	private void calcPage() {

		int tempEnd = (int) (Math.ceil(this.current / viewCount) * 10);
		this.start = tempEnd - 9;
		this.end = tempEnd * viewCount > total ? (int) Math.ceil(total / viewCount) : tempEnd;

		this.prev = this.start == 1 ? false : true;
		this.next = this.total > this.end * viewCount ? true : false;
	}

}
