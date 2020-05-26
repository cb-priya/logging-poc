package com.logger;

/*import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.apache.logging.log4j.util.Strings;

import java.nio.charset.Charset;


@Plugin(name = "ChargebeeLayout", category = Node.CATEGORY, elementType = Layout.ELEMENT_TYPE)
public class ChargebeeLayout extends AbstractStringLayout {

    private final boolean locationInfo;
    private final boolean hideEnvironmentWhenNull;

    public ChargebeeLayout(Configuration config, Charset aCharset, Serializer headerSerializer, Serializer footerSerializer, boolean locationInfo, boolean hideEnvironmentWhenNull) {
        super(config, aCharset, headerSerializer, footerSerializer);
        this.locationInfo = locationInfo;
        this.hideEnvironmentWhenNull = hideEnvironmentWhenNull;
    }

    @PluginFactory
    public static ChargebeeLayout createLayout(@PluginConfiguration final Configuration config,
                                                @PluginAttribute(value = "charset", defaultString = "US-ASCII") final Charset charset,
                                                @PluginAttribute(value = "locationInfo", defaultBoolean = false) final boolean locationInfo,
                                                @PluginAttribute(value = "hideEnvironmentWhenNull", defaultBoolean = true) final boolean hideEnvironmentWhenNull) {
        return new ChargebeeLayout(config, charset, null, null, locationInfo, hideEnvironmentWhenNull);
    }

    public class ChargebeeEvent {
        public String message;
    }

    @Override
    public String toSerializable(LogEvent event) {
        ObjectMapper objectMapper = new ObjectMapper();

        ChargebeeEvent ce = new ChargebeeEvent();
        ce.message = event.getMessage().getFormattedMessage();
        event.getInstant().

        try {
            return objectMapper.writeValueAsString(ce);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
*/