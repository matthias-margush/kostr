package wordcount

import mu.KotlinLogging
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.kstream.KStreamBuilder
import java.util.concurrent.TimeUnit

private val log = KotlinLogging.logger {}

fun main(args: Array<String>) {
    val builder = KStreamBuilder()

    for (topology in configs.topologies) {
        log.debug("building topology: $topology")
        topology(builder)
    }

    val streams = KafkaStreams(builder, configs.kafka())

    log.info("Starting stream")

    streams.start()
    Thread.sleep(5000)
    streams.close(1000, TimeUnit.MILLISECONDS)
    log.info("Done")
}