package com.emma.springboot.kafka.streaming.reducer;

import com.emma.springboot.kafka.streaming.entity.StreamItem;
import org.apache.kafka.streams.kstream.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.Objects;

public class StreamItemReducer implements Reducer<StreamItem> {
    private static final Logger LOG = LoggerFactory.getLogger(StreamItemReducer.class);

    @Override
    public StreamItem apply(StreamItem item1, StreamItem item2) {
        LOG.info("#apply recv item1 non-null status {}, item2 non-null status {}",
                Objects.nonNull(item1), Objects.nonNull(item2));
        if (Objects.isNull(item1) || CollectionUtils.isEmpty(item1.getValues())) {
            LOG.info("#apply recv item1 is empty, return item2");
            return item2;
        } else if (Objects.isNull(item2) || CollectionUtils.isEmpty(item2.getValues())) {
            LOG.info("#apply recv item1 is empty, return item2");
            return item1;
        } else {
            LOG.info("#apply recv item1 and item2 non-empty, merge them and return!");
            item1.getValues().addAll(item2.getValues());
            return item1;
        }
    }
}
