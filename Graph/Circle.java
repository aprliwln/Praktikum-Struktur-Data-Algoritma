import java.util.*;

public class Circle {
    Map<Mahasiswa, Set<Mahasiswa>> graf = new HashMap<>();

    public void addMhs (String name, List<String> friendsList) {
        Mahasiswa std = new Mahasiswa(name);
        graf.putIfAbsent(std, new HashSet<>());

        for (String friendName: friendsList) {
            Mahasiswa friend = new Mahasiswa(friendName);
            graf.putIfAbsent(friend, new HashSet<>());
            graf.get(std).add(friend);
            graf.get(friend).add(std);
        }
    }

    public void addCircle (String name1, String name2) {
        Mahasiswa m1 = new Mahasiswa(name1);
        Mahasiswa m2 = new Mahasiswa(name2);

        if (graf.containsKey(m1) && graf.containsKey(m2)) {
            graf.get(m1).add(m2);
            graf.get(m2).add(m1);
        }
    }

    public void removeMhs (String name) {
        Mahasiswa std = new Mahasiswa(name);
        if (graf.containsKey(std)) {
            for (Mahasiswa friend: graf.get(std)) {
                graf.get(friend).remove(std);
            }
            graf.remove(std);
        }
    }

    public void search (String name1, String name2) {
        Mahasiswa m1 = new Mahasiswa(name1);
        Mahasiswa m2 = new Mahasiswa(name2);

        if (graf.containsKey(m1) && graf.containsKey(m2)) {
            Set<Mahasiswa> friend1 = graf.get(m1);
            Set<Mahasiswa> friend2 = graf.get(m2);

            Set<Mahasiswa> find = new HashSet<>(friend1);
            find.retainAll(friend2);

            if (find.isEmpty()) {
                System.out.println("No mutual friends");
            }
            else {
                for (Mahasiswa m: find) {
                    System.out.println("-> " + m.name);
                }
            }
        }
    }

    public void display (String name) {
        Mahasiswa std = new Mahasiswa(name);

        if (graf.containsKey(std)) {
            System.out.println("Friends of " + name + ": ");
            for (Mahasiswa friend: graf.get(std)) {
                System.out.println("-> " + friend.name);
            }
        }
        else {
            System.out.println("Not found");
        }
    }
}
