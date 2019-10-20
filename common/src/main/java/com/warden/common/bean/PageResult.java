package com.warden.common.bean;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author YangJiaYing
 * @date 2018/10/31
 */
@Data
public class  PageResult<T> {
    private List<T> list;
    private Pagination pagination;


    public PageResult(List<T> list, Integer current, Integer pageSize, long total) {
        this.list = list;
        this.pagination = new Pagination(current, pageSize, total);
    }

    public PageResult(Page<T> page) {
        this.list = page.getContent();
        this.pagination = new Pagination(page.getNumber(), page.getSize(), page.getTotalElements());
    }

    class Pagination {
        private int current;
        private int pageSize;
        private long total;

        private Pagination(int current, int pageSize, long total) {
            this.current = current + 1;
            this.pageSize = pageSize;
            this.total = total;
        }
        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public long getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

}
