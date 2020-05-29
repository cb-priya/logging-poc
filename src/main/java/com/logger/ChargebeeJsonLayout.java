package com.logger;

import com.google.gson.JsonObject;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginConfiguration;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.impl.ThrowableProxy;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;

import java.nio.charset.Charset;


@Plugin(name = "ChargebeeJsonLayout", category = Node.CATEGORY, elementType = Layout.ELEMENT_TYPE)
public class ChargebeeJsonLayout extends AbstractStringLayout {

    private static final String DEFAULT_EOL = "\r\n";

    private final boolean locationInfo;

    public ChargebeeJsonLayout(Configuration config, Charset aCharset, Serializer headerSerializer,
                               Serializer footerSerializer, boolean locationInfo) {
        super(config, aCharset, headerSerializer, footerSerializer);
        this.locationInfo = locationInfo;
    }

    @PluginFactory
    public static ChargebeeJsonLayout createLayout(@PluginConfiguration final Configuration config,
                                                   @PluginAttribute(value = "charset", defaultString = "US-ASCII") final Charset charset,
                                                   @PluginAttribute(value = "locationInfo", defaultBoolean = false) final boolean locationInfo) {
        return new ChargebeeJsonLayout(config, charset, null, null, locationInfo);
    }

    @Override
    public String toSerializable(LogEvent event) {

        JsonObject jsonObject = new JsonObject();

        // Log Information
        jsonObject.addProperty("level", event.getLevel().name());
        jsonObject.addProperty("thread", event.getThreadName());
        jsonObject.addProperty("thread_id", event.getThreadId());
        jsonObject.addProperty("logger", event.getLoggerName());
        jsonObject.addProperty("timestamp", event.getInstant().getEpochMillisecond());

        // Log Location Information
        if (locationInfo) {
            final StackTraceElement source = event.getSource();
            jsonObject.addProperty("source", source.getClassName() + "." + source.getMethodName() + ":" + source.getLineNumber());
        }

        jsonObject.addProperty("message", event.getMessage().getFormattedMessage());

        if(event.getContextMap()!= null) {
            if(event.getContextMap().containsKey("domain")) {
                jsonObject.addProperty("domain", event.getContextMap().get("domain"));
            }
            if(event.getContextMap().containsKey("request_id")) {
                jsonObject.addProperty("request_id", event.getContextMap().get("request_id"));
            }
        }

        // Exceptions
        if (event.getThrownProxy() != null) {
            final ThrowableProxy thrownProxy = event.getThrownProxy();
            final Throwable throwable = thrownProxy.getThrowable();

            final String exceptionsClass = throwable.getClass().getCanonicalName();
            if (exceptionsClass != null) {
                jsonObject.addProperty("exception", exceptionsClass);
            }

            final String exceptionsMessage = throwable.getMessage();
            if (exceptionsMessage != null) {
                jsonObject.addProperty("cause", exceptionsMessage);
            }

            final String stackTrace = thrownProxy.getExtendedStackTraceAsString("");
            if (stackTrace != null) {
                jsonObject.addProperty("stack_trace", stackTrace);
            }
        }

        return jsonObject.toString().concat(DEFAULT_EOL);
    }

}
