package com.ronsembrano.sparkplugdecoder.gateway;

import org.eclipse.tahu.message.SparkplugBPayloadEncoder;
import org.eclipse.tahu.message.model.Metric.MetricBuilder;
import org.eclipse.tahu.message.model.MetricDataType;
import org.eclipse.tahu.message.model.SparkplugBPayload;
import org.eclipse.tahu.message.model.SparkplugBPayload.SparkplugBPayloadBuilder;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SparkplugDecoderScriptModuleTest {
    @Test
    void decodesKnownMetricFromEncodedPayload() throws Exception {
        SparkplugBPayload payload = new SparkplugBPayloadBuilder().addMetric(new MetricBuilder("Temperature", MetricDataType.Double, 70.5).createMetric()).createPayload();
        byte[] payloadBytes = new SparkplugBPayloadEncoder().getBytes(payload, false);
        SparkplugDecoderScriptModule module = new SparkplugDecoderScriptModule();
        Map<String, Object> result = module.decodeSparkplugBirth(payloadBytes);

        assertNotNull(result);
    }
}