package wordcount

import kostr.Topic
import org.apache.kafka.common.serialization.Serdes

object topics {
    val textLines = Topic(Serdes.String(), Serdes.String(), "text-lines")
    val wordCounts = Topic(Serdes.String(), Serdes.Long(), "word-counts")
}
