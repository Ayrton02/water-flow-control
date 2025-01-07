package waterflow.domain.exceptions;

import io.netty.handler.codec.dns.AbstractDnsRecord;

public enum ExceptionCodes {
    NEGATIVE_VOLUME,
    WATER_OVERFLOW,
    SAFETY_THRESHOLD,
    CONTAINER_ALREADY_FULL,
    WATER_FLOW_SESSION,
    INVALID_VOLUME_TYPE,
    INVALID_TIME_MEASUREMENT_UNIT
}
