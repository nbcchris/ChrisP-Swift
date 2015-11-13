package Tests

import org.scalatest._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.FlatSpec

abstract class TestBase extends FlatSpec with Matchers with OptionValues with Inside with Inspectors

//trait ParallelTestBase extends TestBase with BeforeAndAfterAll with ParallelTestExecution