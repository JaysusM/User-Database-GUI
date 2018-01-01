public class user {

    public class profilePic
    {
        byte[] profPic;
        String extension;

        public profilePic(byte[] profPic, String extension)
        {
            this.profPic = profPic;
            this.extension = extension;
        }

        public byte[] getProfPic() {
            return profPic;
        }

        public String getExtension() {
            return extension;
        }

    }

    private int id;
    private String name, surname;

    private profilePic profPic;

    public user(int id, String name, String surname, byte[] profPic, String extension)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.profPic = new profilePic(profPic, extension);
    }

    public user(int id) {
        this(id, null, null, null, null);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public profilePic getProfPic() {
        return profPic;
    }

    public String toString() {
        return "(" + getId() + ", " + getName() + getSurname() + ")";
    }

    public boolean equals(Object obj) {
        return (obj instanceof user) && (this.id == ((user) obj).getId());
    }
}
