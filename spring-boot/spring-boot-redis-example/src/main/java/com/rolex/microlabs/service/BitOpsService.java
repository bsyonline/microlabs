package com.rolex.microlabs.service;

import com.rolex.microlabs.model.PointInfo;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.connection.RedisStringCommands;

import java.util.Collection;
import java.util.List;

/**
 * @author rolex
 * @since 2020
 */
public interface BitOpsService {

    void setBit(String key, long offset, boolean val);

    Boolean getBit(String key, long offset);

    Long getBitOp(String key, RedisStringCommands.BitOperation op, String... desKey);

    Long bitCount(String key);
}
