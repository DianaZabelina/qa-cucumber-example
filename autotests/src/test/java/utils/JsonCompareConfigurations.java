package utils;

import java.util.Map;

import net.javacrumbs.jsonunit.core.Configuration;

import static net.javacrumbs.jsonunit.core.Configuration.empty;
import static net.javacrumbs.jsonunit.core.Option.*;

public class JsonCompareConfigurations {

    public static final Map<String, Configuration> CONFIGURATIONS = Map.of(
            "STRICT", empty(), // no options at all — one-to-one comparison
            "EXTRA_FIELDS", empty().withOptions(IGNORING_EXTRA_FIELDS), // only extra fields are ignored
            "STRICT_ORDER", empty().withOptions(IGNORING_ARRAY_ORDER) // order doesn't matter, but everything else is strict
    );
}