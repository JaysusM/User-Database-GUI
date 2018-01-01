import java.util.Comparator;

public class descendingUserComparator implements Comparator<user> {

    public int compare(user u1, user u2)
    {
        int res = Integer.compare(u1.getId(), u2.getId());
        return (res == 0 ? u1.getSurname().compareTo(u2.getSurname()) : res);
    }
}
