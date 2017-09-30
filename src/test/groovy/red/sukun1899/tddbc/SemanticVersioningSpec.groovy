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
        semanticVersioning1             | semanticVersioning2              || expected | label
        new SemanticVersioning(1, 4, 2) | new SemanticVersioning(1, 4, 2)  || true     | '等しい'
        new SemanticVersioning(1, 4, 2) | new SemanticVersioning(2, 0, 9)  || false    | '等しくない'
        new SemanticVersioning(1, 4, 2) | new SemanticVersioning(1, 4, 3)  || false    | '等しくない'
        new SemanticVersioning(1, 4, 2) | new SemanticVersioning(1, 0, 2)  || false    | '等しくない'
        new SemanticVersioning(1, 4, 2) | new SemanticVersioning(99, 4, 2) || false    | '等しくない'
    }

    def '同一インスタンスは等価であること'() {
        setup:
        def semanticVersioning1 = new SemanticVersioning(1, 4, 2)
        def semanticVersioning2 = semanticVersioning1

        expect:
        semanticVersioning1.equals(semanticVersioning2)
    }

    def '異なるクラスで比較した場合は等価でないこと'() {
        def semanticVersioning1 = new SemanticVersioning(1, 4, 2)

        expect:
        !semanticVersioning1.equals(semanticVersioning2)

        where:
        semanticVersioning2 | _
        new ArrayList()     | _
        null                | _
    }

    def '#label 例外が発生する'() {
        when:
        new SemanticVersioning(major, minor, patch)

        then:
        def e = thrown(IllegalArgumentException)
        e.getMessage() == message

        where:
        major | minor | patch | label           || message
        -1    | 4     | 2     | 'majorが負の整数の場合' || 'major is invalid: -1'
        1     | -4    | 2     | 'minorが負の整数の場合' || 'minor is invalid: -4'
        1     | 4     | -2    | 'patchが負の整数の場合' || 'patch is invalid: -2'
    }
}
