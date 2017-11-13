package in.varadharajan.quant.candlesticks

import org.joda.time.DateTime
import org.scalatest.{FlatSpec, Matchers}

class SingleCandlePatternTest extends FlatSpec with Matchers {
  "apply" should "identify Doji pattern" in {
    SingleCandlePatterns(Candle(100, 110, 100, 108, 100, DateTime.now)) shouldBe List()
    SingleCandlePatterns(Candle(100, 105, 95, 101, 100, DateTime.now)) shouldBe List(Doji)
  }
}