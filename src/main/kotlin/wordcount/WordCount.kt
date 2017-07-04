package wordcount

import kostr.streams.*
import kostr.streams.stream
import kostr.streams.to
import kostr.streams.count
import org.apache.kafka.streams.KeyValue
import org.apache.kafka.streams.kstream.KStreamBuilder

fun wordCount(builder: KStreamBuilder) {
    val textLines = builder.stream(topics.textLines)

    textLines.flatMapValues { it.toLowerCase().split(Regex("\\s+")) }
            .map { _, word -> KeyValue(word, word) }
            .groupByKey(topics.textLines)
            .count(topics.wordCounts)
            .to(topics.wordCounts)
}
