package in.varadharajan.quant.candlesticks

sealed trait Sentiment

sealed trait BullishSentiment extends Sentiment

sealed trait NeutralSentiment extends Sentiment

sealed trait BearishSentiment extends Sentiment

sealed trait Pattern

sealed trait SingleCandlePattern {
  def applicable(candle: Candle): Boolean
}

object SingleCandlePatterns {

  def apply(candle: Candle): Iterable[SingleCandlePattern] =
  patternList.filter(x => x.applicable(candle))

  private def patternList: Iterable[SingleCandlePattern] = List(
  Doji
  )
}

case object Doji extends SingleCandlePattern with NeutralSentiment {
  override def applicable(candle: Candle): Boolean =
    candle.spreadRatio <= 0.1 &&
      candle.highWick >= candle.spread &&
      candle.lowWick >= candle.spread()
}
