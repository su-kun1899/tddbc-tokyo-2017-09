package red.sukun1899.tddbc;

/**
 * @author aha-oretama
 */
public class SemanticVersioning {

    private final int major;
    private final int minor;
    private final int patch;

    public SemanticVersioning(int major, int minor, int patch) {
        if (major < 0 || minor < 0 || patch < 0) {
            throw new IllegalArgumentException(String.format("引数に負の値は設定できません。 major:%d,minor:%d,patch:%d", major, minor, patch));
        }

        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    public String getVersion() {
        return String.join(".", String.valueOf(major), String.valueOf(minor), String.valueOf(patch));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SemanticVersioning that = (SemanticVersioning) o;

        return major == that.major && minor == that.minor && patch == that.patch;
    }

    @Override
    public int hashCode() {
        int result = major;
        result = 31 * result + minor;
        result = 31 * result + patch;
        return result;
    }
}
