public class Mahasiswa {
    String name;

    public Mahasiswa (String name) {
        this.name = name;
    }

    @Override
    public String toString() { 
        return name;
    }

    @Override
    public boolean equals (Object a) {
        if (this == a) {
            return true;
        }
        if (!(a instanceof Mahasiswa)) {
            return false;
        }
        Mahasiswa mhs = (Mahasiswa) a;
        return name.equalsIgnoreCase(mhs.name);
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }
}
