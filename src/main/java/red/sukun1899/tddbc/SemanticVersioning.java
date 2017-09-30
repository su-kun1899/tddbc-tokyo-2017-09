package red.sukun1899.tddbc;

/**
 * @author aha-oretama
 */
public class SemanticVersioning {

    private final int major;
    private final int minor;
    private final int patch;

    public SemanticVersioning(int major, int minor, int patch) {
        if (major < 0) {
            throw new IllegalArgumentException("major is invalid: " + major);
        }
        if (minor < 0) {
            throw new IllegalArgumentException("minor is invalid: " + minor);
        }
        if (patch < 0) {
            throw new IllegalArgumentException("patch is invalid: " + patch);
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

        if (major != that.major) {
            return false;
        }
        if (minor != that.minor) {
            return false;
        }
        return patch == that.patch;
    }

    @Override
    public int hashCode() {
        int result = major;
        result = 31 * result + minor;
        result = 31 * result + patch;
        return result;
    }
}
