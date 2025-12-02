package com.solvd.delivery;

import com.solvd.delivery.util.TimeStampAdapter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Timestamp;

public class TimestampAdapterTest {

    @Test
    public void marshalUnmarshalRoundTrip() throws Exception {
        TimeStampAdapter adapter = new TimeStampAdapter();
        Timestamp ts = Timestamp.valueOf("2024-01-02 03:04:05");
        String s = adapter.marshal(ts);
        Assert.assertEquals(s, "2024-01-02T03:04:05");
        Timestamp parsed = adapter.unmarshal(s);
        Assert.assertEquals(parsed, ts);
    }

    @Test
    public void handleNulls() throws Exception {
        TimeStampAdapter adapter = new TimeStampAdapter();
        Assert.assertNull(adapter.marshal(null));
        Assert.assertNull(adapter.unmarshal("   "));
    }
}
