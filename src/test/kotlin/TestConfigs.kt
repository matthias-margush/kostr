package kostr

import org.apache.kafka.streams.StreamsConfig.BOOTSTRAP_SERVERS_CONFIG
import org.apache.kafka.clients.consumer.ConsumerConfig.FETCH_MIN_BYTES_CONFIG
import org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG
import java.lang.System.getenv
import java.util.*

object testConfigs {
    fun kafka(): Properties {
        val props = Properties()
        props[BOOTSTRAP_SERVERS_CONFIG] = getenv("BOOTSTRAP_SERVERS") ?: "localhost:9092"
        props[GROUP_ID_CONFIG] = getenv("GROUP_ID") ?: "wordcount-test-" + UUID.randomUUID()
        props[FETCH_MIN_BYTES_CONFIG] = getenv("FETCH_MIN_BYTES") ?: 1
        return props
    }
}