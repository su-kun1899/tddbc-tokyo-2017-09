package red.sukun1899.tddbc

import spock.lang.Specification

/**
 * @author aha-oretama
 */
class SemanticVersioningSpec extends Specification {

    def "バージョンオブジェクトを作成する"() {
        expect:
        SemanticVersioning semanticVersioning = new SemanticVersioning(1, 4, 2)
    }
}
