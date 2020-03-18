package com.zglu.generator;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @param <T>
 * @author zglu
 */
@Data
@NoArgsConstructor
public class Page<T> {
    private long totalElements;
    private long totalPages;
    private int size;
    private int number;
    private int offset;
    private List<T> content;
    private boolean empty;
    private boolean first;
    private boolean last;

    public Page(long totalElements, Integer number, Integer size) {
        this.totalElements = totalElements;
        this.number = number;
        this.size = size;
        this.offset = number * size;
        // 页数=总数/每页数，整除时，页数为整除结果，有余数时页数需要+1。通过总数+size-1，可满足以上规则
        this.totalPages = (totalElements + size - 1) / size;
        this.empty = this.totalElements <= 0;
        this.first = number == 0;
        this.last = number >= totalPages - 1;
    }
}
