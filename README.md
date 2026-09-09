# Sparkplug B Birth Decoder

An Ignition custom module that decodes Sparkplug B birth messages (NBIRTH/DBIRTH)
into a plain Map, exposed as a Gateway-scope scripting function.

## What It Does

Registers `system.ron.decodeSparkplugBirth(byte[] payload)` under the Ignition
scripting API. Given the raw, protobuf-encoded bytes of a Sparkplug B birth
message, it returns a flattened `Map`:

- `metrics`: a List of Maps, one per metric, each with `name`, `value`,
  `alias`, `datatype`, and `timestamp`
- `timestamp`: the payload-level timestamp
- `seq`: the payload's sequence number

Decoding itself is handled by [Eclipse Tahu](https://github.com/eclipse/tahu),
the reference Sparkplug B library, rather than hand-parsing protobuf. This
module handles the SDK integration itself and flattens Tahu's object model
into a plain Map/List structure that's easy to consume from Jython scripting,
without coupling callers to Tahu's own classes.

## Requirements

- Ignition 8.3.9 or later
- Gateway scope only. This function is not available in Designer's
  interactive Script Console. Call it from a Gateway Timer Script, Tag Change
  Script, or from Perspective (Perspective component/session scripts run in
  Gateway scope).
- JDK 17, if building from source

## Installing

Download the latest `.modl` from this repo's
[Releases](https://github.com/ron-sembrano/sparkplug-decoder/releases) page,
or build it from source (below). Then in the Gateway web UI go to
**Platform → Modules → Install or Upgrade a Module** and select the file.

This module is currently unsigned. Ignition Gateways block unsigned modules
by default, so you'll need to explicitly allow them:

- **Docker:** pass `-Dignition.allowunsignedmodules=true` as a Java argument
  in your `docker-compose.yml`'s `command:`.
- **Native install:** add `-Dignition.allowunsignedmodules=true` as a JVM
  argument in `ignition.conf`.

## Building from Source

```bash
git clone https://github.com/ron-sembrano/sparkplug-decoder.git
cd sparkplug-decoder
./gradlew build
```

The built module will be at `build/Sparkplug-B-Birth-Decoder.unsigned.modl`.

## Usage

```python
result = system.ron.decodeSparkplugBirth(payload_bytes)
```

## Testing

- **Unit test:** `gateway/src/test/java/.../SparkplugDecoderScriptModuleTest.java`
  builds a synthetic payload with Tahu's own encoder, decodes it through this
  module, and asserts the result; a round-trip test that doesn't require a
  live Gateway or broker.
- **Live demo:** verified on a real Gateway using a simple Perspective view
  with a button (not included in this repo) that passes a known-good encoded
  payload, the same bytes used in the unit test, into
  `system.ron.decodeSparkplugBirth` and logs the decoded result to the
  Gateway logs.

## Known Limitations

- **Birth messages only:** NDATA/DDATA aren't handled yet. Decoding those
  correctly would require caching alias-to-name mappings from a prior birth
  message, a good candidate for a future version.
- **Limited datatype coverage:** verified against a single `Double`-typed
  metric. Less common Sparkplug B types (arrays, `Template`, `DataSet`) and
  multi-metric or property-bearing payloads haven't been tested.
- **Synthetic testing only:** payloads are built and decoded using Eclipse
  Tahu on both ends, not captured from a real Sparkplug B publisher via a
  live MQTT broker.

## License

MIT