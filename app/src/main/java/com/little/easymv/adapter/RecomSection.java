package com.little.easymv.adapter;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.little.easymv.responsebean.RecommendResponse;

/**
 * Created by Littlezuo on 2017/8/31.
 */

public class RecomSection extends SectionEntity<RecommendResponse> {

    public RecomSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public RecomSection(RecommendResponse recommendResponse) {
        super(recommendResponse);
    }
}
