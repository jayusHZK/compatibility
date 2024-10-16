package com.jayus.smallMyBatis.step12.session;

/**
 * @ClassName RowBounds
 * @Description: 分页记录限制
 * @date: 2024/9/25 08:31
 */
public class RowBounds {

    public static final int NO_ROW_OFFSET = 0;

    public static final int NO_ROW_LIMIT = Integer.MAX_VALUE;

    public static final RowBounds DEFAULT = new RowBounds();

    private int offset;

    private int limit;

    public RowBounds() {
        this.offset = NO_ROW_OFFSET;
        this.limit = NO_ROW_LIMIT;
    }

    public RowBounds(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

}
