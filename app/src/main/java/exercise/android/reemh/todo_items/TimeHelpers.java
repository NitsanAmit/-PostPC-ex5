package exercise.android.reemh.todo_items;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeHelpers {

    public static boolean isLessThanAnHourAgo(long time) {
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(time);

        Calendar hourAgo = Calendar.getInstance();
        hourAgo.setTimeInMillis(new Date().getTime() - TimeUnit.HOURS.toMillis(1));

        return date.after(hourAgo);
    }

    public static boolean isToday(long time) {
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(time);

        Calendar now = Calendar.getInstance();

        return now.get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR) && now.get(Calendar.YEAR) == date.get(Calendar.YEAR);
    }
}
