package red.sukun1899.tddbc

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author aha-oretama
 */
@Unroll
class SemanticVersioningSpec extends Specification {

    def 'バージョンオブジェクトを作成する'() {
        expect:
        SemanticVersioning semanticVersioning = new SemanticVersioning(1, 4, 2)
    }

    def 'Majorが#major, Minorが#minor、Patchが#patchのバージョニング文字列表現が#expectedであること'() {
        given:
        SemanticVersioning semanticVersioning = new SemanticVersioning(major, minor, patch)

        when:
        def actual = semanticVersioning.getVersion()

        then:
        actual == expected

        where:
        major | minor | patch || expected
        1     | 4     | 2     || '1.4.2'
        2     | 0     | 9     || '2.0.9'
    }

    def '#semanticVersioning1.getVersion()と#semanticVersioning2.getVersion()のバージョンオブジェクトが#labelこと'() {
        expect:
        semanticVersioning1.equals(semanticVersioning2) == expected

        where:
        semanticVersioning1             | semanticVersioning2             || expected | label
        new SemanticVersioning(1, 4, 2) | new SemanticVersioning(1, 4, 2) || true     | '等しい'
        new SemanticVersioning(1, 4, 2) | new SemanticVersioning(2, 0, 9)|| false    | '等しくない'
    }
}
