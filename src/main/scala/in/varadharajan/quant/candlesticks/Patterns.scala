package in.varadharajan.quant.candlesticks

sealed trait Sentiment

sealed trait BullishSentiment extends Sentiment

sealed trait UnclearSentiment extends Sentiment

sealed trait BearishSentiment extends Sentiment

sealed trait Pattern

sealed trait SingleCandlePattern extends Pattern {
  def applicable(candle: Candle): Boolean
}

sealed trait TwoCandlePattern extends Pattern {
  def applicable(left: Candle, right: Candle): Boolean
}

object SingleCandlePatterns {

  def apply(candle: Candle): Iterable[SingleCandlePattern] =
  patternList.filter(x => x.applicable(candle))

  private def patternList: Iterable[SingleCandlePattern] = List(
    Doji,
    Hammer
  )
}

object TwoCandlePatterns {

  def apply(left: Candle, right: Candle): Iterable[TwoCandlePattern] =
    patternList.filter(x => x.applicable(left, right))

  private def patternList: Iterable[TwoCandlePattern] = List(
    BearishEngulfing,
    BullishEngulfing
  )
}


case object Doji extends SingleCandlePattern with UnclearSentiment {
  override def applicable(candle: Candle): Boolean =
    candle.spreadRatio <= 0.1 &&
      candle.highWick / candle.spreadWithWicks >= 0.3 &&
      candle.lowWick / candle.spreadWithWicks >= 0.3
}

case object Hammer extends SingleCandlePattern with UnclearSentiment {
  override def applicable(candle: Candle): Boolean =
    candle.spreadRatio <= 0.1 && (
      (candle.highWick / candle.spreadWithWicks <= 0.3) || (candle.lowWick / candle.spreadWithWicks <= 0.3))
}

case object BearishEngulfing extends TwoCandlePattern with BearishSentiment {
  override def applicable(left: Candle, right: Candle) = right.colour.equals(Red) &&
    right.open >= Math.max(left.open, left.close) &&
    right.close <= Math.min(left.open, left.close)
}

case object BullishEngulfing extends TwoCandlePattern with BullishSentiment {
  override def applicable(left: Candle, right: Candle) = right.colour.equals(Green) &&
    right.open <= Math.min(left.open, left.close) &&
    right.close >= Math.max(left.open, left.close)

}