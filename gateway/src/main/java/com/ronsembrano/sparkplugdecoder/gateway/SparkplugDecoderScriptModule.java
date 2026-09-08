package com.ronsembrano.sparkplugdecoder.gateway;

import org.eclipse.tahu.message.SparkplugBPayloadDecoder;
import org.eclipse.tahu.message.model.SparkplugBPayload;

import java.util.HashMap;
import java.util.Map;

import java.util.List;
import java.util.ArrayList;

import org.eclipse.tahu.message.model.Metric;


public class SparkplugDecoderScriptModule {
    /**
     * Decodes a raw Sparkplug B birth message payload into a flattened
     * Map/List structure consumable from scripting, without exposing
     * Tahu's own object model.
     *
     * @param payload the raw, protobuf-encoded Sparkplug B birth message bytes
     * @return a Map containing "metrics" (a List of per-metric Maps),
     * plus the payload's own "timestamp" and "seq"
     * @throws Exception if the bytes are not a valid Sparkplug B payload
     */
    public Map<String, Object> decodeSparkplugBirth(byte[] payload) throws Exception {
        SparkplugBPayloadDecoder decoder = new SparkplugBPayloadDecoder();
        SparkplugBPayload decodedPayload = decoder.buildFromByteArray(payload, null);
        List<Metric> metrics = decodedPayload.getMetrics();
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> metricsList = new ArrayList<>();
        result.put("metrics", metricsList);
        result.put("timestamp", decodedPayload.getTimestamp());
        result.put("seq", decodedPayload.getSeq());


        for (Metric metric : metrics) {
            Map<String, Object> metricMap = new HashMap<>();
            metricMap.put("name", metric.getName());
            metricMap.put("value", metric.getValue());
            metricMap.put("alias", metric.getAlias());
            metricMap.put("datatype", metric.getDataType());
            metricMap.put("timestamp", metric.getTimestamp());
            metricsList.add(metricMap);
        }

        return result;
    }
}