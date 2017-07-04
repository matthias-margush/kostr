package kostr

import org.apache.kafka.common.serialization.Serde

data class Topic<K, V>(val keySerde: Serde<K>, val valueSerde: Serde<V>, val name: String)
