package wordcount

import org.apache.kafka.streams.StreamsConfig.*
import org.apache.kafka.streams.kstream.KStreamBuilder
import java.lang.System.getenv
import java.util.*

object configs {
    fun kafka(): Properties {
        val props = Properties()
        props[APPLICATION_ID_CONFIG] = getenv("APPLICATION_ID") ?: "wordcount-" + UUID.randomUUID()
        props[BOOTSTRAP_SERVERS_CONFIG] = getenv("BOOTSTRAP_SERVERS") ?: "localhost:9092"
        props[COMMIT_INTERVAL_MS_CONFIG] = getenv("COMMIT_INTERVAL_MS") ?: "10"
        return props
    }

    var topologies: List<(KStreamBuilder) -> Unit> = listOf(::wordCount)
}