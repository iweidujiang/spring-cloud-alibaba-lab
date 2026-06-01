package io.github.iweidujiang.lab15.common.seata;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.seata.rm.datasource.undo.parser.spi.JacksonSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Seata undo_log 中 LocalDateTime 序列化扩展。
 *
 * @author 苏渡苇
 */
public class LocalDateTimeJacksonSerializer implements JacksonSerializer<LocalDateTime> {

    private static final String NORM_DATETIME_MS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 返回支持的类型。
     *
     * @return LocalDateTime 类型
     */
    @Override
    public Class<LocalDateTime> type() {
        return LocalDateTime.class;
    }

    /**
     * 返回序列化器。
     *
     * @return LocalDateTime 序列化器
     */
    @Override
    public JsonSerializer<LocalDateTime> ser() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(NORM_DATETIME_MS_PATTERN));
    }

    /**
     * 返回反序列化器。
     *
     * @return LocalDateTime 反序列化器
     */
    @Override
    public JsonDeserializer<? extends LocalDateTime> deser() {
        return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(NORM_DATETIME_MS_PATTERN));
    }
}
