package serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import util.Time;

import java.io.IOException;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-01-23
 */
public class MSToRoundedHoursSerializer extends StdSerializer<Integer> {

    public MSToRoundedHoursSerializer() {
        this(null);
    }

    private MSToRoundedHoursSerializer(Class<Integer> t) {
        super(t);
    }

    @Override
    public void serialize(Integer value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeNumber(Time.millisecondsToHours(value));
    }
}
