package in.varadharajan.quant.candlesticks

import org.joda.time.DateTime
import org.scalacheck.Prop.forAll
import org.scalacheck.Properties
import org.scalatest.{FlatSpec, Matchers}

class CandleTest extends FlatSpec with Matchers {
  "spread" should "return the difference between close and open" in {
    Candle(100, 110, 90, 105, 1000, DateTime.now()).spread shouldBe 5
    Candle(100, 110, 90, 95, 1000, DateTime.now()).spread shouldBe 5
  }

  "spreadWithWicks" should "return the difference between high and low" in {
    Candle(100, 110, 90, 105, 1000, DateTime.now()).spreadWithWicks shouldBe 20
  }

  "colour" should "return the colour of the candle based on the open and close" in {
    Candle(100, 110, 90, 105, 1000, DateTime.now()).colour shouldBe Green
    Candle(100, 110, 90, 99, 1000, DateTime.now()).colour shouldBe Red
  }
}


class CandlePropertyTest extends Properties("Candle"){
  property("colour") = forAll { (open: Double, high: Double, low: Double, close: Double, volume: Long) =>
    Candle(open, high, low, close, volume, DateTime.now).colour match {
      case Red => open > close
      case Green => open < close
    }
  }
}