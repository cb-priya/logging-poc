package com.logger;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.contrib.json.classic.JsonLayout;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChargebeeJsonLayout extends JsonLayout {

    public static final String DOMAIN_ATTR_NAME = "domain";
    public static final String REQUESTID_ATTR_NAME = "requestId";


    @Override
    protected Map toJsonMap(ILoggingEvent event) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        addTimestamp(TIMESTAMP_ATTR_NAME, this.includeTimestamp, event.getTimeStamp(), map);
        add(LEVEL_ATTR_NAME, this.includeLevel, String.valueOf(event.getLevel()), map);
        add(THREAD_ATTR_NAME, this.includeThreadName, event.getThreadName(), map);
        if(event.getMDCPropertyMap() != null ) {
            if (event.getMDCPropertyMap().containsKey("domain")) {
                add(DOMAIN_ATTR_NAME, this.includeMDC, event.getMDCPropertyMap().get("domain"), map);
            }
            if(event.getMDCPropertyMap().containsKey("requestId")) {
                add(REQUESTID_ATTR_NAME, this.includeMDC, event.getMDCPropertyMap().get("requestId"), map);
            }
        }
        add(LOGGER_ATTR_NAME, this.includeLoggerName, event.getLoggerName(), map);
        add("logMessage", this.includeFormattedMessage, event.getFormattedMessage(), map);
        add(MESSAGE_ATTR_NAME, this.includeMessage, event.getMessage(), map);
        add(CONTEXT_ATTR_NAME, this.includeContextName, event.getLoggerContextVO().getName(), map);
        //addMap(MDC_ATTR_NAME, this.includeMDC, event.getMDCPropertyMap(), map);
        addThrowableInfo(EXCEPTION_ATTR_NAME, this.includeException, event, map);
        addCustomDataToJsonMap(map, event);
        return map;
    }
}
