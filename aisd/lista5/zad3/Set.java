public class Set {
    static void makeSet(SetElement element) {
        element.up = element;
        element.rank = 0;
    }

    static SetElement findSet(SetElement element) {
        SetElement set = element;
        while(set.up != set) {
            set = set.up;
        }
        return set;
    }

    static void  setUnion(SetElement element1, SetElement element2) {
        SetElement set1 = findSet(element1);
        SetElement set2 = findSet(element2);

        if(set1 == set2) return;

        if(set1.rank > set2.rank) set2.up = set1;
        else set1.up = set2;

        if(set1.rank == set2.rank) set2.rank++;
    }
}
