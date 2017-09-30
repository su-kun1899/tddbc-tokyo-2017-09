package red.sukun1899.tddbc;

/**
 * @author aha-oretama
 */
public class SemanticVersioning {

    private final int major;
    private final int minor;
    private final int patch;

    public SemanticVersioning(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    public String getVersion() {
        return String.join(".", String.valueOf(major), String.valueOf(minor), String.valueOf(patch));
    }
}
