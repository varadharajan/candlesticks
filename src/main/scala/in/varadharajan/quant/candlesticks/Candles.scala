package in.varadharajan.quant.candlesticks

import in.varadharajan.quant.candlesticks.Types.{PricePoint, Volume}
import org.joda.time.DateTime

object Types {
  type PricePoint = Double
  type Volume = Long
}

sealed trait CandleColour

case object Red extends CandleColour

case object Green extends CandleColour

sealed trait CandleTrait {
  def open: PricePoint

  def high: PricePoint

  def low: PricePoint

  def close: PricePoint

  def volume: Volume

  def timestamp: DateTime

  def spread(): Double = Math.abs(close - open)

  def spreadWithWicks(): Double = high - low

  def colour: CandleColour = if (open > close) Red else Green
}

case class Candle(open: PricePoint,
                  high: PricePoint,
                  low: PricePoint,
                  close: PricePoint,
                  volume: Volume,
                  timestamp: DateTime) extends CandleTrait
