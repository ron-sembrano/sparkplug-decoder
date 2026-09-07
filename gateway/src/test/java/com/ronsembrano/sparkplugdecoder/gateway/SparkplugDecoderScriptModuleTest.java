package com.ronsembrano.sparkplugdecoder.gateway;

import org.eclipse.tahu.SparkplugInvalidTypeException;
import org.eclipse.tahu.message.SparkplugBPayloadEncoder;
import org.eclipse.tahu.message.model.Metric.MetricBuilder;
import org.eclipse.tahu.message.model.MetricDataType;
import org.eclipse.tahu.message.model.SparkplugBPayload;
import org.eclipse.tahu.message.model.SparkplugBPayload.SparkplugBPayloadBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SparkplugDecoderScriptModuleTest {

    @Test
    void decodesKnownMetricFromEncodedPayload() throws IOException, SparkplugInvalidTypeException {
        // TODO: build a payload with Tahu's encoder, decode it, assert the result
        SparkplugBPayload payload = new SparkplugBPayloadBuilder().addMetric(new MetricBuilder("Temperature", MetricDataType.Double, 70.5).createMetric()).createPayload();
        byte[] payloadBytes = new SparkplugBPayloadEncoder().getBytes(payload, false);
        SparkplugDecoderScriptModule module = new SparkplugDecoderScriptModule();
        Map <String, Object> result = module.decodeSparkplugBirth(payloadBytes);

        assertNotNull(result);
    }
}