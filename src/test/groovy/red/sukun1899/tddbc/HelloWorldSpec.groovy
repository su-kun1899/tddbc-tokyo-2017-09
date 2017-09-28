package red.sukun1899.tddbc

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author su-kun1899
 */
@Unroll
class HelloWorldSpec extends Specification {
    def 'Hello, spock!'() {
        given:
        def hw = new HelloWorld()

        when:
        def actual = hw.hello()

        then:
        actual == 'Hello, world!!'
    }
}
