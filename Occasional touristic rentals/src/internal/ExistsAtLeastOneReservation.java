package internal;

import java.util.ArrayList;
import java.util.function.Predicate;

public class ExistsAtLeastOneReservation implements Predicate<ArrayList<Reservation>> {
    @Override
    public boolean test(ArrayList<Reservation> reservations) {
        boolean reservation = false;
        if (reservations.size() != 0) {
            int i = 0;
            while (i < reservations.size() && !reservation) {
                if (reservations.get(i) != null) {
                    reservation = true;
                }
                i++;
            }
        }
        return reservation;
    }
}
