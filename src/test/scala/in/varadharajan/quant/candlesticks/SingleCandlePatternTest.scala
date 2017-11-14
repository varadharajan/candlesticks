package in.varadharajan.quant.candlesticks

import org.joda.time.DateTime
import org.scalatest.{FlatSpec, Matchers}

class SingleCandlePatternsTest extends FlatSpec with Matchers {
  "apply" should "identify Doji pattern" in {
    SingleCandlePatterns(Candle(100, 110, 100, 108, 100, DateTime.now)) shouldBe List()
    SingleCandlePatterns(Candle(100, 105, 95, 101, 100, DateTime.now)) shouldBe List(Doji)
  }

  "apply" should "identify Hammer pattern" in {
    SingleCandlePatterns(Candle(100, 110, 97, 99, 1000, DateTime.now)) shouldBe List(Hammer)
  }
}

class BearishEngulfingPatternTest extends FlatSpec with Matchers {
  "applicable" should "identify Bearish Engulfing pattern" in {
    val leftCandle = Candle(100, 110, 95, 105, 100, DateTime.now)
    val rightCandle = Candle(106, 110, 90, 99, 100, DateTime.now)
    BearishEngulfing.applicable(leftCandle, rightCandle) shouldBe true
  }

  "applicable" should "not classify as Bearish Engulfing pattern" in {
    val leftCandle = Candle(100, 110, 95, 105, 100, DateTime.now)
    val rightCandle = Candle(106, 110, 90, 101, 100, DateTime.now)
    BearishEngulfing.applicable(leftCandle, rightCandle) shouldBe false
  }
}

class BullishEngulfingPatternTest extends FlatSpec with Matchers {
  "applicable" should "identify Bullish Engulfing pattern" in {
    val leftCandle = Candle(100, 110, 95, 105, 100, DateTime.now)
    val rightCandle = Candle(99, 120, 90, 106, 100, DateTime.now)
    BullishEngulfing.applicable(leftCandle, rightCandle) shouldBe true
  }

  "applicable" should "not classify as Bullish Engulfing pattern" in {
    val leftCandle = Candle(100, 110, 95, 105, 100, DateTime.now)
    val rightCandle = Candle(106, 110, 90, 101, 100, DateTime.now)
    BullishEngulfing.applicable(leftCandle, rightCandle) shouldBe false
  }
}