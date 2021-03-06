package red.sukun1899.tddbc

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author aha-oretama
 */
@Unroll
class SemanticVersioningSpec extends Specification {

    def '#label バージョンオブジェクトが生成できる'() {
        expect:
        new SemanticVersioning(major, minor, patch)

        where:
        major | minor | patch | label
        1     | 4     | 2     | 'major:1,minor:4,patch:2 の場合'
        0     | 4     | 2     | 'major:0,minor:4,patch:2 の場合'
        1     | 0     | 2     | 'major:1,minor:0,patch:2 の場合'
        1     | 4     | 0     | 'major:1,minor:4,patch:0 の場合'
        0     | 0     | 0     | 'major:0,minor:0,patch:0 の場合'
    }

    def '#label バージョンオブジェクトが生成できず例外が発生する'() {
        when:
        new SemanticVersioning(major, minor, patch)

        then:
        def e = thrown(IllegalArgumentException)
        e.getMessage() == message

        where:
        major | minor | patch | label                          || message
        -1    | 4     | 2     | 'majorが負の整数の場合'                || '引数に負の値は設定できません。 major:-1,minor:4,patch:2'
        1     | -4    | 2     | 'minorが負の整数の場合'                || '引数に負の値は設定できません。 major:1,minor:-4,patch:2'
        1     | 4     | -2    | 'patchが負の整数の場合'                || '引数に負の値は設定できません。 major:1,minor:4,patch:-2'
        -1    | -4    | -2    | 'major,minor,patchすべてが負の整数の場合' || '引数に負の値は設定できません。 major:-1,minor:-4,patch:-2'
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

    def '#semanticVersioning.getVersion()のパッチバージョンをアップすると#expectedになる'() {
        when:
        semanticVersioning."$method"()

        then:
        semanticVersioning.getVersion() == expected

        where:
        method           | semanticVersioning              | label       || expected
        "incrementPatch" | new SemanticVersioning(1, 4, 2) | 'パッチアップデート' || '1.4.3'
        "incrementPatch" | new SemanticVersioning(1, 4, 0) | 'パッチアップデート' || '1.4.1'
        "incrementPatch" | new SemanticVersioning(1, 4, 9) | 'パッチアップデート' || '1.4.10'
    }
}
